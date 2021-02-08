package inf112.skeleton.app.mechanics.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

public class MovementTest {

    TiledMap map;
    TiledMapTileLayer playerLayer;
    Movement m;

    @BeforeEach
    public void setup() {
        TmxMapLoader loader = new TmxMapLoader();
        playerLayer = new TiledMapTileLayer(5, 5, 300, 300);
        m = new Movement(new Vector2(2, 2), playerLayer);
        playerLayer.setCell((int) m.playerPosition.x, (int) m.playerPosition.y, new TiledMapTileLayer.Cell());
    }

    @Test
    public void testMoveUp() {
        setup(); // TODO @BeforeEach is not run, call setup() from here..

        // Assert that the Cell in the playerLayer is occupied
        assertNotNull(m.playerLayer.getCell((int) m.playerPosition.x, (int) m.playerPosition.y));

        // Test up arrow
        Vector2 oldPosition = new Vector2(m.playerPosition.x, m.playerPosition.y + 1);
        m.keyUp(Input.Keys.UP);
        assertEquals(oldPosition, m.playerPosition);

        // Assert that the Cell in the playerLayer is no longer occupied
        assertNull(m.playerLayer.getCell((int) m.playerPosition.x, (int) m.playerPosition.y));

        // Reset and test 'W' key
        oldPosition = new Vector2(m.playerPosition.x, m.playerPosition.y + 1);
        m.keyUp(Input.Keys.W);
        assertEquals(oldPosition, m.playerPosition);

        // Assert that the Cell in the playerLayer is no longer occupied
        assertNull(m.playerLayer.getCell((int) m.playerPosition.x, (int) m.playerPosition.y));
    }

    @Test
    public void testMoveDown() {
        setup(); // TODO @BeforeEach is not run, call setup() from here..

        // Assert that the Cell in the playerLayer is occupied
        assertNotNull(m.playerLayer.getCell((int) m.playerPosition.x, (int) m.playerPosition.y));

        // Test down arrow
        Vector2 oldPosition = new Vector2(m.playerPosition.x, m.playerPosition.y - 1);
        m.keyUp(Input.Keys.DOWN);
        assertEquals(oldPosition, m.playerPosition);

        // Assert that the Cell in the playerLayer is no longer occupied
        assertNull(m.playerLayer.getCell((int) m.playerPosition.x, (int) m.playerPosition.y));

        // Reset and test 'S' key
        oldPosition = new Vector2(m.playerPosition.x, m.playerPosition.y - 1);
        m.keyUp(Input.Keys.S);
        assertEquals(oldPosition, m.playerPosition);

        // Assert that the Cell in the playerLayer is no longer occupied
        assertNull(m.playerLayer.getCell((int) m.playerPosition.x, (int) m.playerPosition.y));
    }

    @Test
    public void testMoveLeft() {
        setup(); // TODO @BeforeEach is not run, call setup() from here..

        // Assert that the Cell in the playerLayer is occupied
        assertNotNull(m.playerLayer.getCell((int) m.playerPosition.x, (int) m.playerPosition.y));

        // Test left arrow
        Vector2 oldPosition = new Vector2(m.playerPosition.x - 1, m.playerPosition.y);
        m.keyUp(Input.Keys.LEFT);
        assertEquals(oldPosition, m.playerPosition);

        // Assert that the Cell in the playerLayer is no longer occupied
        assertNull(m.playerLayer.getCell((int) m.playerPosition.x, (int) m.playerPosition.y));

        // Reset and test 'A' key
        oldPosition = new Vector2(m.playerPosition.x - 1, m.playerPosition.y);
        m.keyUp(Input.Keys.A);
        assertEquals(oldPosition, m.playerPosition);

        // Assert that the Cell in the playerLayer is no longer occupied
        assertNull(m.playerLayer.getCell((int) m.playerPosition.x, (int) m.playerPosition.y));
    }

    @Test
    public void testMoveRight() {
        setup(); // TODO @BeforeEach is not run, call setup() from here..

        // Assert that the Cell in the playerLayer is occupied
        assertNotNull(m.playerLayer.getCell((int) m.playerPosition.x, (int) m.playerPosition.y));

        // Test right arrow
        Vector2 oldPosition = new Vector2(m.playerPosition.x + 1, m.playerPosition.y);
        m.keyUp(Input.Keys.RIGHT);
        assertEquals(oldPosition, m.playerPosition);

        // Assert that the Cell in the playerLayer is no longer occupied
        assertNull(m.playerLayer.getCell((int) m.playerPosition.x, (int) m.playerPosition.y));

        // Reset and test 'D' key
        oldPosition = new Vector2(m.playerPosition.x + 1, m.playerPosition.y);
        m.keyUp(Input.Keys.D);
        assertEquals(oldPosition, m.playerPosition);

        // Assert that the Cell in the playerLayer is no longer occupied
        assertNull(m.playerLayer.getCell((int) m.playerPosition.x, (int) m.playerPosition.y));
    }
}
