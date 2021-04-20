package inf112.skeleton.app.server.packets;

import java.io.Serializable;

public class LobbyUpdate implements Serializable {
    private final boolean start;
    private final int playerLimit;
    private final int connectedClients;

    public LobbyUpdate(boolean start, int playerLimit, int connectedClients) {
        this.start = start;
        this.playerLimit = playerLimit;
        this.connectedClients = connectedClients;
    }
}
