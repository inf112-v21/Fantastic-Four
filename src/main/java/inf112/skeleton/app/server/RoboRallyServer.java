package inf112.skeleton.app.server;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import inf112.skeleton.app.assets.Player;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.server.packets.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/*
Since the server and clients are
 */

public class RoboRallyServer extends Server {

    static final int udpPort = 62210;
    static final int tcpPort = 62210;

    final ArrayList<Connection> connections;
    final HashMap<Connection, Player> clients;

    final RoboGame roboGame;

    public RoboRallyServer(RoboGame roboGame) {
        super(8024, 8024);

        // ======== Assign roboGame for easy access throughout the code ========

        this.roboGame = roboGame;

        // ======== register() Registers "packets/classes" we send from client to server and vice-/versa ========

        this.register();

        // ======== Initiate lists for tracking users/players =========

        connections = new ArrayList<>();
        clients = new HashMap<>();
    }

    public void startServer(String nickname) {
        new Thread(this).start();

        // ======== Try-catch for lower crash-rates when trying to start a server. ========

        try {
            this.bind(udpPort, tcpPort);
            Utils.openPort(udpPort, tcpPort);
            System.out.println("[Server] Started server at port: " + udpPort + ". Send this IP to your friends: " + InetAddress.getLocalHost().getHostAddress());
        } catch (IOException e) {
            System.out.println("[Server] Could not bind to port " + udpPort + ". Something else is occupying it, close all other applications that utilize the same port before trying again.");
            //e.printStackTrace();
        }

         // ======= Start server =======

        this.start();

        // ======== Listener for server/client actions ========

        this.addListener(new Listener() {

            // ======== Method to run when server receives a new connection ========

            public void connected(Connection connection) {
                System.out.println("[Server] Received a connection from " + connection.getRemoteAddressTCP().getHostString());
                handleConnection(connection);
            }

            // ======== Method to run when a client disconnects from the server ========

            public void disconnected(Connection connection) {
                System.out.println("[Server] The following client has disconnected: " + connection.getRemoteAddressTCP().getHostString());
            }

            // ======== Method to run when receiving a new packet/class object ========

            public void received(Connection connection, Object packet) {
                if (packet instanceof ConfigureClient) {
                    System.out.println("[Server] Client Configuration received");
                    handleConfigureClient(connection, packet);
                }

                else if (packet instanceof PlayerAction) {
                    System.out.println("[Client] Server Action Received");
                    Gdx.app.postRunnable(() -> handlePlayerAction(packet));
                }

                else if (packet instanceof LobbyUpdate) {
                    startGame(packet);
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

    public void register() {
        Kryo kryo = this.getKryo();
        kryo.register(AddPlayer.class, new JavaSerializer());
        kryo.register(Connect.class, new JavaSerializer());
        kryo.register(PlayerAction.class, new JavaSerializer());
        kryo.register(Confirmation.class, new JavaSerializer());
        kryo.register(ConfigureClient.class, new JavaSerializer());
        kryo.register(PlayerAction.class, new JavaSerializer());
        kryo.register(LobbyUpdate.class, new JavaSerializer());
    }

    /**
     * Handles the connection and sends a confirmation to the client
     * @param connection
     */
    public void handleConnection(Connection connection) {
        // ======== Add the connection to standby list and await setup ========
        connections.add(connection);

        // ======== Create connection packet to send to client ========
        Confirmation confirm = new Confirmation(true);

        // ======== Send confirmation so client setup can start ========
        connection.sendTCP(confirm);
    }

    public void handleConfigureClient(Connection connection, Object packet) {
        // ======== Retrieve nickname from client and assign a player/robot to the client ========
        ConfigureClient config = (ConfigureClient) packet;

        Player newPlayer = new Player(config.nickname);
        newPlayer.setId(connection.getID());

        clients.put(connection, newPlayer);

        List<Player> players = new LinkedList<>();

        for (Player player : clients.values()) {
            players.add(player);
        }

        sendToAllTCP(new AddPlayer(players));
    }

    public void handlePlayerAction(Object packet) {
        sendToAllTCP(packet);
    }

    public void startGame(Object packet) {
        sendToAllTCP(packet);
    }

    public void shutdown() {
        Utils.closePorts(udpPort, tcpPort);
        this.close();
    }
}
