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
    }

    @Test
    public void testMoveUp() {
        setup();
        Vector2 oldPosition = new Vector2(m.playerPosition.x, m.playerPosition.y + 1);
        m.keyUp(Input.Keys.UP);
        assertEquals(oldPosition, m.playerPosition);

        oldPosition = new Vector2(m.playerPosition.x, m.playerPosition.y + 1);
        m.keyUp(Input.Keys.W);
        assertEquals(oldPosition, m.playerPosition);
    }

    @Test
    public void testMoveUDown() {
        setup();
        Vector2 oldPosition = new Vector2(m.playerPosition.x, m.playerPosition.y - 1);
        m.keyUp(Input.Keys.DOWN);
        assertEquals(oldPosition, m.playerPosition);

        oldPosition = new Vector2(m.playerPosition.x, m.playerPosition.y - 1);
        m.keyUp(Input.Keys.S);
        assertEquals(oldPosition, m.playerPosition);
    }

    @Test
    public void testMoveLeft() {
        setup();
        Vector2 oldPosition = new Vector2(m.playerPosition.x - 1, m.playerPosition.y);
        m.keyUp(Input.Keys.LEFT);
        assertEquals(oldPosition, m.playerPosition);

        oldPosition = new Vector2(m.playerPosition.x - 1, m.playerPosition.y);
        m.keyUp(Input.Keys.A);
        assertEquals(oldPosition, m.playerPosition);
    }

    @Test
    public void testMoveRight() {
        setup();
        Vector2 oldPosition = new Vector2(m.playerPosition.x + 1, m.playerPosition.y);
        m.keyUp(Input.Keys.RIGHT);
        assertEquals(oldPosition, m.playerPosition);

        oldPosition = new Vector2(m.playerPosition.x + 1, m.playerPosition.y);
        m.keyUp(Input.Keys.D);
        assertEquals(oldPosition, m.playerPosition);
    }
}
