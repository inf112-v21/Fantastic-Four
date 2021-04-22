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
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.assets.Player;
import inf112.skeleton.app.assets.cards.CardUI;
import inf112.skeleton.app.assets.cards.ProgramCard;
import inf112.skeleton.app.game.RoboGame;

import java.util.*;

public class GameActionScreen implements Screen {

	private SpriteBatch batch;
	private BitmapFont font;
	Texture background, slot;
	public TextureRegion[][] textureRegions;
	ArrayList<ImageButton> pickedCards;
	TiledMap tiledMap;
	TiledMapTileLayer playerLayer, boardLayer, holeLayer, flagLayer;
	final TmxMapLoader tmxMapLoader = new TmxMapLoader();
	InputMultiplexer inputMultiplexer;
	OrthogonalTiledMapRenderer renderer;
	OrthographicCamera camera;
	public Map<Player, TiledMapTileLayer.Cell> playerTextures;
	final String mapName;
	final RoboGame roboGame;
	OrthographicCamera gameCamera, uiCamera;

	final int width;
	final int height;
	List<ProgramCard> programCardsToChooseFrom;

	int viewPortWidth, viewPortHeight;
	final Stage pickedCardsStage;
	final Stage damageTokensStage;
	final CardUI cardui;
	final Stage otherButtonsStage;
	final Stage startCardsStage, powerButtonsStage;
	LinkedList<Integer> cardPositions;
	LinkedList<ProgramCard> picked;
	final LinkedList<ProgramCard> chosen;
	ImageButton imageButton;
	CardInputListener imageButtonListener;

	public GameActionScreen(RoboGame roboGame, String mapName) {
		this.roboGame = roboGame;
		this.mapName = mapName;
		batch = new SpriteBatch();
		cardui = new CardUI();
		startCardsStage = new Stage();
		powerButtonsStage = new Stage();
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
		resetCardPositions();

		// CardDeck
		chosen = new LinkedList<>();
	}

	public void resetCardPositions() {
		cardPositions = new LinkedList<>();
		cardPositions.add(65);
		cardPositions.add(190);
		cardPositions.add(315);
		cardPositions.add(440);
		cardPositions.add(565);
	}

	@Override
	public void show() {
		background = new Texture(Gdx.files.internal("backgroundui.png"));
//		batch = new SpriteBatch();
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
			assignPlayerTexture(textureRegions[i][player.directionIndex], player);
			i++;
		}

		showDamageTokens();
		showOtherButtons();
	}

	private void assignPlayerTexture(TextureRegion textureRegion, Player player) {
		System.out.println(player);
		TiledMapTileLayer.Cell playerCell = new TiledMapTileLayer.Cell();
		playerCell.setTile(new StaticTiledMapTile(textureRegion));
		playerTextures.put(player, playerCell);
	}

	public void showCards() {
		programCardsToChooseFrom = roboGame.localPlayer.getReceivedProgramCards();// TODO drawCard and cardNames are not
																					// used, picked is used but can be
																					// changed
		picked = new LinkedList<>();

		picked.addAll(programCardsToChooseFrom);

		int xStart = 800;
		int yStart = 295;
		int width = 120;
		int height = 200;
		int deltaW = 125;
		int deltaH = 205;
		int i = 0;
		//Add values to the cards
		Label.LabelStyle label1Style = new Label.LabelStyle();
		BitmapFont myFont = new BitmapFont(Gdx.files.internal("src/main/resources/skin/font-export.fnt"));
		myFont.getData().setScale(.6f);

		label1Style.font = myFont;
		label1Style.fontColor = Color.RED;

		int index = 0;
		for (int x = xStart; x < xStart + 3 * deltaW; x += deltaW) {
			for (int y = yStart; y < yStart + 3 * deltaH; y += deltaH) {
				ProgramCard currentCard = roboGame.localPlayer.getReceivedProgramCards().get(index);
				index++;
				imageButton = CardUI
						.createTextureButton(("/cards/" + currentCard.getProgramCardType().toString()));
				imageButton.setPosition(x, y);
				imageButton.setSize(width, height);
				Label cardvalue = new Label("" + currentCard.getPriorityNumber(), label1Style);
				Group overlay = new Group();
				cardvalue.setPosition((float) (imageButton.getX()+(width*.7)), (float) (imageButton.getY()+(height *.8)));
				overlay.addActor(imageButton);
				overlay.addActor(cardvalue);
				imageButton.addListener(new CardInputListener(imageButton, this, cardvalue, x, y, width, height, i));
				i++;
				startCardsStage.addActor(overlay);
				pickedCards = new ArrayList<>();
				pickedCards.add(imageButton);
			}

		}

	}

	public void hideCards() {
		startCardsStage.dispose();
	}

	public void clearCards() {
		startCardsStage.clear();
		pickedCardsStage.clear();
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
				powerButtonsStage.addActor(powerDownON);

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
				powerButtonsStage.addActor(lockCardsON);

			}

			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		
		powerButtonsStage.addActor(lockCards);
		powerButtonsStage.addActor(powerDown);

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
		powerButtonsStage.act();
		powerButtonsStage.draw();
		
		//Handle 2 x input 
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		inputMultiplexer.addProcessor(startCardsStage);
		inputMultiplexer.addProcessor(powerButtonsStage);
		Gdx.input.setInputProcessor(inputMultiplexer);
		

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