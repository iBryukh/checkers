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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Checker checker = (Checker) o;

        if (color != checker.color) return false;
        if (type != checker.type) return false;
        return position != null ? position.equals(checker.position) : checker.position == null;

    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
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
