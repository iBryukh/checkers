package checkers.server;

import checkers.pojo.ChangeObject;
import checkers.server.room.Player;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * Created by oleh_kurpiak on 13.09.2016.
 */
public class Server {

    public static final int DEFAULT_PORT = 3000;

    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(port(args));
            Player player = new Player(serverSocket.accept());

            ChangeObject object = new ChangeObject();
            object.setMessage("server");
            player.write(object);
            System.out.println(player.read());
            player.endConnection();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int port(String[] args){
        int port = DEFAULT_PORT;
        if(args != null && args.length > 0){
            try {
                port = Integer.parseInt(args[0]);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return port;
    }

}
