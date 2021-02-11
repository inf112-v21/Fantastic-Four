package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import inf112.skeleton.app.mechanics.map.Map;
import inf112.skeleton.app.screens.MainMenuScreen;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("RoboRally");

        Map ui = new Map("example.tmx");

        cfg.setWindowedMode(500, 500);

        new Lwjgl3Application(ui, cfg);
    }
}