package checkers.pojo.board;

import java.io.Serializable;

/**
 * Created by oleh_kurpiak on 21.09.2016.
 */
public enum Letters implements Serializable {

    A(1),

    B(2),

    C(3),

    D(4),

    E(5),

    F(6),

    G(7),

    H(8);

    private int value;

    Letters(int value){
        this.value = value;
    }

    public boolean isOdd(){
        return value % 2 != 0;
    }

    public int getValue(){
        return value;
    }
}
