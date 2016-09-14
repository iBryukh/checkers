package checkers.pojo;

import java.io.Serializable;

/**
 * Created by oleh_kurpiak on 14.09.2016.
 */
public class ChangeObject implements Serializable {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ChangeObject{");
        sb.append("message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
