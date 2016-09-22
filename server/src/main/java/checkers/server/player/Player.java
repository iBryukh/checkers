package checkers.server.player;

import checkers.pojo.ChangeObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by oleh_kurpiak on 14.09.2016.
 */
public class Player {

    private Socket socket;

    private ObjectInputStream inputStream;

    private ObjectOutputStream outputStream;

    private boolean connected;

    public Player(Socket socket){
        try {
            this.socket = socket;
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
            this.inputStream = new ObjectInputStream(socket.getInputStream());
            connected = true;
        } catch (Exception e){
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
        if(!isConnected())
            return;

        try {
            outputStream.writeObject(object);
            outputStream.flush();
        } catch (Exception e){
            e.printStackTrace();
            endConnection();
        }
    }

    public void write(String message){
        ChangeObject object = new ChangeObject();
        object.setMessage(message);
        write(object);
    }

    public void endConnection(){
        connected = false;
        try {
            if (outputStream != null) {
                outputStream.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            if(inputStream != null)
                inputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        try {
            if(socket != null)
                socket.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean isConnected() {
        return connected;
    }
}
