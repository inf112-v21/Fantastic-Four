package inf112.skeleton.app.graphics;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.math.Vector2;

import java.util.Iterator;

/**
 * Morten
 */
public class UI_test extends InputAdapter implements ApplicationListener {
    private SpriteBatch batch;
    private BitmapFont font;
    private TiledMap map;
    private TiledMapTileLayer boardLayer, playerLayer, holeLayer, flagLayer;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    TiledMapTileLayer.Cell playerCell, playerWonCell, playerDiedCell;
    Vector2 playerPosition;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

        // Display map and layers
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("example.tmx");
        for (Iterator<String> it = map.getProperties().getKeys(); it.hasNext(); ) {
            String key = it.next();
            System.err.println("key, value: " + key + ", " + map.getProperties().get(key));
        }
        boardLayer = (TiledMapTileLayer) map.getLayers().get("Board");
        playerLayer = (TiledMapTileLayer) map.getLayers().get("Player");
        holeLayer = (TiledMapTileLayer) map.getLayers().get("Hole");
        flagLayer = (TiledMapTileLayer) map.getLayers().get("Flag");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 5, 5);
//        camera.translate(0f, 0.0f);
        camera.update();
        renderer = new OrthogonalTiledMapRenderer(map, 1f/300f);
        renderer.setView(camera);

        // Display player
        Texture playerTexture = new Texture("player.png");
        TextureRegion[][] trmap = TextureRegion.split(playerTexture, 300, 300);
        playerCell = new TiledMapTileLayer.Cell();
        playerCell.setTile(new StaticTiledMapTile(trmap[0][0]));
        playerDiedCell = new TiledMapTileLayer.Cell();
        playerDiedCell.setTile(new StaticTiledMapTile(trmap[0][1]));
        playerWonCell = new TiledMapTileLayer.Cell();
        playerWonCell.setTile(new StaticTiledMapTile(trmap[0][2]));
        playerPosition = new Vector2(0, 0);

        // Move player
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        renderer.render();
        if (holeLayer.getCell((int) playerPosition.x, (int) playerPosition.y) != null) {
            playerLayer.setCell((int) playerPosition.x, (int) playerPosition.y, playerDiedCell);
        }
        else if (flagLayer.getCell((int) playerPosition.x, (int) playerPosition.y) != null) {
            playerLayer.setCell((int) playerPosition.x, (int) playerPosition.y, playerWonCell);
        }
        else {
            playerLayer.setCell((int) playerPosition.x, (int) playerPosition.y, playerCell);
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                move(0, 1);
                break;
            case Input.Keys.DOWN:
                move(0, -1);
                break;
            case Input.Keys.LEFT:
                move(-1, 0);
                break;
            case Input.Keys.RIGHT:
                move(1, 0);
                break;
            case Input.Keys.W:
                move(0, 1);
                break;
            case Input.Keys.S:
                move(0, -1);
                break;
            case Input.Keys.A:
                move(-1, 0);
                break;
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

    public int getWidthPixels() {
        int numTiles = (int) map.getProperties().get("width");
        int tileWidth = (int) map.getProperties().get("tilewidth");
        return numTiles * tileWidth;
    }

    public int getHeightPixels() {
        int numTiles = (int) map.getProperties().get("height");
        int tileHeigth = (int) map.getProperties().get("tileheight");
        return numTiles * tileHeigth;
    }
}
