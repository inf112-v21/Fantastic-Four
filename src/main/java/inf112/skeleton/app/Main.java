package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import inf112.skeleton.app.graphics.UI_test;

public class Main {
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("RoboRally");

        UI_test ui = new UI_test();

//        cfg.setWindowedMode(ui.getWidthPixels(), ui.getHeightPixels());
        cfg.setWindowedMode(500, 500);

        new Lwjgl3Application(ui, cfg);
    }
}