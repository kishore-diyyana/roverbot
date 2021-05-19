package com.mufg.roverbot.domain;

import com.mufg.roverbot.constants.Angle;
import com.mufg.roverbot.constants.Direction;
import com.mufg.roverbot.constants.Rotation;

/**
 * This is a Domain class used to hold and process
 * Rover directions and rotations in coordinates.
 *
 * @author Kishore Diyyana
 */
public class Rover {

    private int x;

    private int y;

    private final char initialDirection;

    private char currentDirection;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getCurrentDirection() {
        return currentDirection;
    }

    public void setCurrentDirection(char currentDirection) {
        this.currentDirection = Character.toUpperCase(currentDirection);
    }

    public Rover(int x, int y, char currentDirection){
        this.setInitialLocation(x, y);
        this.currentDirection = Character.toUpperCase(currentDirection);
        this.initialDirection = this.currentDirection;
    }

    private void setInitialLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void changeDirection(char rotation, int angle) {
        //When angle 90
        if (angle == Angle.NINETY.asInt()) {
            changeTo90Degrees(rotation);
        }
        //When angle 180
        if (angle == Angle.ONE_EIGHTY.asInt()) {
            changeTo180Degrees(rotation);
        }
        //When angle 270
        if (angle == Angle.TWO_SEVENTY.asInt()) {
            changeTo270Degrees(rotation);
        }
        //When angle is 360 or 0 return to same location,
        // hence skipping calling of this method.
    }

    public void changeTo90Degrees(char rotation) {

        char rot = Character.toUpperCase(rotation);
        //When angle 90
        if (rot == Rotation.RIGHT.asChar() || rot == Rotation.LEFT.asChar()) {
            switch (getCurrentDirection()) {
                case 'N':
                    if (rot == Rotation.LEFT.asChar()) {
                        setCurrentDirection(Direction.WEST.asChar());
                    } else {
                        setCurrentDirection(Direction.EAST.asChar());
                    }
                    break;
                case 'W':
                    if (rot == Rotation.LEFT.asChar()) {
                        setCurrentDirection(Direction.SOUTH.asChar());
                    } else {
                        setCurrentDirection(Direction.NORTH.asChar());
                    }
                    break;
                case 'E':
                    if (rot == Rotation.LEFT.asChar()) {
                        setCurrentDirection(Direction.NORTH.asChar());
                    } else {
                        setCurrentDirection(Direction.SOUTH.asChar());
                    }
                    break;
                case 'S':
                    if (rot == Rotation.LEFT.asChar()) {
                        setCurrentDirection(Direction.EAST.asChar());
                    } else {
                        setCurrentDirection(Direction.WEST.asChar());
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Current Direction " +
                                getCurrentDirection() + " is unknown.");
            }
        }
    }

    public void changeTo180Degrees(char rotation) {
        char rot = Character.toUpperCase(rotation);
        //When angle 180
        if (rot == Rotation.RIGHT.asChar() || rot == Rotation.LEFT.asChar()) {
            switch (getCurrentDirection()) {
                case 'N':
                    setCurrentDirection(Direction.SOUTH.asChar());
                    break;
                case 'W':
                    setCurrentDirection(Direction.EAST.asChar());
                    break;
                case 'E':
                    setCurrentDirection(Direction.WEST.asChar());
                    break;
                case 'S':
                    setCurrentDirection(Direction.NORTH.asChar());
                    break;
                default:
                    throw new IllegalArgumentException("Current Direction " +
                            getCurrentDirection() + " is unknown.");
            }
        }
    }

    public void changeTo270Degrees(char rotation) {
        char rot = Character.toUpperCase(rotation);
        //When angle 270
        if (rot == Rotation.RIGHT.asChar() || rot == Rotation.LEFT.asChar()) {
            switch (getCurrentDirection()) {
                case 'N':
                    if (rot == Rotation.LEFT.asChar()) {
                        setCurrentDirection(Direction.EAST.asChar());
                    } else {
                        setCurrentDirection(Direction.WEST.asChar());
                    }
                    break;
                case 'W':
                    if (rot == Rotation.LEFT.asChar()) {
                        setCurrentDirection(Direction.NORTH.asChar());
                    } else {
                        setCurrentDirection(Direction.SOUTH.asChar());
                    }
                    break;
                case 'E':
                    if (rot == Rotation.LEFT.asChar()) {
                        setCurrentDirection(Direction.SOUTH.asChar());
                    } else {
                        setCurrentDirection(Direction.NORTH.asChar());
                    }
                    break;
                case 'S':
                    if (rot == Rotation.LEFT.asChar()) {
                        setCurrentDirection(Direction.WEST.asChar());
                    } else {
                        setCurrentDirection(Direction.EAST.asChar());
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Current Direction " +
                                getCurrentDirection() + " is unknown.");
            }
        }
    }

    public void moveForward(int coordinateLength) {
        switch(getCurrentDirection()){
            case 'N':
                this.y += coordinateLength;
                break;
            case 'W':
                this.x += -coordinateLength;
                break;
            case 'E':
                this.x += coordinateLength;
                break;
            case 'S':
                this.y += -coordinateLength;
                break;
            default:
                throw new IllegalArgumentException("Current Direction " + getCurrentDirection() + " is unknown.");
        }
    }

    public void moveBackward(int coordinateLength) {
        switch(getCurrentDirection()){
            case 'N':
                this.y -= coordinateLength;
                break;
            case 'W':
                this.x -= -coordinateLength;
                break;
            case 'E':
                this.x -= coordinateLength;
                break;
            case 'S':
                this.y -= -coordinateLength;
                break;
            default:
                throw new IllegalArgumentException("Current Direction " + getCurrentDirection() + " is unknown.");
        }
    }

    @Override
    public String toString() {
        return "Rover{" +
                "x=" + x +
                ", y=" + y +
                ", initialDirection=" + initialDirection +
                ", currentDirection=" + currentDirection +
                '}';
    }
}
