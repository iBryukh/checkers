package checkers.pojo.chess;

import java.io.Serializable;

/**
 * Created by oleh_kurpiak on 21.09.2016.
 */
public class Chess implements Serializable {

    private final ChessColor color;

    private final ChessType type;

    private Position position;

    public Chess(ChessColor color, ChessType type, Position position) {
        this.color = color;
        this.type = type;
        this.position = position;
    }

    public ChessColor getColor() {
        return color;
    }

    public ChessType getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
