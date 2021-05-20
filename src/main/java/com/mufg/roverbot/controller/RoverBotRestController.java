package com.mufg.roverbot.controller;

import com.mufg.roverbot.domain.RoverBotRequest;
import com.mufg.roverbot.service.RoverBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;

import javax.validation.Valid;

/***
 * Implement a REST service which receives the Stock Trades JSON to POST/GET data.
 * Perform the below tasks :
 *
 **POST** request to `/api/roverbot-service/events`
 **GET** request to `/api/roverbot-service/position`
 *
 * @author Kishore Diyyana
 */
@RestController
@Validated
@RequestMapping(value = "/api/roverbot-service", produces = { MediaType.APPLICATION_JSON_VALUE })
public class RoverBotRestController {

    private Logger log = LoggerFactory.getLogger(RoverBotRestController.class);
    @Autowired
    private RoverBotService service;

    /**
     * Test Stock-Trade Service status is Healthy or not
     * @return String
     */
    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public String status() {
        log.info("Rover-Bot Service is up and healthy.");
        return "Rover-Bot Service is up and healthy..."
                + LocalDate.now();
    }

    /**
     *  This method is used as Post Service and it performs following:
     *  - Creates a Rover events of Instruction Orders.
     *  - Save the Input and Output in an XML file.
     *
     *  The input for the API is the current position of the Bot and directions.
     *  The output is final position of the Bot.
     *
     * - If successfully created the response code is 201.
     * - Otherwise returns rest generated HTTP error messages.
     *
     * @param request
     * @return ResponseEntity
     */
    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public ResponseEntity events(@RequestBody @Valid RoverBotRequest request) throws Exception {
        log.info("RoverBot events method started.");
        return service.createEvents(request);
    }

    /**
     *  This method used as GET Service to retrieve Rover Postion and it performs following:
     * - Read the current position from the file of the rover.
     * - Return Rover current position in API response.
     * - Return position of RoverBot as Json Response once it read from xml file.
     *
     * - If successfully created the response code is 201.
     * - Otherwise returns rest generated HTTP error messages.
     *
     * @return ResponseEntity
     */
    @RequestMapping(value = "/position", method = RequestMethod.GET)
    public ResponseEntity getPosition() throws Exception {
        log.info("RoverBot getPosition method started.");
        return service.getPosition();
    }
}