package inf112.skeleton.app.server;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import inf112.skeleton.app.assets.Player;
import inf112.skeleton.app.assets.cards.ProgramCard;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.server.packets.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class RoboRallyClient extends Client {
    String nickname;

    final RoboGame roboGame;

    static final int udpPort = 62210;
    static final int tcpPort = 62210;

    public RoboRallyClient(RoboGame roboGame, String ip, String username, AtomicBoolean multiplayerReadyToStartGame) {
        // Start the client
        new Thread(this).start();
        this.start();
        this.roboGame = roboGame;
        this.nickname = username;

        this.register();

        // Connect to lobby
        try {
            this.connect(5000, ip, udpPort, tcpPort);
        } catch (IOException e) {
            System.out.println("Error connecting to server " + e);
        }

        // Register listeners
        this.addListener(new Listener.ThreadedListener(new Listener() {

            // Add new players to the main file
            public void received (Connection connection, Object object) {
                if (object instanceof AddPlayer) {
                    System.out.println("[Client] Updated player-list received");
                    AddPlayer addPlayer = (AddPlayer) object;
                    Gdx.app.postRunnable(() -> handlePlayersUpdate(addPlayer.players));
                    return;
                }

                else if (object instanceof Confirmation) {
                    System.out.println("[Client] Confirmation Received");
                    setupClient(object);
                }

                else if (object instanceof Connect) {
                    System.out.println("[Client] Connect Received");
                    Connect connect = (Connect) object;
                    handleInit(connect.getLobbyName(), connect.getIp());
                }

                else if (object instanceof PlayerAction) {
                    System.out.println("[Client] Player Action Received");
                    PlayerAction playerAction = (PlayerAction) object;
                    roboGame.playerAction(playerAction.cards, playerAction.player);
                }

                else if (object instanceof LobbyUpdate) {
                    LobbyUpdate lobbyUpdate = (LobbyUpdate) object;
                    if (lobbyUpdate.start) {
                        multiplayerReadyToStartGame.set(true);
//                        roboGame.launchGame();
                    }
                }
            }
        }));
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

    public void setupClient(Object object) {
        Confirmation confirmation = (Confirmation) object;

        // ======== Create and send back the configure packet ========
        ConfigureClient config = new ConfigureClient(nickname);
        this.sendTCP(config);
    }

    private void handlePlayersUpdate(List<Player> players) {
        for (Player player : players) {
            boolean exists = false;

            for (Player listedPlayer : roboGame.players) {
                if (listedPlayer.getId() == player.getId()) {
                    exists = true;
                }
            }
            if (!exists) {
                roboGame.players.add(player);
                System.out.println("[Client] + Added Player to list: " + player.id + ". List now: " + roboGame.players);
            }

            if (player.id == this.getID()) {
                roboGame.localPlayer = player;
                System.out.println("[Client] ! This player is the local player: " + player.id);
            }

            if (player.id == 1) {
                roboGame.host = player;
                System.out.println("[Client] ! This player is the host " + player.id);
            }
        }
    }

    public void startAsHost() {

        LobbyUpdate lobbyUpdate = new LobbyUpdate(true, roboGame.players.size());

        sendTCP(lobbyUpdate);
    }

    public void sendPlayerAction(Player player, List<ProgramCard> cards) {
        PlayerAction playerAction = new PlayerAction(cards, player);
        sendTCP(playerAction);
    }

    public void handleInit(String name, String ip) {

    }
}
