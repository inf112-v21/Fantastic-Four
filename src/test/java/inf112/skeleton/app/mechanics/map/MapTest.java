package inf112.skeleton.app.mechanics.map;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class MapTest {
    static Map map;
    static Lwjgl3Application app;
    static Lwjgl3ApplicationConfiguration cfg;

    @BeforeClass
    public static void setup() {
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

    @Test
    public void testLayersAreCreated() {
        assertNotNull(map.boardLayer);
        assertNotNull(map.playerLayer);
        assertNotNull(map.holeLayer);
        assertNotNull(map.flagLayer);
    }
}
