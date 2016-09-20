package checkers.client;

import checkers.pojo.Board;
import checkers.pojo.Step;

/**
 * Created by oleh_kurpiak on 16.09.2016.
 */
public class Tester {

    public static void main(String[] args){
        Client client = new Client(new CheckersBot() {

            public Step next(Board board) {
                return new Step();
            }


            public void onGameEnd(String message){
                System.out.println(message);
            }
        });
        client.run();
    }
}
