package checkers.pojo.board;

import java.io.Serializable;

/**
 * Created by oleh_kurpiak on 21.09.2016.
 */
public enum Letters implements Serializable {

    A(true),

    B(false),

    C(true),

    D(false),

    E(true),

    F(false),

    G(true),

    H(false);

    private boolean odd;

    Letters(boolean odd){
        this.odd = odd;
    }

    public boolean isOdd(){
        return odd;
    }
}
