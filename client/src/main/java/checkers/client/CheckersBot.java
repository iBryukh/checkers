package checkers.client;

import checkers.pojo.board.Board;
import checkers.pojo.step.Step;

/**
 * Created by oleh_kurpiak on 16.09.2016.
 */
public interface CheckersBot {

    /**
     *
     * @param board - board that server send to client
     * @return step that was calculated by bot
     */
    Step next(Board board);

    /**
     * execute when game ends
     * @param message - message from server
     */
    void onGameEnd(String message);

}