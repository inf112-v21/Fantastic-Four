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
	final TmxMapLoader tmxMapLoader = new TmxMapLoader();
	InputMultiplexer inputMultiplexer;
	OrthogonalTiledMapRenderer renderer;
	OrthographicCamera camera;
	Map<Player, TiledMapTileLayer.Cell> playerTextures;
	final String mapName;
	Movement movementMechanics;
	final RoboGame roboGame;
	OrthographicCamera gameCamera, uiCamera;

	int xPosition;
	final int width;
	final int height;
	ImageTextButton.ImageTextButtonStyle imageLabelButtonStyle;
	Texture lifeToken, lifeToken2, lifeToken3;
	ProgramDeck deck;
	List<ProgramCard> programCardsToChooseFrom;

	int viewPortWidth, viewPortHeight;
	final Stage pickedCardsStage;
	final Stage damageTokensStage;
	final CardUI cardui;
	final Stage otherButtonsStage;
	final Stage startCardsStage;
	final LinkedList<Integer> cardPositions;
	final LinkedList<ProgramCard> picked;
	final LinkedList<ProgramCard> chosen;

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

		// CardDeck
		picked = new LinkedList<>();
		chosen = new LinkedList<>();
		boolean donePickingCards = false;
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
		programCardsToChooseFrom = roboGame.localPlayer.getReceivedProgramCards();// TODO drawCard and cardNames are not
																					// used, picked is used but can be
																					// changed

		picked.addAll(programCardsToChooseFrom);

		int xStart = 800;
		int yStart = 295;
		int width = 120;
		int height = 200;
		int deltaW = 125;
		int deltaH = 205;
		int i = 0;
		for (int x = xStart; x < xStart + 3 * deltaW; x += deltaW) {
			for (int y = yStart; y < yStart + 3 * deltaH; y += deltaH) {
				ProgramCard currentCard = roboGame.localPlayer.getReceivedProgramCards().remove(0);
				ImageButton imageButton = CardUI
						.createTextureButton(("/cards/" + currentCard.getProgramCardType().toString()));
				imageButton.setPosition(x, y);
				imageButton.setSize(width, height);
				imageButton.addListener(new CardInputListener(imageButton, this, x, y, width, height, i));
				startCardsStage.addActor(imageButton);
				i++;
			}
		}
	}

	public void hideCards() {
		startCardsStage.dispose();
	}

	public void showSlots() {
		slot = new Texture(Gdx.files.internal("src/main/resources/cards/SLOT.png"));

		int xStart = 800;
		int yStart = 305;
		int width = 120;
		int height = 185;
		int deltaW = 125;
		int deltaH = 205;

		batch.begin();
		for (int x = xStart; x < xStart + 3 * deltaW; x += deltaW) {
			for (int y = yStart; y < yStart + 3 * deltaH; y += deltaH) {
				batch.draw(slot, x, y, width, height);
			}
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
			damageButton.setPosition((float) (damageButtons.get(damageNumber - 1).getX()
					+ (damageButtons.get(damageNumber - 1).getWidth() * 0.7)), viewPortWidth);
			damageButton.setSize(120, 70);
			damageButtons.add(damageButton);
		}

		// Add elements to stage
		for (ImageButton damageButton : damageButtons)
			damageTokensStage.addActor(damageButton);
	}

	public void showOtherButtons() {
		// === Power down button ===
		ImageButton powerDown = CardUI.createTextureButton("powerdown");
		ImageButton powerDownON = CardUI.createTextureButton("powerdownON");

		ImageButton lockCards = CardUI.createTextureButton("lockcardsbutton");
		ImageButton lockCardsON = CardUI.createTextureButton("lockcardsbuttonON");

		powerDown.setSize(150, 150);
		powerDown.setPosition(870, 55);
		powerDown.addListener(new InputListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				powerDownON.setPosition(powerDown.getX(), powerDown.getY());
				powerDownON.setSize(150, 150);
				startCardsStage.addActor(powerDownON);

			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		
		lockCards.setSize(150, 150);
		lockCards.setPosition(1050, 55);
		lockCards.addListener(new InputListener() {
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				lockCardsON.setPosition(lockCards.getX(), lockCards.getY());
				lockCardsON.setSize(150, 150);
				startCardsStage.addActor(lockCardsON);

			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		
		startCardsStage.addActor(lockCards);
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
			playerTextures.get(player)
					.setTile(new StaticTiledMapTile(textureRegions[playerIndex][player.directionIndex]));
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