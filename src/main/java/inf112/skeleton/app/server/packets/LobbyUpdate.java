package inf112.skeleton.app.server.packets;

import java.io.Serializable;

public class LobbyUpdate implements Serializable {
    public final boolean start;
    public final int players;

    public LobbyUpdate(boolean start, int players) {
        this.start = start;
        this.players = players;
    }
}
