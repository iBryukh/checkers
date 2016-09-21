package checkers.pojo.step;

import checkers.pojo.chess.Position;

import java.io.Serializable;

/**
 * Created by oleh_kurpiak on 21.09.2016.
 */
public class StepUnit implements Serializable {

    private final Position from;

    private final Position to;

    public StepUnit(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }
}
