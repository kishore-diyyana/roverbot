package com.mufg.roverbot.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * This is a Domain class used to hold RoverBot Position attributes
 *
 * @author Kishore Diyyana
 */
public class Position {

    /**
     * X coordinate value
     */
    @NotNull
    private Integer x;

    /**
    * Y coordinate value
    */
    @NotNull
    private Integer y;

    /**
     * Direction value
     */
    @NotEmpty(message = "Please provide valid Direction value")
    @NotNull
    private String direction ;

    public Position(Integer x, Integer y, String direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public Position() { }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

   @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                ", direction='" + direction + '\'' +
                '}';
    }
}
