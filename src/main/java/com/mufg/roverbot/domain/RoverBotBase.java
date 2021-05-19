package com.mufg.roverbot.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * This is a Domain class used to hold RoverBot common attributes
 *
 * @author Kishore Diyyana
 */
public class RoverBotBase {
    
    @NotNull
    private Position position;

    public RoverBotBase(Position position) {
        this.position = position;
    }

    public RoverBotBase() {
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "RoverBotBase{" +
                "position=" + position +
                '}';
    }
}
