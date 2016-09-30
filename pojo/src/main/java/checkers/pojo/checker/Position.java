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

    @Override
    public String toString() {
        return ("{" + letter.toString() + ", " + number.getValue() + "}").replace("_", "");
    }
}
