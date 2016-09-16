package checkers.client;

import checkers.pojo.Board;
import checkers.pojo.Step;

/**
 * Created by oleh_kurpiak on 16.09.2016.
 */
public abstract class CheckersBot {

    /**
     *
     * @param board - board that server send to client
     * @return step that was calculated by bot
     */
    abstract Step next(Board board);

    /**
     * execute when game ends
     * @param message - message from server
     */
    abstract void onGameEnd(String message);

}