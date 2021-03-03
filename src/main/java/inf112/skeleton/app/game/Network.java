package inf112.skeleton.app.game;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import inf112.skeleton.app.assets.Player;
import inf112.skeleton.app.assets.cards.Card;

public class Network {
    static public final int port = 62210;

    static public void register (EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(AddPlayer.class);
        kryo.register(PlayCard.class);
    }

    static public class AddPlayer {
        public Player player;
    }

    static public class PlayCard {
        public Card card;
        public Player player;
    }
}
