package checkers.server.room;

import checkers.pojo.ChangeObject;
import checkers.pojo.board.Board;
import checkers.pojo.board.Letters;
import checkers.pojo.board.Numbers;
import checkers.pojo.checker.Checker;
import checkers.pojo.checker.CheckerColor;
import checkers.pojo.checker.CheckerType;
import checkers.pojo.checker.Position;
import checkers.server.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by oleh_kurpiak on 21.09.2016.
 */
public class GameRoom implements Runnable {

    public final int GAME_ROOM_ID;

    private Player firstPlayer;
    private String firstPlayerName;

    private Player secondPlayer;
    private String secondPlayerName;

    private Board board;

    private volatile boolean hasTwoPlayers = false;

    private volatile boolean gameRun = true;

    public GameRoom(Player first, int id){
        this.GAME_ROOM_ID = id;
        this.firstPlayer = first;
        if(firstPlayer.isConnected()){
            firstPlayerName = firstPlayer.read().getMessage();
            firstPlayer.write(new ChangeObject().playerColor(CheckerColor.WHITE));
            board = new Board();
            System.out.println(String.format("GAME ROOM %d: initialized with player '%s'", GAME_ROOM_ID, firstPlayerName));
        } else {
            gameRun = false;
        }
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
            secondPlayer.write(new ChangeObject().playerColor(CheckerColor.BLACK));
            System.out.println(String.format("GAME ROOM %d: second player connected with name '%s'", GAME_ROOM_ID, secondPlayerName));

            hasTwoPlayers = true;
            // start main game loop and start swapping with steps
            new Thread(this).start();
        }
    }

    public int ID(){
        return GAME_ROOM_ID;
    }

    public void run() {
        System.out.println(String.format("GAME ROOM %d: game started", GAME_ROOM_ID));
        // main game loop here

        // if true first player turn to move, otherwise second player
        boolean turn = true;

        while (gameRun){
            if(turn){
                firstPlayer.write(new ChangeObject().board(board));
                firstPlayer.read();
            } else {
                secondPlayer.write(new ChangeObject().board(board));
                secondPlayer.read();
            }

            turn = !turn;

            if(!firstPlayer.isConnected() || !secondPlayer.isConnected()){
                gameRun = false;
            }
        }

        ChangeObject object = new ChangeObject();
        object.setEnd(true);
        firstPlayer.write(object);
        secondPlayer.write(object);

        System.out.println(String.format("GAME ROOM %d: game ended", GAME_ROOM_ID));
    }
}
