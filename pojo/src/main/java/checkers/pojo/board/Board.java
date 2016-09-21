package checkers.pojo.board;

import checkers.pojo.chess.Chess;
import checkers.pojo.chess.ChessColor;
import checkers.pojo.chess.ChessType;
import checkers.pojo.chess.Position;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by oleh_kurpiak on 16.09.2016.
 */
public class Board implements Serializable {

    private final List<Chess> chesses;

    public Board(List<Chess> chesses) {
        this.chesses = chesses;
    }

    public List<Chess> getChesses() {
        return chesses;
    }

    /**
     *
     * @param color - color of chess for filtering
     * @param type - type of chess for filtering
     * @return list of chesses that match color and type or null if was not found any
     */
    public List<Chess> get(ChessColor color, ChessType type){
        List<Chess> result = new ArrayList<Chess>();
        for(Chess chess : chesses)
            if(chess.getType().equals(type) && chess.getColor().equals(color))
                result.add(chess);

        if(result.isEmpty())
            return null;
        return result;
    }

    /**
     *
     * @param color - color of chess for filtering
     * @return list of chesses that match color and type or null if was not found any
     */
    public List<Chess> get(ChessColor color){
        List<Chess> result = new ArrayList<Chess>();
        for(Chess chess : chesses)
            if(chess.getColor().equals(color))
                result.add(chess);

        if(result.isEmpty())
            return null;
        return result;
    }

    /**
     *
     * @param type - type of chess for filtering
     * @return list of chesses that match color and type or null if was not found any
     */
    public List<Chess> get(ChessType type){
        List<Chess> result = new ArrayList<Chess>();
        for(Chess chess : chesses)
            if(chess.getType().equals(type))
                result.add(chess);

        if(result.isEmpty())
            return null;
        return result;
    }

    /**
     *
     * @param p - position on board
     * @return chess on passed position or null if was not found
     */
    public Chess get(Position p){
        if(p == null)
            return null;

        Chess chess = null;
        for(Chess c : chesses)
            if(c.getPosition().isSame(p)){
                chess = c;
                break;
            }

        return chess;
    }


}
