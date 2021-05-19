package com.mufg.roverbot.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * This is a Domain class used to hold RoverBot Request attributes
 *
 * @author Kishore Diyyana
 */
public class RoverBotRequest extends RoverBotBase {

    @NotEmpty(message = "Please provide valid Instruction-Orders as Move object")
    @NotNull
    private List<Move> moveOrders;

    public RoverBotRequest(Position position, List<Move> moveOrders) {
        super(position);
        this.moveOrders = moveOrders;
    }

    public List<Move> getMove() {
        return moveOrders;
    }

    public void setMove(List<Move> instructionOrders) {
        this.moveOrders = instructionOrders;
    }

   @Override
    public String toString() {
        return "RoverBotRequest{" +
                "position=" + super.getPosition() +
                ", move=" + this.getMove() +
                '}';
    }
}
