package inf112.skeleton.app.mechanics.map;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
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
import inf112.skeleton.app.mechanics.player.Movement;

import java.util.Iterator;

public class Map implements ApplicationListener {
    private SpriteBatch batch;
    private BitmapFont font;

    TiledMap tiledMap;
    TiledMapTileLayer playerLayer, boardLayer, holeLayer, flagLayer;
    TiledMapTileLayer.Cell playerCell, playerWonCell, playerDiedCell;
    TmxMapLoader tmxMapLoader = new TmxMapLoader();

    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;

    Vector2 playerPosition;

    String mapName;

    Movement movementMechanics;

    public Map(String mapName) {
        this.mapName = mapName;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

        // Display map and layers
        tiledMap = tmxMapLoader.load(mapName);

        boardLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Board");
        playerLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Player");
        holeLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Hole");
        flagLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Flag");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 5, 5);
        camera.update();

        renderer = new OrthogonalTiledMapRenderer(tiledMap, 1f/300f);
        renderer.setView(camera);

        // Display player
        Texture playerTexture = new Texture("player.png");
        TextureRegion[][] textureRegions = TextureRegion.split(playerTexture, 300, 300);
        playerCell = new TiledMapTileLayer.Cell();
        playerCell.setTile(new StaticTiledMapTile(textureRegions[0][0]));
        playerDiedCell = new TiledMapTileLayer.Cell();
        playerDiedCell.setTile(new StaticTiledMapTile(textureRegions[0][1]));
        playerWonCell = new TiledMapTileLayer.Cell();
        playerWonCell.setTile(new StaticTiledMapTile(textureRegions[0][2]));
        playerPosition = new Vector2(0, 0);

        // Add Movement
        movementMechanics = new Movement(playerPosition, playerLayer);

        Gdx.input.setInputProcessor(movementMechanics);
    }

    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        renderer.render();
        if (holeLayer.getCell((int) playerPosition.x, (int) playerPosition.y) != null) {
            playerLayer.setCell((int) playerPosition.x, (int) playerPosition.y, playerDiedCell);
        }
        else if (flagLayer.getCell((int) playerPosition.x, (int) playerPosition.y) != null) {
            playerLayer.setCell((int) playerPosition.x, (int) playerPosition.y, playerWonCell);
            Gdx.app.getGraphics().setTitle("You won!");
            movementMechanics.stopGame();
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
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
