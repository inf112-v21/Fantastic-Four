package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.skeleton.app.graphics.UI_test;
import inf112.skeleton.app.mechanics.map.Map;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("RoboRally");

        Map ui = new Map("example.tmx");

//        cfg.setWindowedMode(ui.getWidthPixels(), ui.getHeightPixels());
        cfg.setWindowedMode(500, 500);

        new Lwjgl3Application(ui, cfg);
    }
}