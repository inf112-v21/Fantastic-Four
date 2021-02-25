package inf112.skeleton.app.game;

import com.esotericsoftware.kryonet.Server;

import java.awt.*;
import java.io.IOException;

public class RoboRallyServer {

    Server roboRallyServer;
    int PORT_MAIN = 62210;

    public void startServer() {
        roboRallyServer = new Server();
        roboRallyServer.start();
        try {
            roboRallyServer.bind(62210);
        } catch (IOException e) {
            System.out.println(Color.red + "Could not bind to port " + PORT_MAIN + ". Something else is occupying it, close all other applications that utilize the same port before trying again.");
            e.printStackTrace();
        }
    }
}
