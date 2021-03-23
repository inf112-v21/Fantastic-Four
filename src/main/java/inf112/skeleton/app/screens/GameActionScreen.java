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
import inf112.skeleton.app.assets.cards.ProgramCard;
import inf112.skeleton.app.assets.cards.ProgramCard.ProgramCardType;
import inf112.skeleton.app.assets.cards.ProgramDeck;
import inf112.skeleton.app.assets.Definitions;
import inf112.skeleton.app.assets.Player;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.mechanics.player.Movement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GameActionScreen implements Screen {

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
	ProgramDeck deck;
	List<ProgramCard> drawCards;
	
	
	int viewPortWidth, viewPortHeight;
	Stage pickedCardsStage;
	Stage damageTokensStage;
	CradUI cardui;
	Stage otherButtonsStage;
	Stage startCardsStage;
	LinkedList<Integer> cardPositions;
	LinkedList<ProgramCard> picked;
	LinkedList<ProgramCard> chosen;
	
	public GameActionScreen(RoboGame roboGame, String mapName) {
		this.roboGame = roboGame;
		this.mapName = mapName;
		batch = new SpriteBatch();
		cardui = new CradUI();
		startCardsStage = new Stage();
		width = Gdx.graphics.getWidth();
		height = Gdx.graphics.getHeight();

		gameCamera = new OrthographicCamera();
		uiCamera = new OrthographicCamera();

		gameCamera.setToOrtho(false, width - 400, height - 300); 
		uiCamera.setToOrtho(false, viewPortWidth, viewPortWidth);

		gameCamera.update();
		uiCamera.update();
		pickedCardsStage = new Stage();
		damageTokensStage = new Stage();
		otherButtonsStage = new Stage();

		gameCamera = new OrthographicCamera();
		uiCamera = new OrthographicCamera();

		gameCamera.update();
		uiCamera.update();
		cardPositions = new LinkedList<>();
		cardPositions.add(65);
		cardPositions.add(190);
		cardPositions.add(315);
		cardPositions.add(440);
		cardPositions.add(565);
		
		
		//CardDeck
//		drawCards = roboGame.localPlayer.getProgramCards();
		drawCards = roboGame.getdealProgramCards();
		picked = new LinkedList(); 
		chosen = new LinkedList<>();
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
		ImageButton poweroff = CradUI.createTextureButton("powerdown");
		
		poweroff.setSize(180, 180);
		poweroff.setPosition(870, 55);
		poweroff.addListener(new InputListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				//poweroff.setVisible(false);
				//poweroffON.setPosition(850, 55);
			//	startCardsStage.addActor(poweroffON);
			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		
		
		LinkedList<String> cardNames = new LinkedList<>();
		for (ProgramCard c : drawCards) {
			System.out.println(c.getProgramCardType());
			cardNames.add(c.getProgramCardType().toString());
			picked.add(c);
		}
		cardNames.size();
		
		
		

		ImageButton move1 = CradUI.createTextureButton(("/cards/" + cardNames.removeFirst()));
		move1.setPosition(800, 700);
		move1.setSize(120, 200);
		move1.addListener(new InputListener() {
			

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				ImageButton back = CradUI.createTextureButton("/back");
				back.setSize(120, 200);
				back.setPosition(800, 700);
				move1.setPosition(cardPositions.removeFirst(), 0);
				startCardsStage.addActor(back);
				pickedCardsStage.addActor(move1);
				chosen.add(picked.removeFirst());
			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});

		ImageButton move2 = CradUI.createTextureButton(("/cards/" + cardNames.removeFirst()));
		move2.setSize(120, 200);
		move2.setPosition(925, 700);
		move2.addListener(new InputListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				ImageButton back = CradUI.createTextureButton("/back");
				back.setSize(120, 200);
				back.setPosition(925, 700);
				move2.setPosition(cardPositions.removeFirst(), 0);
				pickedCardsStage.addActor(move2);
				startCardsStage.addActor(back);
				chosen.add(picked.removeFirst());


			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		ImageButton move3 = CradUI.createTextureButton(("/cards/" + cardNames.removeFirst()));
		move3.setSize(120, 200);
		move3.setPosition(1050, 700);
		move3.addListener(new InputListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				ImageButton back = CradUI.createTextureButton("/back");
				back.setPosition(1050, 700);
				back.setSize(120, 200);
				move3.setPosition(cardPositions.removeFirst(), 0);
				pickedCardsStage.addActor(move3);
				startCardsStage.addActor(back);
				chosen.add(picked.removeFirst());


			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});

		ImageButton move4 = CradUI.createTextureButton(("/cards/" + cardNames.removeFirst()));
		move4.setSize(120, 200);
		move4.setPosition(800, 495);
		move4.addListener(new InputListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				
				ImageButton back = CradUI.createTextureButton("/back");
				back.setPosition(800, 495);
				back.setSize(120, 200);
				move4.setPosition(cardPositions.removeFirst(), 0);
				pickedCardsStage.addActor(move4);
				startCardsStage.addActor(back);
				chosen.add(picked.removeFirst());


			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});

		ImageButton move5 =CradUI.createTextureButton(("/cards/" + cardNames.removeFirst()));
		move5.setSize(120, 200);
		move5.setPosition(925, 495);
		move5.addListener(new InputListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				ImageButton back = CradUI.createTextureButton("/back");
				back.setPosition(925, 495);
				back.setSize(120, 200);
				move5.setPosition(cardPositions.removeFirst(), 0);
				pickedCardsStage.addActor(move5);
				startCardsStage.addActor(back);
				chosen.add(picked.removeFirst());

			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		ImageButton move6 = CradUI.createTextureButton(("/cards/" + cardNames.removeFirst()));
		move6.setSize(120, 200);
		move6.setPosition(1050, 495);
		move6.addListener(new InputListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				ImageButton back = CradUI.createTextureButton("/back");
				back.setSize(120, 200);
				back.setPosition(1050, 495);
				move6.setPosition(cardPositions.removeFirst(), 0);
				pickedCardsStage.addActor(move6);
				startCardsStage.addActor(back);
				chosen.add(picked.removeFirst());


			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		ImageButton move7 = CradUI.createTextureButton(("/cards/" + cardNames.removeFirst()));
		move7.setSize(120, 200);
		move7.setPosition(800, 290);
		move7.addListener(new InputListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				
				ImageButton back = CradUI.createTextureButton("/back");
				back.setPosition(800, 290);
				back.setSize(120, 200);
				move7.setPosition(cardPositions.removeFirst(), 0);
				pickedCardsStage.addActor(move7);
				startCardsStage.addActor(back);
				chosen.add(picked.removeFirst());
				

			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		ImageButton move8 = CradUI.createTextureButton(("/cards/" + cardNames.removeFirst()));
		move8.setSize(120, 200);
		move8.setPosition(925, 290);
		move8.addListener(new InputListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				ImageButton back = CradUI.createTextureButton("/back");
				back.setPosition(925, 290);
				back.setSize(120, 200);
				move8.setPosition(cardPositions.removeFirst(), 0);
				pickedCardsStage.addActor(move8);
				startCardsStage.addActor(back);
				chosen.add(picked.removeFirst());


			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		ImageButton move9 = CradUI.createTextureButton(("/cards/" + cardNames.removeFirst()));
		move9.setSize(120, 200);
		move9.setPosition(1050, 290);
		move9.addListener(new InputListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				ImageButton back = CradUI.createTextureButton("/back");
				back.setPosition(1050, 290);
				back.setSize(120, 200);
				move9.setPosition(cardPositions.removeFirst(), 0);
				pickedCardsStage.addActor(move9);
				startCardsStage.addActor(back);
				chosen.add(picked.removeFirst());
			
				for (ProgramCard c: chosen) {
					System.out.println("picked" + c.getProgramCardType());
				}


			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		
	
		
		
		
		
		// Add elements to stage
		damageTokensStage.addActor(damage1);
		damageTokensStage.addActor(damage2);
		damageTokensStage.addActor(damage3);
		damageTokensStage.addActor(damage4);
		damageTokensStage.addActor(damage5);
		damageTokensStage.addActor(damage6);
		damageTokensStage.addActor(damage7);
		damageTokensStage.addActor(damage8);
		damageTokensStage.addActor(damage9);
		
		startCardsStage.addActor(move1);
		startCardsStage.addActor(move2);
		startCardsStage.addActor(move3);
		startCardsStage.addActor(move4);
		startCardsStage.addActor(move5);
		startCardsStage.addActor(move6);
		startCardsStage.addActor(move7);
		startCardsStage.addActor(move8);
		startCardsStage.addActor(move9);
		startCardsStage.addActor(poweroff);

		otherButtonsStage.addActor(life);
		otherButtonsStage.addActor(life2);
		otherButtonsStage.addActor(life3);

		// InputMultiplexer inputMultiplexer = new InputMultiplexer();
		// inputMultiplexer.addProcessor(moveCards);
		// inputMultiplexer.addProcessor(buttons);

	}
	
	public LinkedList<ProgramCard> getPickedCardList() {
		return chosen;
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
		Player player = roboGame.getPlayers().get(0);

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
	
		
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		startCardsStage.act();
		startCardsStage.draw();

		Gdx.input.setInputProcessor(startCardsStage);

		// RenderCards
		Gdx.gl.glViewport(-70, 0, Gdx.graphics.getWidth(), 700);
		pickedCardsStage.act();
		pickedCardsStage.draw();

		// Render damage tokens
		Gdx.gl.glViewport(-150, 200, Gdx.graphics.getWidth(), 700);
		damageTokensStage.act();
		damageTokensStage.draw();

		// render buttons
		Gdx.gl.glViewport(800, 50, Gdx.graphics.getWidth(), 700);
		otherButtonsStage.act();
		otherButtonsStage.draw();

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