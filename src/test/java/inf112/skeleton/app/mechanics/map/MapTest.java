package inf112.skeleton.app.mechanics.map;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class MapTest {
    Map map;
    Lwjgl3Application app;
    Lwjgl3ApplicationConfiguration cfg;

    @Before
    public void setup() {
        cfg = new Lwjgl3ApplicationConfiguration();
        cfg.setTitle("RoboRally");
//        cfg.setInitialVisible(false);
        cfg.setWindowedMode(500, 500);

        map = new Map("example.tmx");
        app = new Lwjgl3Application(map, cfg);
        app.exit();

    }

    @Test
    public void testMapObject() {
        assertNotNull(map.tiledMap);
    }
}
