package inf112.skeleton.app.game;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import inf112.skeleton.app.assets.Player;
import inf112.skeleton.app.assets.cards.Card;

import java.io.IOException;

public class RoboRallyClient {

    Client client;
    String nickname;

    RoboGame roboGame;

    public RoboRallyClient(RoboGame roboGame) {
        client = new Client();
        client.start();
        this.roboGame = roboGame;

        Network.register(client);

        client.addListener(new Listener.ThreadedListener(new Listener() {
            public void connected (Connection connection) {

            }

            public void received (Connection connection, Object object) {
                if (object instanceof Network.AddPlayer) {
                    Network.AddPlayer msg = (Network.AddPlayer) object;
                    roboGame.addPlayer(((Network.AddPlayer) object).player);
                    return;
                }
            }
        }));
    }

    public void connectToServer(String serverIp, String nickname) {
        try {
            client.connect(5000, serverIp, 62210);
            this.nickname = nickname;
            roboGame.launchGame();
        } catch (IOException e) {
            System.out.println("Could not connect to " + serverIp);
        }
    }

    public void updatePlayer(Player player, Card card) {

    }

}
