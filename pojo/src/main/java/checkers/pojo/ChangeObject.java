package checkers.pojo;

import checkers.pojo.board.Board;
import checkers.pojo.chess.ChessColor;
import checkers.pojo.step.Step;

import java.io.Serializable;

/**
 * Created by oleh_kurpiak on 14.09.2016.
 */
public class ChangeObject implements Serializable {

    private Board board;

    private Step step;

    private String message;

    private ChessColor playerColor;

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

    public ChessColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(ChessColor playerColor) {
        this.playerColor = playerColor;
    }

    public ChangeObject board(Board board){
        this.board = board;
        return this;
    }

    public ChangeObject step(Step step){
        this.step = step;
        return this;
    }

    public ChangeObject message(String message){
        this.message = message;
        return this;
    }

    public ChangeObject playerColor(ChessColor color){
        this.playerColor = color;
        return this;
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
