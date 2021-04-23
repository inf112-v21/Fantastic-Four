package inf112.skeleton.app.server.packets;

import java.io.Serializable;

public class Confirmation implements Serializable {
    public boolean success;

    public Confirmation(boolean success){
        this.success = success;
    }
}
