package inf112.skeleton.app.game;

import com.esotericsoftware.kryonet.Client;

import java.io.IOException;

public class RoboRallyClient {

    Client client;
    String nickname;

    RoboGame roboGame;

    public RoboRallyClient(RoboGame roboGame) {
        client = new Client();
        client.start();
        this.roboGame = roboGame;
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

}
