package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
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
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import inf112.skeleton.app.assets.Player;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.mechanics.player.Movement;

import java.util.HashMap;
import java.util.Map;

public class GameActionScreen implements Screen {

    private SpriteBatch batch;
    private BitmapFont font;

    TiledMap tiledMap;
    TiledMapTileLayer playerLayer, boardLayer, holeLayer, flagLayer;
    TmxMapLoader tmxMapLoader = new TmxMapLoader();

    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;

    Map<Player, TiledMapTileLayer.Cell> playerTextures;

    String mapName;

    Movement movementMechanics;

    RoboGame roboGame;

    public GameActionScreen(RoboGame roboGame, String mapName) {
        this.roboGame = roboGame;
        this.mapName = mapName;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        batch.begin();
        font = new BitmapFont();
        font.setColor(Color.RED);

        // Display map and layers
        tiledMap = tmxMapLoader.load("src/main/resources/" + mapName);

        boardLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Board");
        playerLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Player");
        holeLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Hole");
        flagLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Flag");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 12, 12); // TODO change the size of the map on the screen
        camera.update();

        renderer = new OrthogonalTiledMapRenderer(tiledMap, 1f / 300f); // TODO change the size of the map on the screen
        renderer.setView(camera);

        // Display player
        Texture playerTexture = new Texture("test.png");
        TextureRegion[][] textureRegions = TextureRegion.split(playerTexture, 300, 300);

        playerTextures = new HashMap<>();

        int i = 0;
        for (Player player : roboGame.getPlayers()) {
            System.out.println(player);
            TiledMapTileLayer.Cell playerCell = new TiledMapTileLayer.Cell();
            playerCell.setTile(new StaticTiledMapTile(textureRegions[0][i]));
            playerTextures.put(player, playerCell);
            i++;
        }

        Gdx.input.setInputProcessor(movementMechanics);
    }

    @Override
    public void render(float v) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        renderer.render();

        for (Player player : roboGame.getPlayers()) {
            float x = player.getRobotPosition().x;
            float y = player.getRobotPosition().y;
            playerLayer.setCell((int) x, (int) y, playerTextures.get(player));
            batch.draw(playerTextures.get(player).getTile().getTextureRegion(),
                player.getRobotPosition().x,
                player.getRobotPosition().y,
                150,
                150,
                300,
                300,
                1,
                1,
                player.getRobotPosition().angle());
        }
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}