package checkers.pojo.checker;

import java.io.Serializable;

/**
 * Created by oleh_kurpiak on 21.09.2016.
 */
public class Checker implements Serializable {

    private final CheckerColor color;

    private CheckerType type;

    private Position position;

    public Checker(CheckerColor color, CheckerType type, Position position) {
        this.color = color;
        this.type = type;
        this.position = position;
    }

    public CheckerColor getColor() {
        return color;
    }

    public CheckerType getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setType(CheckerType type){
        this.type = type;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Checker{");
        sb.append("color=").append(color);
        sb.append(", type=").append(type);
        sb.append(", position=").append(position);
        sb.append('}');
        return sb.toString();
    }
}
