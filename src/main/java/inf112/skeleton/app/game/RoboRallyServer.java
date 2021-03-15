package inf112.skeleton.app.game;

import com.esotericsoftware.kryonet.*;
import inf112.skeleton.app.assets.Player;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

public class RoboRallyServer {

    Server roboRallyServer;
    static int udpPort = 62210, tcpPort = 62210;

    ArrayList<Connection> connections = new ArrayList<>();
    HashMap<Connection, PlayerConnection> players = new HashMap();

    String nickname;

    RoboGame roboGame;

    public RoboRallyServer(RoboGame roboGame, String nickname) {
        this.roboGame = roboGame;
        this.nickname = nickname;
    }

    public void startServer() {
        roboRallyServer = new Server();

        roboRallyServer.start();

        // Try-catch for lower crash-rates when trying to start a server.
        try {
            roboRallyServer.bind(udpPort, tcpPort);
            System.out.println("[Server] Started server at port: " + udpPort + ". Send this IP to your friends: " + InetAddress.getLocalHost().getHostAddress());
            roboGame.launchGame();
        } catch (IOException e) {
            System.out.println("[Server] Could not bind to port " + udpPort + ". Something else is occupying it, close all other applications that utilize the same port before trying again.");
            //e.printStackTrace();
        }

        // For higher consistency we use the same packet/classes for both client and server.
        Network.register(roboRallyServer);

        // Registering listeners for events, used for retrieving packets from client and vice-versa
        roboRallyServer.addListener(new Listener() {
            // Gets executed every time we receive a new connection
            public void connected(Connection connection) {
                System.out.println("[Server] Received a connection from " + connection.getRemoteAddressTCP().getHostString());
                connections.add(connection);

                confirmConnection(connection);
            }

            // Gets executed every time a client disconnects
            public void disconnected(Connection connection) {
                System.out.println("[Server] The following client has disconnected: " + connection.getRemoteAddressTCP().getHostString());
            }

            // Gets executed every time the server receives a packet
            public void received(Connection connection, Object packet) {
                // Uncomment the line bellow to check if server receives packets
                // System.out.println(connection.getRemoteAddressTCP().getHostString() + ": " + packet.toString());

                //Check for specific packages
                if (packet instanceof Network.AddPlayer) {
                    Network.AddPlayer playerPacket = (Network.AddPlayer) packet;

                    PlayerConnection newPlayer = new PlayerConnection();
                    newPlayer.player = playerPacket.player;

                    players.put(connection, newPlayer);

                    roboRallyServer.sendToAllTCP(playerPacket);
                    System.out.println("Player " + playerPacket.player.getPlayerName() + " Has connected. All players present at the server now: " + roboGame.getPlayers());
                    return;
                }
            }
        });

        try {
            roboGame.connectToHost(InetAddress.getLocalHost().getHostAddress(), nickname);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        System.out.println("[Server] Server is operational");
    }

    public void confirmConnection(Connection c) {
        Network.ConnectionConfirmed connectionConfirmed = new Network.ConnectionConfirmed();
        connectionConfirmed.success = true;

        c.sendTCP(connectionConfirmed);

        System.out.println("[Server] Sent connection confirmation");
    }

    static class PlayerConnection extends Connection {
        public Player player;
    }
}
