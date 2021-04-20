package inf112.skeleton.app.server.packets;

import inf112.skeleton.app.assets.Player;

import java.io.Serializable;
import java.util.List;

public class AddPlayer implements Serializable {
    public List<Player> players;

    public AddPlayer(List<Player> players) {
        this.players = players;
    }
}
