package com.mufg.roverbot.domain;

/**
 * This is a Domain class used to hold RoverBotResponse attributes
 *
 * @author Kishore Diyyana
 */
public class RoverBotResponse extends RoverBotBase {

    public RoverBotResponse(Position position) {
        super(position);
    }

    public RoverBotResponse() { }

    @Override
    public String toString() {
        return "RoverBotResponse{" +
                "position=" + super.getPosition() +
                '}';
    }
}
