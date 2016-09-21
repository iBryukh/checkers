package checkers.pojo;

import checkers.pojo.board.Board;
import checkers.pojo.step.Step;

import java.io.Serializable;

/**
 * Created by oleh_kurpiak on 14.09.2016.
 */
public class ChangeObject implements Serializable {

    private Board board;

    private Step step;

    private String message;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ChangeObject{");
        sb.append("board=").append(board);
        sb.append(", step=").append(step);
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
