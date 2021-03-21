package inf112.skeleton.app;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import inf112.skeleton.app.game.RoboGame;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("RoboRally");
        cfg.setWindowedMode(1200, 900);

        //Map ui = new Map("example.tmx");
        //MainMenuScreen mainMenuScreen = new MainMenuScreen();
        RoboGame roboGame = new RoboGame();

        new Lwjgl3Application(roboGame, cfg);
    }
}