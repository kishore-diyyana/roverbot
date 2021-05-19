package com.mufg.roverbot.service;

import com.mufg.roverbot.domain.RoverBotRequest;
import org.springframework.http.ResponseEntity;

/**
 * This interface serve and process business operations.
 *
 * @author Kishore Diyyana
 */
public interface RoverBotService {

    /**
     * This method is used to createTrade
     * @param request
     * @return ResponseEntity
     */
    ResponseEntity createEvents(RoverBotRequest request) throws Exception;

    /**
     * This method is used to get RoverBot position.
     * @return ResponseEntity
     */
    ResponseEntity getPosition() throws Exception;
}
