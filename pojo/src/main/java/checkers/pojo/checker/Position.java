package checkers.pojo.checker;

import checkers.pojo.board.Letters;
import checkers.pojo.board.Numbers;

import java.io.Serializable;

/**
 * Created by oleh_kurpiak on 21.09.2016.
 */
public class Position implements Serializable {

    private final Letters letter;

    private final Numbers number;

    public Position(Letters letter, Numbers number) {
        this.letter = letter;
        this.number = number;
    }

    public Letters getLetter() {
        return letter;
    }

    public Numbers getNumber() {
        return number;
    }

    public boolean isSame(Position p){
        return p.letter == this.letter && p.number == this.number;
    }

    /**
     * return the middle position before two passed positions
     *
     * @param p1
     * @param p2
     * @return
     */
    public static Position middle(Position p1, Position p2){
        int letterValue = (p1.getLetter().getValue() + p2.getLetter().getValue()) / 2;
        int numberValue = (p1.getNumber().getValue() + p2.getNumber().getValue()) / 2;

        return new Position(Letters.getByValue(letterValue), Numbers.getByValue(numberValue));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        return number == position.number && letter == position.letter;

    }

    @Override
    public int hashCode() {
        int result = letter != null ? letter.hashCode() : 0;
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return ("{" + letter.toString() + ", " + number.getValue() + "}").replace("_", "");
    }
}
