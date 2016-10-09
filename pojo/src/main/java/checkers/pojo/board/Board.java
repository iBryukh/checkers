package checkers.pojo.board;

import checkers.pojo.checker.*;
import checkers.pojo.step.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by oleh_kurpiak on 16.09.2016.
 */
public class Board implements Serializable {

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
     * @throws IllegalArgumentException - throw if step is invalid. contains description why step is invalid
     */
    public void apply(Step step) throws IllegalArgumentException {
        System.out.println(checkers);
        for(StepUnit unit : step.getSteps()){
            Checker checker = get(unit.getFrom());
            int stepSize = stepSize(unit);

            if(stepSize == 1){
                if (get(unit.getTo()) == null) {
                    checker.setPosition(unit.getTo());
                } else {
                    throw new IllegalArgumentException(String.format("position %s is not empty", unit.getTo()));
                }
            } else if(checker.getType() == CheckerType.SIMPLE) {
                if (stepSize == 2) {
                    Position middlePosition = Position.middle(unit.getFrom(), unit.getTo());
                    Checker middle = get(middlePosition);
                    Checker atTo = get(unit.getTo());

                    if (atTo == null && middle != null && middle.getColor() != checker.getColor()) {
                        checkers.remove(middle);
                        checker.setPosition(unit.getTo());
                    } else {
                        throw new IllegalArgumentException(String.format("position %s is not empty or middle position %s was not empty", unit.getTo(), middlePosition));
                    }
                } else {
                    throw new IllegalArgumentException(String.format("simple checker can not move to %s position", unit.getTo()));
                }
            } else if(checker.getType() == CheckerType.QUEEN) {
                // todo:
            } else {
                throw new IllegalArgumentException("invalid step");
            }
        }
    }

    /**
     * return the number of position that checker was moved
     *
     * @param unit - step unit
     * @return
     */
    private int stepSize(StepUnit unit){
        return Math.abs(unit.getFrom().getLetter().getValue() - unit.getTo().getLetter().getValue());
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
