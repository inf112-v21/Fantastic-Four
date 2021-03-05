package inf112.skeleton.app.game;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import inf112.skeleton.app.assets.Player;
import inf112.skeleton.app.assets.ProgramSheet;
import inf112.skeleton.app.assets.Robot;
import inf112.skeleton.app.assets.cards.ProgramCard;
import inf112.skeleton.app.assets.cards.ProgramDeck;

public class Network {
    static public final int port = 62210;

    static public void register (EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(AddPlayer.class);
        kryo.register(PlayCard.class);
        kryo.register(ConnectionConfirmed.class);
        kryo.register(Player.class);
        kryo.register(Robot.class);
        kryo.register(ProgramSheet.class);
        kryo.register(ProgramDeck.class);
    }

    static public class AddPlayer {
        public Player player;
    }

    static public class PlayCard {
        public ProgramCard card;
        public Player player;
    }

    static public class ConnectionConfirmed {
        static boolean success;
    }
}
