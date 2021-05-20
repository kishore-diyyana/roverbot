package com.mufg.roverbot.service;

import com.mufg.roverbot.constants.FileConstants;
import com.mufg.roverbot.constants.Rotation;
import com.mufg.roverbot.controller.RoverBotRestController;
import com.mufg.roverbot.domain.*;
import com.mufg.roverbot.utils.RoverBotUtils;
import com.mufg.roverbot.validator.RoverValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Comparator;

/**
 * This class implements registering events of RoverBot.
 *
 * @author Kishore Diyyana
 */
@Service("RoverBotService")
public class RoverBotServiceImpl implements RoverBotService {
    private Logger log = LoggerFactory.getLogger(RoverBotServiceImpl.class);

    @Autowired
    RoverValidator validator;

    /**
     * This method is used to create Order of Instruction as Events for Rover
     * @param request
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity createEvents(RoverBotRequest request) throws Exception {

        //Validate Rover
        validator.validateRover(request);

        RoverBotResponse response;
        Rover rover = new Rover(request.getPosition().getX(), request.getPosition().getY(),
                                request.getPosition().getDirection().charAt(0));

        //Sort Order-of-Instructions in case it got jumbled in request.
        request.getMove().sort(Comparator.comparing(Move::getO));

        //Process Rover Events.
        processEvent(request, rover);

        //Build Rover Response
        response = new RoverBotResponse(new Position(rover.getX(),
                                rover.getY(), String.valueOf(rover.getCurrentDirection())));

        //Create Input and Output XML files.
        processXMLFile(request, response);
        log.debug("RoverBot createEvents method completed.");

        return new ResponseEntity(HttpStatus.CREATED);

    }

    /**
     * This method is used to get RoverBot position.
     * @return ResponseEntity
     */
    @Override
    public ResponseEntity getPosition() throws Exception {

        return ResponseEntity.status(HttpStatus.OK)
                .body(RoverBotUtils.readXMLFile(FileConstants.DIR_OUTPUT.getVal(),
                        FileConstants.FILE_NAME_OUTPUT.getVal()));
    }

    /**
     * This private method to Rover process Events
     * @param request
     * @param rover
     * @return Rover
     * @throws Exception
     */
    private Rover processEvent(RoverBotRequest request, Rover rover) throws Exception{
        for (Move move : request.getMove()) {
            //Skip when Rotation is 0 or 360 Degrees because Rover return to same place
            if ((move.getR() != null && move.getR() == 360) ||
                    move.getL() != null && move.getL() == 0) {
                continue;
            }
            if (move.getR() != null) {
                rover.changeDirection(Rotation.RIGHT.asChar(), move.getR());
            } else if (move.getL() != null) {
                rover.changeDirection(Rotation.LEFT.asChar(), move.getL());
            }
            if (move.getF() != null) {
                rover.moveForward(move.getF());
            } else if (move.getB() != null) {
                rover.moveBackward(move.getB());
            }
        }
        log.debug("RoverBot processEvent method completed.");
        return rover;
    }

    /**
     * This private method is used to process XML File.
     * Create Input and Output XML files.
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void processXMLFile(RoverBotRequest request, RoverBotResponse response) throws IOException {
        RoverBotUtils.createXMLFile(request, FileConstants.DIR_INPUT.getVal(),
                FileConstants.FILE_NAME_INPUT.getVal());

        RoverBotUtils.createXMLFile(response, FileConstants.DIR_OUTPUT.getVal(),
                FileConstants.FILE_NAME_OUTPUT.getVal());
        log.debug("RoverBot processXMLFile method completed.");
    }
	
}
