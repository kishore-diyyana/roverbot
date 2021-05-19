package com.mufg.roverbot.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles Global error messages and briefed into
 * single message to user as INTERNAL_SERVER_ERROR.
 *
 * @author Kishore Diyyana
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        System.out.println("RoverBot Exception Occurred - "+ ex); //TODO - /Log exceptions in log streams
        //ex.printStackTrace();
        return new ResponseEntity( HttpStatus.INTERNAL_SERVER_ERROR);
    }
}