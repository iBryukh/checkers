package checkers.server.room;

import checkers.pojo.ChangeObject;
import checkers.pojo.board.Board;
import checkers.pojo.checker.CheckerColor;
import checkers.server.logic.Validator;
import checkers.server.player.Player;

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

    private Validator validator = new Validator();

    private volatile boolean hasTwoPlayers = false;

    private volatile boolean gameRun = true;

    public GameRoom(Player first, int id){
        this.GAME_ROOM_ID = id;
        if(first.isConnected()){
            setFirstPlayer(first);
            board = new Board();
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
        if(!firstPlayer.isConnected()){
            setFirstPlayer(second);
            return;
        }

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

    private void setFirstPlayer(Player player){
        this.firstPlayer = player;
        firstPlayerName = firstPlayer.read().getMessage();
        firstPlayer.write(new ChangeObject().playerColor(CheckerColor.WHITE));

        System.out.println(String.format("GAME ROOM %d: initialized with player '%s'", GAME_ROOM_ID, firstPlayerName));
    }

    public int ID(){
        return GAME_ROOM_ID;
    }

    public void run() {
        System.out.println(String.format("GAME ROOM %d: game started", GAME_ROOM_ID));
        // main game loop here

        // if true first player turn to move, otherwise second player
        CheckerColor turn = CheckerColor.WHITE;

        while (gameRun){
            ChangeObject object;
            if(turn == CheckerColor.WHITE){
                firstPlayer.write(new ChangeObject().board(board));
                object = firstPlayer.read();
            } else {
                secondPlayer.write(new ChangeObject().board(board));
                object = secondPlayer.read();
            }

            String message = null;
            if(!validator.isValidDataFromUser(object)) {
                message = String.format("PLAYER %s SEND INVALID DATA", turn);
                gameRun = false;
            } else if(!validator.isValidStep(board, object.getStep(), turn)){
                message = String.format("PLAYER %s MAKE INVALID STEP", turn);
                gameRun = false;
            } else if(!firstPlayer.isConnected() || !secondPlayer.isConnected()){
                gameRun = false;
            }

            if(!gameRun){
                finishGame(message);
            } else {
                try {
                    board.apply(object.getStep());
                } catch (IllegalArgumentException e){
                    gameRun = false;
                    finishGame(e.getMessage());
                }
                turn = turn.opposite();
            }
        }

        System.out.println(String.format("GAME ROOM %d: game ended", GAME_ROOM_ID));
    }

    private void finishGame(String message){
        ChangeObject object = new ChangeObject();
        object.setEnd(true);
        object.setMessage(message);
        firstPlayer.write(object);
        secondPlayer.write(object);

        //TODO: send data to server
    }
}