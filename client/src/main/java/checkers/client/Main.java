package checkers.client;

import checkers.pojo.ChangeObject;

/**
 * Created by oleh_kurpiak on 13.09.2016.
 */
public class Main {

    public static void main(String[] args){
        ChangeObject object = new ChangeObject();
        object.setMessage("client");

        Client client = new Client();
        client.write(object);

        System.out.println(client.read());

        client.endConnection();
    }

}
