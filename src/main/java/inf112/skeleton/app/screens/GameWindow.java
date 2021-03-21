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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.assets.cards.CradUI;
import inf112.skeleton.app.assets.Definitions;
import inf112.skeleton.app.assets.Player;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.mechanics.player.Movement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class GameWindow implements Screen {

	private SpriteBatch batch;
	private BitmapFont font;
	Texture background;

	TiledMap tiledMap;
	TiledMapTileLayer playerLayer, boardLayer, holeLayer, flagLayer;
	TmxMapLoader tmxMapLoader = new TmxMapLoader();
	InputMultiplexer inputMultiplexer;
	OrthogonalTiledMapRenderer renderer;
	OrthographicCamera camera;

	Map<Player, TiledMapTileLayer.Cell> playerTextures;

	String mapName;

	Movement movementMechanics;

	RoboGame roboGame;

	Vector2 playerPosition;
	OrthographicCamera gameCamera, uiCamera;

	int width;
	int height;
	ImageTextButton.ImageTextButtonStyle imageLabelButtonStyle;
	Texture lifeToken, lifeToken2, lifeToken3;

	ControlPanel rules;
	int viewPortWidth, viewPortHeight;
	Stage uiStage;
	Stage damageStage;
	CradUI cardui;
	Stage buttons;
	Stage moveCards;
	LinkedList<Integer> positions;

	public GameWindow(RoboGame roboGame, String mapName) {
		this.roboGame = roboGame;
		this.mapName = mapName;
		batch = new SpriteBatch();
		cardui = new CradUI();
		moveCards = new Stage();
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		gameCamera = new OrthographicCamera();
		uiCamera = new OrthographicCamera();

		gameCamera.setToOrtho(false, width - 400, height - 300); 
		uiCamera.setToOrtho(false, viewPortWidth, viewPortWidth);

		gameCamera.update();
		uiCamera.update();
		rules = new ControlPanel(roboGame);
		uiStage = new Stage();
		damageStage = new Stage();
		buttons = new Stage();

		gameCamera = new OrthographicCamera();
		uiCamera = new OrthographicCamera();

		gameCamera.update();
		uiCamera.update();
		positions = new LinkedList<>();
		positions.add(65);
		positions.add(190);
		positions.add(315);
		positions.add(440);
		positions.add(565);

	}

	@Override
	public void show() {
		background = new Texture(Gdx.files.internal("backgroundui.jpg"));
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
		camera.setToOrtho(false, 12, 12);
		camera.update();

		renderer = new OrthogonalTiledMapRenderer(tiledMap, 1f / 300f);
		renderer.setView(camera);

		// Display player
		Texture playerTexture = new Texture("arrowsAndRobots.png");
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

		// LifeTokens
		Texture lifeTexture = new Texture(Gdx.files.internal("src/main/resources/greenLife.png"));

		TextureRegion lifeTextureRegion = new TextureRegion(lifeTexture);
		TextureRegionDrawable lifeTextureDrawable = new TextureRegionDrawable(lifeTextureRegion);

		ImageButton life = new ImageButton(lifeTextureDrawable);
		life.setSize(250, 200);
		life.setPosition(viewPortHeight - 90, viewPortWidth + 70);

		ImageButton life2 = new ImageButton(lifeTextureDrawable);
		life2.setPosition(viewPortHeight - 90, viewPortWidth);
		life2.setSize(250, 200);

		ImageButton life3 = new ImageButton(lifeTextureDrawable);
		life3.setPosition(viewPortHeight - 90, viewPortWidth - 70);
		life3.setSize(250, 200);

		
		
		// DamageTokens
		Texture damageTexture = new Texture(Gdx.files.internal("src/main/resources/tokendamage.png"));

		TextureRegion damageTextureRegion = new TextureRegion(damageTexture);
		TextureRegionDrawable damageTextureDrawable = new TextureRegionDrawable(damageTextureRegion);

		ImageButton damage1 = new ImageButton(damageTextureDrawable);
		damage1.setPosition(150, viewPortWidth);
		damage1.setSize(120, 70);

		ImageButton damage2 = new ImageButton(damageTextureDrawable);
		damage2.setPosition((float) (damage1.getX() + (damage1.getWidth() * 0.7)), viewPortWidth);
		damage2.setSize(120, 70);

		ImageButton damage3 = new ImageButton(damageTextureDrawable);
		damage3.setPosition((float) (damage2.getX() + (damage2.getWidth() * 0.7)), viewPortWidth);
		damage3.setSize(120, 70);

		ImageButton damage4 = new ImageButton(damageTextureDrawable);
		damage4.setPosition((float) (damage3.getX() + (damage2.getWidth() * 0.7)), viewPortWidth);
		damage4.setSize(120, 70);

		ImageButton damage5 = new ImageButton(damageTextureDrawable);
		damage5.setPosition((float) (damage4.getX() + (damage2.getWidth() * 0.7)), viewPortWidth);
		damage5.setSize(120, 70);

		ImageButton damage6 = new ImageButton(damageTextureDrawable);
		damage6.setPosition((float) (damage5.getX() + (damage2.getWidth() * 0.7)), viewPortWidth);
		damage6.setSize(120, 70);

		ImageButton damage7 = new ImageButton(damageTextureDrawable);
		damage7.setPosition((float) (damage6.getX() + (damage2.getWidth() * 0.7)), viewPortWidth);
		damage7.setSize(120, 70);

		ImageButton damage8 = new ImageButton(damageTextureDrawable);
		damage8.setPosition((float) (damage7.getX() + (damage2.getWidth() * 0.7)), viewPortWidth);
		damage8.setSize(120, 70);

		ImageButton damage9 = new ImageButton(damageTextureDrawable);
		damage9.setPosition((float) (damage8.getX() + (damage2.getWidth() * 0.7)), viewPortWidth);
		damage9.setSize(120, 70);

		
		// CARDS
		ImageButton poweroff = CradUI.createCard("powerdown");
		ImageButton poweroffON = CradUI.createCard("powerdownON");

		poweroff.setPosition(850, 55);
		poweroff.addListener(new InputListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				poweroff.setVisible(false);
				poweroffON.setPosition(850, 55);
				moveCards.addActor(poweroffON);
			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		
		
		
		
		

		ImageButton move1 = CradUI.createCard("/cards/move1");
		move1.setPosition(800, 700);
		move1.setSize(120, 200);
		move1.addListener(new InputListener() {
			
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				ImageButton back = CradUI.createCard("/back");
				back.setSize(120, 200);
				back.setPosition(800, 700);
				move1.setPosition(positions.removeFirst(), 0);
				moveCards.addActor(back);
				uiStage.addActor(move1);
			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});

		ImageButton move2 = CradUI.createCard("/cards/move2");
		move2.setSize(120, 200);
		move2.setPosition(925, 700);
		move2.addListener(new InputListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				ImageButton back = CradUI.createCard("/back");
				back.setSize(120, 200);
				back.setPosition(925, 700);
				move2.setPosition(positions.removeFirst(), 0);
				uiStage.addActor(move2);
				moveCards.addActor(back);

			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		ImageButton move3 = CradUI.createCard("/cards/move3");
		move3.setSize(120, 200);
		move3.setPosition(1050, 700);
		move3.addListener(new InputListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				ImageButton back = CradUI.createCard("/back");
				back.setPosition(1050, 700);
				back.setSize(120, 200);
				move3.setPosition(positions.removeFirst(), 0);
				uiStage.addActor(move3);
				moveCards.addActor(back);

			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});

		ImageButton move4 = CradUI.createCard("/cards/rotateLeft");
		move4.setSize(120, 200);
		move4.setPosition(800, 495);
		move4.addListener(new InputListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				
				ImageButton back = CradUI.createCard("/back");
				back.setPosition(800, 495);
				back.setSize(120, 200);
				move4.setPosition(positions.removeFirst(), 0);
				uiStage.addActor(move4);
				moveCards.addActor(back);

			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});

		ImageButton move5 = CradUI.createCard("/cards/rotateRight");
		move5.setSize(120, 200);
		move5.setPosition(925, 495);
		move5.addListener(new InputListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				ImageButton back = CradUI.createCard("/back");
				back.setPosition(925, 495);
				back.setSize(120, 200);
				move5.setPosition(positions.removeFirst(), 0);
				uiStage.addActor(move5);
				moveCards.addActor(back);

			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		ImageButton move6 = CradUI.createCard("/cards/rotateRight");
		move6.setSize(120, 200);
		move6.setPosition(1050, 495);
		move6.addListener(new InputListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				ImageButton back = CradUI.createCard("/back");
				back.setSize(120, 200);
				back.setPosition(1050, 495);
				move6.setPosition(positions.removeFirst(), 0);
				uiStage.addActor(move6);
				moveCards.addActor(back);

			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		ImageButton move7 = CradUI.createCard("/cards/rotateRight");
		move7.setSize(120, 200);
		move7.setPosition(800, 290);
		move7.addListener(new InputListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				
				ImageButton back = CradUI.createCard("/back");
				back.setPosition(800, 290);
				back.setSize(120, 200);
				move7.setPosition(positions.removeFirst(), 0);
				uiStage.addActor(move7);
				moveCards.addActor(back);

			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		ImageButton move8 = CradUI.createCard("/cards/rotateRight");
		move8.setSize(120, 200);
		move8.setPosition(925, 290);
		move8.addListener(new InputListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				ImageButton back = CradUI.createCard("/back");
				back.setPosition(925, 290);
				back.setSize(120, 200);
				move8.setPosition(positions.removeFirst(), 0);
				uiStage.addActor(move8);
				moveCards.addActor(back);

			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		ImageButton move9 = CradUI.createCard("/cards/rotateRight");
		move9.setSize(120, 200);
		move9.setPosition(1050, 290);
		move9.addListener(new InputListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				ImageButton back = CradUI.createCard("/back");
				back.setPosition(1050, 290);
				back.setSize(120, 200);
				move9.setPosition(positions.removeFirst(), 0);
				uiStage.addActor(move9);
				moveCards.addActor(back);

			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		
		// Add elements to stage
		damageStage.addActor(damage1);
		damageStage.addActor(damage2);
		damageStage.addActor(damage3);
		damageStage.addActor(damage4);
		damageStage.addActor(damage5);
		damageStage.addActor(damage6);
		damageStage.addActor(damage7);
		damageStage.addActor(damage8);
		damageStage.addActor(damage9);
		
		moveCards.addActor(move1);
		moveCards.addActor(move2);
		moveCards.addActor(move3);
		moveCards.addActor(move4);
		moveCards.addActor(move5);
		moveCards.addActor(move6);
		moveCards.addActor(move7);
		moveCards.addActor(move8);
		moveCards.addActor(move9);
		moveCards.addActor(poweroff);

		buttons.addActor(life);
		buttons.addActor(life2);
		buttons.addActor(life3);

		// InputMultiplexer inputMultiplexer = new InputMultiplexer();
		// inputMultiplexer.addProcessor(moveCards);
		// inputMultiplexer.addProcessor(buttons);

	}

	@Override
	public void render(float v) {
		Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), 550);

		batch.begin();
		batch.draw(background, 0, 0, 1200, 500);
		batch.end();

		// RENDER GAME //
		Gdx.gl.glViewport(0, 300, Gdx.graphics.getWidth() - 400, Gdx.graphics.getHeight() - 300);

		gameCamera.update();
		uiCamera.update();
		renderer.render();

		for (Player player : roboGame.getPlayers()) {
			// === (x, y) ===
			playerLayer.setCell(player.lastX, player.lastY, null);
			playerLayer.setCell(player.x, player.y, playerTextures.get(player));
			player.lastX = player.x;
			player.lastY = player.y;

			// === Direction ===
			Definitions.Direction direction = Definitions.Direction.values()[player.directionIndex];
			int angle;
			if (direction == Definitions.Direction.UP)
				angle = 0;
			else if (direction == Definitions.Direction.RIGHT)
				angle = 90;
			else if (direction == Definitions.Direction.DOWN)
				angle = 180;
			else
				angle = 270;
		}
		// render card 9.

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		moveCards.act();
		moveCards.draw();

		Gdx.input.setInputProcessor(moveCards);

		// RENDER cards /
		Gdx.gl.glViewport(-70, 0, Gdx.graphics.getWidth(), 700);
		uiStage.act();
		uiStage.draw();

		// Render damagetokens
		Gdx.gl.glViewport(-150, 200, Gdx.graphics.getWidth(), 700);
		damageStage.act();
		damageStage.draw();

		// render buttons
		Gdx.gl.glViewport(800, 50, Gdx.graphics.getWidth(), 700);
		buttons.act();
		buttons.draw();

	}

	@Override
	public void resize(int i, int i1) {
		moveCards.getViewport().update(width, height, true);

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