package inf112.skeleton.app.game;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import inf112.skeleton.app.assets.Player;
import inf112.skeleton.app.assets.cards.ProgramCard;

import java.io.IOException;

public class RoboRallyClient {

    Client client;
    String nickname;

    RoboGame roboGame;

    static int udpPort = 62210, tcpPort = 62210;

    public RoboRallyClient(RoboGame roboGame) {
        // Create client
        client = new Client();

        // Start the client
        client.start();
        this.roboGame = roboGame;

        Network.register(client);

        // Register listeners
        client.addListener(new Listener.ThreadedListener(new Listener() {

            // Add new players to the main file
            public void received (Connection connection, Object object) {
                if (object instanceof Network.AddPlayer) {
                    Network.AddPlayer msg = (Network.AddPlayer) object;
                    roboGame.addPlayer(((Network.AddPlayer) object).player);
                    return;
                }

                if (object instanceof Network.ConnectionConfirmed) {
                    addPlayer();
                }
            }
        }));
    }

    public void connectToServer(String serverIp, String nickname) {
        try {
            client.connect(5000, serverIp, udpPort, tcpPort);
            this.nickname = nickname;
            roboGame.launchGame();
            System.out.println("Now connected to " + serverIp + " under the name " + nickname);
        } catch (IOException e) {
            System.out.println("Could not connect to " + serverIp);
            e.printStackTrace();
        }
    }

    public void updatePlayer(Player player, ProgramCard card) {

    }

    public void addPlayer() {
        Network.AddPlayer addPlayer = new Network.AddPlayer();
        addPlayer.player = new Player(nickname, roboGame);

        System.out.println("Sent player register");

        client.sendTCP(addPlayer);
    }
}
