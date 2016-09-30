package checkers.pojo.board;

import checkers.pojo.checker.*;
import checkers.pojo.step.*;
import checkers.pojo.validator.Validator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by oleh_kurpiak on 16.09.2016.
 */
public class Board implements Serializable {

    private Validator validator = new Validator();

    private List<Checker> checkers;

    public Board() {
        this.checkers = new ArrayList<Checker>();
        for(Letters letter : Letters.values()){
            for(Numbers number : Arrays.asList(Numbers._1, Numbers._2, Numbers._3)){
                if(isCorrectPosition(letter, number)){
                    checkers.add(new Checker(CheckerColor.WHITE, CheckerType.SIMPLE, new Position(letter, number)));
                }
            }

            for(Numbers number : Arrays.asList(Numbers._6, Numbers._7, Numbers._8)){
                if(isCorrectPosition(letter, number)){
                    checkers.add(new Checker(CheckerColor.BLACK, CheckerType.SIMPLE, new Position(letter, number)));
                }
            }
        }
    }

    private boolean isCorrectPosition(Letters letter, Numbers number){
        return (letter.isOdd() && number.isOdd()) || (!letter.isOdd() && !number.isOdd());
    }

    public List<Checker> getCheckers() {
        return checkers;
    }

    /**
     * apply user steps to board
     * @param step - user steps
     * @param playerColor - current client checker color
     * @throws IllegalArgumentException - throw if step is invalid. contains description why step is invalid
     */
    public void apply(Step step, CheckerColor playerColor) throws IllegalArgumentException {
        // if steps is empty then user make invalid step
        if(step.getSteps().isEmpty())
            throw new IllegalArgumentException(String.format("INVALID STEP ERROR: Color[%s] has empty steps list", playerColor.toString()));

        Checker checker = null;
        for(StepUnit unit : step.getSteps()){
            checker = get(unit.getFrom());
            if(checker == null || checker.getColor() != playerColor)
                throw new IllegalArgumentException(String.format("INVALID STEP ERROR: No checker at %s or it has another color then player's", unit.getFrom().toString()));

            if(validator.isValidStep(checkers, checker, unit)){

            }

        }
    }

    /**
     *
     * @param color - color of checker for filtering
     * @param type - type of checker for filtering
     * @return list of checkers that match color and type or null if was not found any
     */
    public List<Checker> get(CheckerColor color, CheckerType type){
        List<Checker> result = new ArrayList<Checker>();
        for(Checker checker : checkers)
            if(checker.getType().equals(type) && checker.getColor().equals(color))
                result.add(checker);

        if(result.isEmpty())
            return null;
        return result;
    }

    /**
     *
     * @param color - color of checker for filtering
     * @return list of checkers that match color and type or null if was not found any
     */
    public List<Checker> get(CheckerColor color){
        List<Checker> result = new ArrayList<Checker>();
        for(Checker checker : checkers)
            if(checker.getColor().equals(color))
                result.add(checker);

        if(result.isEmpty())
            return null;
        return result;
    }

    /**
     *
     * @param type - type of checker for filtering
     * @return list of checkers that match color and type or null if was not found any
     */
    public List<Checker> get(CheckerType type){
        List<Checker> result = new ArrayList<Checker>();
        for(Checker checker : checkers)
            if(checker.getType().equals(type))
                result.add(checker);

        if(result.isEmpty())
            return null;
        return result;
    }

    /**
     *
     * @param p - position on board
     * @return checkers on passed position or null if was not found
     */
    public Checker get(Position p){
        if(p == null)
            return null;

        Checker checker = null;
        for(Checker c : checkers)
            if(c.getPosition().isSame(p)){
                checker = c;
                break;
            }

        return checker;
    }


}
