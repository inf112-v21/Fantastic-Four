package inf112.skeleton.app.game;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.FrameworkMessage;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.assets.Player;

import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;

public class RoboRallyServer {

    Server roboRallyServer;
    int PORT_MAIN = 62210;

    String nickname;

    RoboGame roboGame;

    public RoboRallyServer(RoboGame roboGame) {
        this.roboGame = roboGame;
    }

    public void startServer(String nickname) {
        this.nickname = nickname;

        roboRallyServer = new Server() {
            protected Connection newConnection () {
                // Providing our own connection implementation so we can store per connection without a connection ID to state look up
                return new PlayerConnection();
            }
        };

        roboRallyServer.start();

        // Try-catch for lower crash-rates when trying to start a server.
        try {
            roboRallyServer.bind(62210);
            System.out.println("Started server at port: " + PORT_MAIN + ". Send this IP to your friends: " + InetAddress.getLocalHost().getHostAddress());
            roboGame.launchGame();
        } catch (IOException e) {
            System.out.println("Could not bind to port " + PORT_MAIN + ". Something else is occupying it, close all other applications that utilize the same port before trying again.");
            //e.printStackTrace();
        }

        // For higher consistency we use the same packet/classes for both client and server.
        Network.register(roboRallyServer);

        // Registering listeners for events, used for retrieving packets from client and vice-versa
        roboRallyServer.addListener(new Listener() {
            public void received (Connection c, Object object) {
                PlayerConnection connection = (PlayerConnection)c;
                Player player = connection.player;

                if (object instanceof Network.AddPlayer) {
                    Player newPlayer = ((Network.AddPlayer)object).player;

                    roboGame.addPlayer(newPlayer);
                }

                if (object instanceof Network.PlayCard) {
                    // TODO: Add an action for each card
                }
            }
        });
    }

    static class PlayerConnection extends Connection {
        public Player player;
    }
}
