package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import inf112.skeleton.app.screens.MainMenuScreen;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("RoboRally");

        MainMenuScreen ui = new MainMenuScreen();

        cfg.setWindowedMode(500, 700);

        new Lwjgl3Application(ui, cfg);
    }
}