package checkers.client;

import checkers.pojo.ChangeObject;
import checkers.pojo.step.Step;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Created by oleh_kurpiak on 14.09.2016.
 */
public class Client {

    private static final int DEFAULT_PORT = 3000;

    private Socket socket;

    private ObjectOutputStream outputStream;

    private ObjectInputStream inputStream;

    private CheckersBot bot;

    public Client(CheckersBot bot){
        this(DEFAULT_PORT, bot);
    }

    public Client(int port, CheckersBot bot){
        this("localhost", port, bot);
    }

    public Client(String ip, int port, CheckersBot bot){
        try {
            this.socket = new Socket(InetAddress.getByName(ip), port);
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());
            this.bot = bot;
        } catch (IOException e) {
            e.printStackTrace();
            endConnection();
        }
    }

    public void run(){
        //write client name
        write(bot.clientBotName());

        // get player color
        ChangeObject onStartRead = read();
        if(onStartRead == null){
            endConnection();
            return;
        }

        bot.onGameStart(onStartRead.getPlayerColor());


        while (true){
            ChangeObject object = read();
            if(object == null) {
                bot.onGameEnd(CONNECTION_CLOSED);
                break;
            }

            Step step = bot.next(object.getBoard());
            write(new ChangeObject().step(step));
        }
        endConnection();
    }

    private ChangeObject read(){
        try {
            return (ChangeObject)inputStream.readObject();
        } catch (IOException e) {
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    private void write(ChangeObject object){
        try {
            outputStream.writeObject(object);
            outputStream.flush();
        } catch (Exception e){
            e.printStackTrace();
            endConnection();
        }
    }

    private void write(String message){
        ChangeObject object = new ChangeObject();
        object.setMessage(message);
        write(object);
    }

    private void endConnection(){
        try {
            if(socket != null && !socket.isClosed())
                socket.close();
        } catch (Exception e){ }

        try {
            if(inputStream != null && inputStream.available() != 0)
                inputStream.close();
        } catch (Exception e){ }

        try {
            if(outputStream != null)
                outputStream.close();
        } catch (Exception e){ }
    }

    private static final String CONNECTION_CLOSED = "CONNECTION CLOSED";
}
