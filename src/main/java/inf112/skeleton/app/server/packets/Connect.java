package inf112.skeleton.app.server.packets;

import java.io.Serializable;

public class Connect implements Serializable {
    public String ip;
    public String lobbyName;

    public Connect(String lobbyName, String ip) {
        this.lobbyName = lobbyName;
        this.ip = ip;
    }

    public String getLobbyName() {
        return this.lobbyName;
    }

    public String getIp() {
        return this.ip;
    }
}
