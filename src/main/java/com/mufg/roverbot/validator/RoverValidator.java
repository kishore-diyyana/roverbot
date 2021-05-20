package com.mufg.roverbot.validator;

import com.mufg.roverbot.domain.RoverBotRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * This class used to validate Rover Bot Service functions.
 *
 * @author Kishore Diyyana
 */
@Component
public class RoverValidator {

    private Logger log = LoggerFactory.getLogger(RoverValidator.class);
    Set validDirections = new HashSet<String>(Arrays.<String> asList("N", "S", "W", "E", "n", "s", "w", "e"));
    Set validDegrees = new HashSet<Integer>(Arrays.<Integer> asList(0, 90, 180, 270, 360));

    /**
     * This method is used to validate input parameters of Rover
     * @param request
     * @return List of type Move
     */
    public void validateRover(RoverBotRequest request) {
        if (request == null || request.getPosition() == null) {
            throw new IllegalArgumentException("Missing Position.");
        }
        if(CollectionUtils.isEmpty(request.getMove())) {
            //TODO - Log message as No Move.
            //Log Initial direction as Current direction in output file
            log.info("Initial direction as Current direction in output file.");
        }
        if(!validDirections.contains(request.getPosition().getDirection())){
            throw new IllegalArgumentException("Invalid Direction provided in Position:"+
                    request.getPosition().getDirection()+", valid directions are "+
                        validDirections.toString());
        }

        request.getMove().forEach(move -> {
            if(move.getR() != null && !validDegrees.contains(move.getR())){
                throw new IllegalArgumentException("Invalid Right Degree "+move.getR()+" provided in Move, valid Degrees are "+
                        validDegrees.toString());
            }
        });

        request.getMove().forEach(move -> {
            if(move.getL() != null && !validDegrees.contains(move.getL())){
                throw new IllegalArgumentException("Invalid Left Degree "+move.getL()+" provided in Move, valid Degrees are "+
                        validDegrees.toString());
            }
        });
    }
}
