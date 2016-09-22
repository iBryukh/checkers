package checkers.server.room;

import checkers.pojo.ChangeObject;
import checkers.pojo.board.Board;
import checkers.pojo.board.Letters;
import checkers.pojo.board.Numbers;
import checkers.pojo.chess.Chess;
import checkers.pojo.chess.ChessColor;
import checkers.pojo.chess.ChessType;
import checkers.pojo.chess.Position;
import checkers.server.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by oleh_kurpiak on 21.09.2016.
 */
public class GameRoom implements Runnable {

    public final int GAME_ROOM_ID = 1;

    private Player firstPlayer;
    private String firstPlayerName;

    private Player secondPlayer;
    private String secondPlayerName;

    private Board board;

    private volatile boolean hasTwoPlayers = false;

    private volatile boolean gameRun = true;

    public GameRoom(Player first){
        this.firstPlayer = first;
        if(firstPlayer.isConnected()){
            firstPlayerName = firstPlayer.read().getMessage();
            firstPlayer.write(new ChangeObject().playerColor(ChessColor.WHITE));
            initStartBoard();
            System.out.println(String.format("GAME ROOM %d: initialized with player '%s'", GAME_ROOM_ID, firstPlayerName));
        } else {
            gameRun = false;
        }
    }

    private void initStartBoard(){
        List<Chess> chesses = new ArrayList<Chess>();
        for(Letters letter : Letters.values()){
            for(Numbers number : Arrays.asList(Numbers._1, Numbers._2, Numbers._3)){
                if(isCorrectPosition(letter, number)){
                    chesses.add(new Chess(ChessColor.WHITE, ChessType.SIMPLE, new Position(letter, number)));
                }
            }

            for(Numbers number : Arrays.asList(Numbers._6, Numbers._7, Numbers._8)){
                if(isCorrectPosition(letter, number)){
                    chesses.add(new Chess(ChessColor.BLACK, ChessType.SIMPLE, new Position(letter, number)));
                }
            }
        }
        this.board = new Board(chesses);
    }

    private boolean isCorrectPosition(Letters letter, Numbers number){
        return (letter.isOdd() && number.isOdd()) || (!letter.isOdd() && !number.isOdd());
    }

    public boolean isHasTwoPlayers() {
        return hasTwoPlayers;
    }

    public boolean isGameRun() {
        return gameRun;
    }

    public void setSecondPlayer(Player second){
        this.secondPlayer = second;

        if(!firstPlayer.isConnected() || !secondPlayer.isConnected()){
            gameRun = false;
        } else {
            secondPlayerName = secondPlayer.read().getMessage();
            secondPlayer.write(new ChangeObject().playerColor(ChessColor.BLACK));
            System.out.println(String.format("GAME ROOM %d: second player connected with name '%s'", GAME_ROOM_ID, secondPlayerName));

            hasTwoPlayers = true;
            // start main game loop and start swapping with steps
            new Thread(this).start();
        }
    }

    public void run() {
        System.out.println(String.format("GAME ROOM %d: game started", GAME_ROOM_ID));
        // main game loop here

        // if true first player turn to move, otherwise second player
        boolean turn = true;

        while (gameRun){
            if(turn){
                firstPlayer.write(new ChangeObject().board(board));
                System.out.println(firstPlayer.read());
            } else {
                secondPlayer.write(new ChangeObject().board(board));
                System.out.println(secondPlayer.read());
            }

            turn = !turn;

            if(!firstPlayer.isConnected() || !secondPlayer.isConnected()){
                gameRun = false;
            }
        }
    }
}
