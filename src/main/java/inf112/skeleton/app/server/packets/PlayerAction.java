package inf112.skeleton.app.server.packets;

import inf112.skeleton.app.assets.Player;
import inf112.skeleton.app.assets.cards.ProgramCard;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PlayerAction implements Serializable {
    public List<ProgramCard> cards;
    public Player player;

    public PlayerAction(List<ProgramCard> cards, Player player) {
        this.cards = cards;
        this.player = player;
    }
}
