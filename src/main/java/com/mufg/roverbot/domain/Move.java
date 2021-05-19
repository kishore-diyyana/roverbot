package com.mufg.roverbot.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * This is a Domain class used to hold RoverBot Move attributes
 *
 * @author Kishore Diyyana
 */
public class Move {

    /**
     * Order of the instruction numeric value
     */
    @NotEmpty(message = "Please provide valid Order of the instruction numeric value")
    @NotNull
    private String o;

    /**
     * Rotation value of R - Turn Left
     */
    private Integer r;

    /**
     * Rotation value of L - Turn Left
     */
    private Integer l;

    /**
     *  Movement value of F : Forward
     */
    private Integer f;

    /**
     * Movement value of B: Backward
     */
    private Integer b;

    public Move(String o, Integer r, Integer l, Integer f, Integer b) {
        this.o=o;
        this.r=r;
        this.l=l;
        this.f=f;
        this.b=b;
    }
   public String getO() {
        return o;
    }

    public void setO(String o) {
        this.o = o;
    }

    public Integer getR() {
        return r;
    }

    public void setR(Integer r) {
        this.r = r;
    }

    public Integer getL() {
        return l;
    }

    public void setL(Integer l) {
        this.l = l;
    }

    public Integer getF() {
        return f;
    }

    public void setF(Integer f) {
        this.f = f;
    }

    public Integer getB() {
        return b;
    }

    public void setB(Integer b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "Move{" +
                "o='" + o + '\'' +
                ", r=" + r +
                ", l=" + l +
                ", f=" + f +
                ", b=" + b +
                '}';
    }
}
