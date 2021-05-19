package com.mufg.roverbot.constants;

/**
 * Direction:
 * ----------
 * N: North
 * E: East
 * S: South
 * W: West
 *
 * @author Kishore Diyyana
 */
public enum Direction {
    NORTH('N'),
    EAST('E'),
    SOUTH('S'),
    WEST('W');

    public char asChar() {
        return asChar;
    }

    private final char asChar;

    Direction(char asChar) {
        this.asChar = asChar;
    }
}