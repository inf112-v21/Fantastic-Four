package inf112.skeleton.app.game;

import com.esotericsoftware.kryonet.Client;

import java.io.IOException;

public class RoboRallyClient {

    Client client;

    public RoboRallyClient() {
        client = new Client();
        client.start();
    }

    public void connect(String serverIp) {
        try {
            client.connect(5000, serverIp, 62210);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
