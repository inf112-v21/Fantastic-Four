package inf112.skeleton.app.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import inf112.skeleton.app.assets.Player;
import inf112.skeleton.app.game.RoboGame;

public class PlayersScreen implements Screen {
	final SpriteBatch batch;
	final Texture logo;
	final Texture background;
	final Stage stage;
	final RoboGame roboGame;
	private final BitmapFont fontLabelBig, fontLabel;
	ImageTextButton startButton, hostButton, playerButton, labelplayerButton;
	ImageTextButtonStyle imageTextButtonStyle, playerButtonStyle, playersLabelStyle, hostButtonStyle;

	public PlayersScreen(RoboGame roboGame) {
		this.roboGame = roboGame;

		Skin skin = new Skin();

		// Create skin
		FileHandle fileHandle = Gdx.files.internal("src/main/resources/skin/uiskin.json");
		FileHandle atlasFile = fileHandle.sibling("uiskin.atlas");
		if (atlasFile.exists()) {
			Gdx.app.log("MyGame", "atlas file is loaded");
			skin.addRegions(new TextureAtlas(atlasFile));
		} else {
			Gdx.app.log("MyGame", "atlas file is NOT loaded");
		}

		// Initiate key variables
		batch = new SpriteBatch();

		logo = new Texture(Gdx.files.internal("logo2.png"));
		background = new Texture(Gdx.files.internal("background.png"));

		// Font section
		fontLabel = new BitmapFont(Gdx.files.internal("src/main/resources/skin/font-export.fnt"), false);
		fontLabelBig = new BitmapFont(Gdx.files.internal("src/main/resources/skin/font-title-export.fnt"), false);
		fontLabelBig.getData().setScale(0.5f);

		Label.LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = fontLabel;

		ImageTextButtonStyle imageTextButtonStyle = new ImageTextButtonStyle();
		imageTextButtonStyle.up = skin.newDrawable("panel2", Color.GRAY);
		imageTextButtonStyle.down = skin.newDrawable("panel2"); // Set image for pressed
		imageTextButtonStyle.over = skin.newDrawable("panel2", Color.BLUE); // set image for mouse over
		imageTextButtonStyle.pressedOffsetX = 1;
		imageTextButtonStyle.pressedOffsetY = -1;
		imageTextButtonStyle.font = fontLabel;
		imageTextButtonStyle.fontColor = Color.WHITE;

		playerButtonStyle = new ImageTextButtonStyle();
		playerButtonStyle.up = skin.newDrawable("label", Color.LIGHT_GRAY);
		imageTextButtonStyle.pressedOffsetX = 1;
		playerButtonStyle.pressedOffsetY = -1;
		playerButtonStyle.font = fontLabel;
		playerButtonStyle.fontColor = Color.WHITE;

		hostButtonStyle = new ImageTextButtonStyle();
		hostButtonStyle.up = skin.newDrawable("label", Color.RED);
		imageTextButtonStyle.pressedOffsetX = 1;
		hostButtonStyle.pressedOffsetY = -1;
		hostButtonStyle.font = fontLabel;
		hostButtonStyle.fontColor = Color.WHITE;

		playersLabelStyle = new ImageTextButtonStyle();
		playersLabelStyle.up = skin.newDrawable("panel1", Color.LIGHT_GRAY);
		playersLabelStyle.pressedOffsetX = 1;
		playersLabelStyle.pressedOffsetY = -1;
		playersLabelStyle.font = fontLabel;
		playersLabelStyle.fontColor = Color.WHITE;

		hostButton = new ImageTextButton("Host", hostButtonStyle);
		hostButton.setSize(80, 70);

		labelplayerButton = new ImageTextButton("Players", playersLabelStyle);
		labelplayerButton.setPosition(150, Gdx.app.getGraphics().getHeight() - 200);
		labelplayerButton.setSize(200, 70);

		startButton = new ImageTextButton("Start Game", imageTextButtonStyle);
		startButton.setX(Gdx.graphics.getWidth() / 2.0f);
		startButton.setX(Gdx.graphics.getWidth() / 2.0f);
		startButton.setSize(300, 70);
		startButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent changeEvent, Actor actor) {
				Gdx.app.exit();
				// TO DO SOMETHING

			}
		});

		ImageTextButton button2 = new ImageTextButton("Return", imageTextButtonStyle);
		button2.setX(320);
		button2.setY(80);
		button2.setSize(300, 60);

		button2.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent changeEvent, Actor actor) {
				Screen mainMenu = new MainMenuScreen(roboGame);
				roboGame.setScreen(mainMenu);

			}
		});

		stage = new Stage();
		stage.addActor(startButton);
		stage.addActor(button2);
		stage.addActor(hostButton);
		stage.addActor(labelplayerButton);

	}

	@Override
	public void dispose() {
		batch.dispose();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background, 0, 0, Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight());
		batch.draw(logo, (Gdx.app.getGraphics().getWidth() / 2.0f) - 200, Gdx.app.getGraphics().getHeight() - 200, 400,
				150);

		int y = Gdx.app.getGraphics().getHeight() - 300;

		for (Player p : roboGame.players) {
			playerButton = new ImageTextButton(p.getPlayerName(), playerButtonStyle);
		 	playerButton.setWidth(200); playerButton.setHeight(70);
		   	stage.addActor(playerButton); playerButton.setPosition(300, y);

		   	if (p.equals(roboGame.host)) {
		   		hostButton.setPosition(200, y);
		   		startButton.setPosition(600, y);
		   	}

		   	y-=80;
		}

		batch.end();
		stage.draw();
		stage.act();
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);

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
	public void hide() {

	}

}
