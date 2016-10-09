package checkers.server.logic;

import checkers.pojo.ChangeObject;
import checkers.pojo.board.Board;
import checkers.pojo.checker.CheckerColor;
import checkers.pojo.step.Step;

/**
 * Use this class to validate different things like steps, data, ets.
 *
 * Created by oleh_kurpiak on 30.09.2016.
 */
public class Validator {

    /**
     * just check if object that user send is not empty
     * @param object - object from user
     * @return true if object is not empty
     */
    public boolean isValidDataFromUser(ChangeObject object){
        return !(object == null || object.getStep() == null || object.getStep().getSteps() == null || object.getStep().getSteps().isEmpty());
    }

    /**
     * check if user make correct step
     *
     * @param board - current board
     * @param step - step from user
     * @param checkerColor - user checkers color
     * @return true if steps are valid
     */
    public boolean isValidStep(Board board, Step step, CheckerColor checkerColor){
        return true;
    }
}
