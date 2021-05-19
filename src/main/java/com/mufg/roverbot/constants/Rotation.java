package com.mufg.roverbot.constants;

/**
 * Rotation:
 * ------------
 * R: Turn right
 * L: Turn Left
 *
 * @author Kishore Diyyana
 */
public enum Rotation {
    LEFT('L'),
    RIGHT('R');

    public char asChar() {
        return asChar;
    }

    private final char asChar;

    Rotation(char asChar) {
        this.asChar = asChar;
    }

}