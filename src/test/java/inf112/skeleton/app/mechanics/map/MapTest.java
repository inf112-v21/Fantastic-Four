package inf112.skeleton.app.mechanics.map;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import inf112.skeleton.app.HelloWorld;
import inf112.skeleton.app.screens.MainMenuScreen;
import org.junit.Test;

import static org.junit.Assert.assertNull;

public class MapTest {

    @Test
    public void testMapObject() {
        Map map = new Map("example.tmx");

        assertNull(map);
    }
}
