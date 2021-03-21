package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
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
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;

import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.mechanics.player.Movement;

public class help implements Screen {

    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera gameCamera, uiCamera;
    TiledMap tiledMap;
    TiledMapTileLayer playerLayer, boardLayer, holeLayer, flagLayer;
    TiledMapTileLayer.Cell playerCell, playerWonCell, playerDiedCell;
    TmxMapLoader tmxMapLoader = new TmxMapLoader();

    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;

    Vector2 playerPosition;

    String mapName;

    Movement movementMechanics;

    RoboGame roboGame;

    int width;
    int height;
    RulesScreen rules;
	private ImageTextButton.ImageTextButtonStyle imageLabelButtonStyle;

    public help(RoboGame roboGame, String mapName) {
        this.roboGame = roboGame;
        this.mapName = mapName;
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
     // Make camera
        gameCamera = new OrthographicCamera();
        uiCamera   = new OrthographicCamera();
        
        
        
        // Set camera to correct view settings, making room for dashboard.
        gameCamera.setToOrtho(false, 50, 50 + 4);  // Set mode. +4, to include room for dashboard.
        uiCamera.setToOrtho(false, 50, 500);

        // Set camera, but does not scale with the fit viewport
        //gameCamera.position.y = initialCameraY;
        gameCamera.update();
        uiCamera.update();
        
       rules = new RulesScreen(roboGame);

    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.RED);

        // Display map and layers
        tiledMap = tmxMapLoader.load("src/main/resources/" + mapName);

        boardLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Board");
        playerLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Player");
        holeLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Hole");
        flagLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Flag");

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 5, 5);
        camera.update();

        renderer = new OrthogonalTiledMapRenderer(tiledMap, 1f/600f);
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

    @Override
    public void render(float v) {
    	 Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
         Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);


         // RENDER GAME //
         Gdx.gl.glViewport( 0, 200, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

         gameCamera.update();
         uiCamera.update();


        renderer.render();
        if (holeLayer.getCell((int) playerPosition.x, (int) playerPosition.y) != null) {
            playerLayer.setCell((int) playerPosition.x, (int) playerPosition.y, playerDiedCell);
        }
        else if (flagLayer.getCell((int) playerPosition.x, (int) playerPosition.y) != null) {
            playerLayer.setCell((int) playerPosition.x, (int) playerPosition.y, playerWonCell);
            Gdx.app.getGraphics().setTitle("You won!");
            GameOverScreen gameOverScreen = new GameOverScreen(roboGame);
            roboGame.setScreen(gameOverScreen);
            movementMechanics.stopGame();
        }
        else {
            playerLayer.setCell((int) playerPosition.x, (int) playerPosition.y, playerCell);
        }
     // RENDER GAME //
        Gdx.gl.glViewport(400, 200, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        rules.stage.act(v);
        rules.stage.draw();
        
        InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(movementMechanics);
		inputMultiplexer.addProcessor(rules.stage);
		Gdx.input.setInputProcessor(inputMultiplexer);
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        

    
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