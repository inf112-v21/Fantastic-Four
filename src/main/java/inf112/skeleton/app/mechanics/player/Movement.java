package inf112.skeleton.app.mechanics.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class Movement extends InputAdapter {

    final Vector2 playerPosition;
    final TiledMapTileLayer playerLayer;
    boolean gameRunning;

    public Movement (Vector2 _playerPosition, TiledMapTileLayer _playerLayer) {
        playerPosition = _playerPosition;
        playerLayer = _playerLayer;
        gameRunning = true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (!gameRunning) return false;
        switch (keycode) {
            case Input.Keys.UP:
            case Input.Keys.W:
                move(0, 1);
                break;
            case Input.Keys.DOWN:
            case Input.Keys.S:
                move(0, -1);
                break;
            case Input.Keys.LEFT:
            case Input.Keys.A:
                move(-1, 0);
                break;
            case Input.Keys.RIGHT:
            case Input.Keys.D:
                move(1, 0);
                break;
        }
        return false;
    }

    private void move(int dx, int dy) {
        playerLayer.setCell((int) playerPosition.x, (int) playerPosition.y, null);
        playerPosition.set(playerPosition.x + dx, playerPosition.y + dy);
    }

    public void stopGame() {
        gameRunning = false;
    }
}
