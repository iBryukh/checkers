package checkers.pojo.board;

import java.io.Serializable;

/**
 * Created by oleh_kurpiak on 21.09.2016.
 */
public enum Numbers implements Serializable {

    _1(true),

    _2(false),

    _3(true),

    _4(false),

    _5(true),

    _6(false),

    _7(true),

    _8(false);

    private boolean odd;

    Numbers(boolean odd){
        this.odd = odd;
    }

    public boolean isOdd(){
        return odd;
    }
}
