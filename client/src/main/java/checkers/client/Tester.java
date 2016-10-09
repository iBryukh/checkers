package checkers.client;

import checkers.pojo.board.Board;
import checkers.pojo.board.Letters;
import checkers.pojo.board.Numbers;
import checkers.pojo.checker.CheckerColor;
import checkers.pojo.checker.Position;
import checkers.pojo.step.Step;
import checkers.pojo.step.StepUnit;

/**
 * Created by oleh_kurpiak on 16.09.2016.
 */
public class Tester {

    public static void main(String[] args){
        Client client = new Client(new CheckersBot() {

            public void onGameStart(CheckerColor color) {
                System.out.println(color);
            }

            public Step next(Board board) {
                return new Step();
            }

            public void onGameEnd(String message){
                System.out.println(message);
            }

            public String clientBotName() {
                return "tester";
            }

        });
        client.run();
    }
}
