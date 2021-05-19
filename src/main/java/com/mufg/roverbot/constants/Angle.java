package com.mufg.roverbot.constants;

/**
 * Angle : 0, 90, 270, 360 Degrees.
 *
 * @author Kishore Diyyana
 */
public enum Angle {
    ZERO(0),
    NINETY(90),
    ONE_EIGHTY(180),
    TWO_SEVENTY(270),
    THREE_SIXTY(360);

    public int asInt() {
        return asInt;
    }

    private final int asInt;

    Angle(int asInt) {
        this.asInt = asInt;
    }
}