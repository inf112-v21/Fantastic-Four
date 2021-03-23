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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.assets.cards.CardUI;
import inf112.skeleton.app.assets.cards.ProgramCard;
import inf112.skeleton.app.assets.cards.ProgramDeck;
import inf112.skeleton.app.assets.Definitions;
import inf112.skeleton.app.assets.Player;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.mechanics.player.Movement;

import java.util.*;

public class GameActionScreen implements Screen {

	private SpriteBatch batch;
	private BitmapFont font;
	Texture background, slot;
	public TextureRegion[][] textureRegions;

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
	OrthographicCamera gameCamera, uiCamera;

	
	int xPosition;
	int width;
	int height;
	ImageTextButton.ImageTextButtonStyle imageLabelButtonStyle;
	Texture lifeToken, lifeToken2, lifeToken3;
	ProgramDeck deck;
	List<ProgramCard> drawCards;
	
	
	int viewPortWidth, viewPortHeight;
	Stage pickedCardsStage;
	Stage damageTokensStage;
	CardUI cardui;
	Stage otherButtonsStage;
	Stage startCardsStage;
	LinkedList<Integer> cardPositions;
	LinkedList<ProgramCard> picked;
	LinkedList<ProgramCard> chosen;
	private boolean donePickingCards;
	
	public GameActionScreen(RoboGame roboGame, String mapName) {
		this.roboGame = roboGame;
		this.mapName = mapName;
		batch = new SpriteBatch();
		cardui = new CardUI();
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
		picked = new LinkedList();
		chosen = new LinkedList<>();
		donePickingCards = false;
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

		// Display players
		Texture playerTexture = new Texture("arrowsAndRobots3.png");
		textureRegions = TextureRegion.split(playerTexture, 300, 300);

		playerTextures = new HashMap<>();
		int i = 0;
		for (Player player : roboGame.getPlayers()) {
			System.out.println(player);
			TiledMapTileLayer.Cell playerCell = new TiledMapTileLayer.Cell();
			playerCell.setTile(new StaticTiledMapTile(textureRegions[i][player.directionIndex]));
			playerTextures.put(player, playerCell);
			i++;
		}

		Gdx.input.setInputProcessor(movementMechanics);

		showDamageTokens();
		showOtherButtons();

	}

	public void showCards() {
		drawCards = roboGame.localPlayer.getReceivedProgramCards();

		LinkedList<String> cardNames = new LinkedList<>();
		for (ProgramCard c : drawCards) {
			cardNames.add(c.getProgramCardType().toString());
			picked.add(c);
		}


		int width = 120;
		int height = 200;
		for (int x = 800; x < 800 + 3 * 125; x += 125) {
			for (int y = 295; y < 295 + 3 * 205; y += 205) {
				System.out.println(x + " " + y);
				ProgramCard currentCard = roboGame.localPlayer.getReceivedProgramCards().remove(0);
				ImageButton imageButton = CardUI.createTextureButton(("/cards/" + currentCard.getProgramCardType().toString()));
				imageButton.setPosition(x, y);
				imageButton.setSize(width, height);
				imageButton.addListener(new CardInputListener(imageButton, this, x, y, width, height));
				startCardsStage.addActor(imageButton);
			}
		}
	}

	public void hideCards() {
		startCardsStage.dispose();
	}
	
	public void showSlots() {
		slot = new Texture(Gdx.files.internal("src/main/resources/cards/SLOT.png"));
		xPosition = 800;
		batch.begin();
		for (int i = 0; i < 3; i++) {
			batch.draw(slot, xPosition, 700, 120, 190);
			xPosition += 125;
		}
		xPosition = 800;

		for (int i = 0; i < 3; i++) {
			batch.draw(slot, xPosition, 505, 120, 190);
			xPosition += 125;
		}
		xPosition = 800;

		for (int i = 0; i < 3; i++) {
			batch.draw(slot, xPosition, 310, 120, 190);
			xPosition += 125;
		}
		batch.end();

	}

	public void showDamageTokens() {
		// DamageTokens
		Texture damageTexture = new Texture(Gdx.files.internal("src/main/resources/tokendamage.png"));
		TextureRegion damageTextureRegion = new TextureRegion(damageTexture);
		TextureRegionDrawable damageTextureDrawable = new TextureRegionDrawable(damageTextureRegion);

		List<ImageButton> damageButtons = new ArrayList<>();

		ImageButton firstDamageButton = new ImageButton(damageTextureDrawable);
		firstDamageButton.setPosition(150, viewPortWidth);
		firstDamageButton.setSize(120, 70);
		damageButtons.add(firstDamageButton);

		for (int damageNumber = 1; damageNumber < 9; damageNumber++) {
			ImageButton damageButton = new ImageButton(damageTextureDrawable);
			damageButton.setPosition((float) (damageButtons.get(damageNumber - 1).getX() + (damageButtons.get(damageNumber - 1).getWidth() * 0.7)), viewPortWidth);
			damageButton.setSize(120, 70);
			damageButtons.add(damageButton);
		}

		// Add elements to stage
		for (ImageButton damageButton : damageButtons) damageTokensStage.addActor(damageButton);
	}

	public void showOtherButtons() {
		// === Power down button ===
		ImageButton powerDown = CardUI.createTextureButton("powerdown");

		powerDown.setSize(180, 180);
		powerDown.setPosition(870, 55);
		powerDown.addListener(new InputListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		startCardsStage.addActor(powerDown);

		// === LifeTokens ===
		Texture lifeTexture = new Texture(Gdx.files.internal("src/main/resources/greenLife.png"));

		TextureRegion lifeTextureRegion = new TextureRegion(lifeTexture);
		TextureRegionDrawable lifeTextureDrawable = new TextureRegionDrawable(lifeTextureRegion);

		int widthOffset = -70;
		for (int lifeButton = 0; lifeButton < 3; lifeButton++) {
			ImageButton life = new ImageButton((lifeTextureDrawable));
			life.setSize(250, 200);
			life.setPosition(viewPortHeight - 90, viewPortWidth + widthOffset);
			otherButtonsStage.addActor(life);
			widthOffset += 70;
		}
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


		// === Move players/robots on-screen ===
		for (int playerIndex = 0; playerIndex < roboGame.getPlayers().size(); playerIndex++) {
			Player player = roboGame.getPlayers().get(playerIndex);
			// === (x, y) ===
			playerLayer.setCell(player.lastX, player.lastY, null);
			playerLayer.setCell(player.x, player.y, playerTextures.get(player));
			player.lastX = player.x;
			player.lastY = player.y;

			// === Direction ===
			playerTextures.get(player).setTile(new StaticTiledMapTile(textureRegions[playerIndex][player.directionIndex]));
		}

		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		showSlots();

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