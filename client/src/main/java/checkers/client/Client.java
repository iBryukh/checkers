package checkers.client;

import checkers.pojo.Board;
import checkers.pojo.ChangeObject;
import checkers.pojo.Step;

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
            endConnection();
        }
    }

    public void run(){
        while (true){
            ChangeObject object = read();
            if(object == null) {
                bot.onGameEnd(CONNECTION_CLOSED);
                break;
            }
            System.out.println(object.getMessage());
            Step step = bot.next(object.getBoard());
            object = new ChangeObject();
            object.setStep(step);
            object.setMessage("client");
            write(object);
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
            endConnection();
        }
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
