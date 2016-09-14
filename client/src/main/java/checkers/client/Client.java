package checkers.client;

import checkers.pojo.ChangeObject;

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

    public Client(){
        this(DEFAULT_PORT);
    }

    public Client(int port){
        this("localhost", port);
    }

    public Client(String ip, int port){
        try {
            this.socket = new Socket(InetAddress.getByName(ip), port);
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            endConnection();
        }
    }

    public ChangeObject read(){
        try {
            ChangeObject object = (ChangeObject)inputStream.readObject();
            return object;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void write(ChangeObject object){
        try {
            outputStream.writeObject(object);
            outputStream.flush();
        } catch (Exception e){
            endConnection();
        }
    }

    public void endConnection(){
        try {
            if(socket != null)
                socket.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
