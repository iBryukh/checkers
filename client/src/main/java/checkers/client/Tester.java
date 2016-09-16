package checkers.client;

import checkers.pojo.Board;
import checkers.pojo.Step;

/**
 * Created by oleh_kurpiak on 16.09.2016.
 */
public class Tester {

    public static void main(String[] args){
        Client client = new Client(new CheckersBot() {
            @Override
            Step next(Board board) {
                return new Step();
            }

            @Override
            void onGameEnd(String message){
                System.out.println(message);
            }
        });
        client.run();
    }

}
