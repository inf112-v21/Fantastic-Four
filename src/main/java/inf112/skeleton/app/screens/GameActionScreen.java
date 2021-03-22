package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
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
import inf112.skeleton.app.assets.Player;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.mechanics.player.Movement;

import java.util.HashMap;
import java.util.Map;

public class GameActionScreen implements Screen {
    private static final long TIMEBETWEENMOVES = 300;
    long lastMove;

    private SpriteBatch batch;
    private BitmapFont font;
    TextureRegion[][] textureRegions;

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
        lastMove = 0L;
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
        camera.setToOrtho(false, 12, 12);
        camera.update();

        renderer = new OrthogonalTiledMapRenderer(tiledMap, 1f / 300f);
        renderer.setView(camera);

        // Display player
        Texture playerTexture = new Texture("arrows2.png");
        textureRegions = TextureRegion.split(playerTexture, 300, 300);

        playerTextures = new HashMap<>();
        int i = 0;
        for (Player player : roboGame.getPlayers()) {
            System.out.println(player);
            TiledMapTileLayer.Cell playerCell = new TiledMapTileLayer.Cell();
            playerCell.setTile(new StaticTiledMapTile(textureRegions[i][0]));
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

        for (int playerIndex = 0; playerIndex < roboGame.getPlayers().size(); playerIndex++) {
            while (System.currentTimeMillis() - roboGame.lastMove < TIMEBETWEENMOVES) {
                // spin waiter TODO improve
            }
            roboGame.lastMove = System.currentTimeMillis();

            Player player = roboGame.getPlayers().get(playerIndex);

            // === (x, y) ===
            playerLayer.setCell(player.lastX, player.lastY, null);
            playerLayer.setCell(player.x, player.y, playerTextures.get(player));
            player.lastX = player.x;
            player.lastY = player.y;

            // === Direction ===
            playerTextures.get(player).setTile(new StaticTiledMapTile(textureRegions[playerIndex][player.directionIndex]));

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