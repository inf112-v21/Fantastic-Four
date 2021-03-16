package inf112.skeleton.app.screens;

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

public class MainMenuScreen implements Screen {
	private static final int BORDER = 20;
	SpriteBatch batch;
	Texture logo;
	Texture background;
	Stage stage;
	RoboGame roboGame;

	private BitmapFont fontLabel, fontLabelText;
	private Skin skin;
	private Label.LabelStyle labelStyle;
	private ImageTextButton button1, button2, button3, button4, button5;
	private ImageTextButton.ImageTextButtonStyle imageTextButtonStyle;

	public MainMenuScreen(RoboGame roboGame) {
		this.roboGame = roboGame;


		// Create skin
		skin = new Skin();

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

		logo = new Texture(Gdx.files.internal("src/main/resources/logo.png"));
		background = new Texture(Gdx.files.internal("background.png"));
		background.setWrap(Texture.TextureWrap.ClampToEdge, Texture.TextureWrap.ClampToEdge);

		// Font section
		fontLabel = new BitmapFont(Gdx.files.internal("src/main/resources/skin/font-export.fnt"), false);
		fontLabelText = new BitmapFont(Gdx.files.internal("src/main/resources/skin/font-export.fnt"), false);
		fontLabelText.setColor(Color.WHITE);

		labelStyle = new Label.LabelStyle();
		labelStyle.font = fontLabel;

		imageTextButtonStyle = new ImageTextButtonStyle();
		imageTextButtonStyle.up = skin.newDrawable("panel2", Color.GRAY);
		imageTextButtonStyle.down = skin.newDrawable("panel2"); // Set image for pressed
		imageTextButtonStyle.over = skin.newDrawable("panel2", Color.BLUE); // set image for mouse over
		imageTextButtonStyle.pressedOffsetX = 1;
		imageTextButtonStyle.pressedOffsetY = -1;
		imageTextButtonStyle.font = fontLabel;

		button1 = new ImageTextButton("Single Player", imageTextButtonStyle);
		button1.setSize(200, 60);
		button1.setX(Gdx.graphics.getWidth() / 2 - button1.getWidth() / 2);
		button1.setY(Gdx.graphics.getHeight() / 2 + button1.getHeight());
		button1.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent changeEvent, Actor actor) {
				roboGame.addPlayer(new Player("Player 1", 5, 5)); // TODO Change the starting position
				roboGame.launchGame();
			}
		});

		button2 = new ImageTextButton("Multiplayer", imageTextButtonStyle);
		button2.setSize(200, 60);
		button2.setX(Gdx.graphics.getWidth() / 2 - button2.getWidth() / 2);
		button2.setY(Gdx.graphics.getHeight() / 2);
		button2.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent changeEvent, Actor actor) {
				roboGame.initiateMultiplayer();
			}
		});

		button3 = new ImageTextButton("Rules", imageTextButtonStyle);
		button3.setSize(200, 60);
		button3.setX(Gdx.graphics.getWidth() / 2 - button3.getWidth() / 2);
		button3.setY(Gdx.graphics.getHeight() / 2 - button3.getHeight());
		button3.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent changeEvent, Actor actor) {
				Screen ruleScreen = new RulesScreen(roboGame);
				roboGame.setScreen(ruleScreen);
			}
		});

		button4 = new ImageTextButton("Settings", imageTextButtonStyle);
		button4.setSize(200, 60);
		button4.setX(Gdx.graphics.getWidth() / 2 - button4.getWidth() / 2);
		button4.setY(Gdx.graphics.getHeight() / 2 -
				button3.getHeight() -
				button4.getHeight());
		button4.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent changeEvent, Actor actor) {
				Gdx.app.exit();
			}
		});

		button5 = new ImageTextButton("Quit", imageTextButtonStyle);
		button5.setSize(200, 60);
		button5.setX(Gdx.graphics.getWidth() / 2 - button5.getWidth() / 2);
		button5.setY(Gdx.graphics.getHeight() / 2 -
				button3.getHeight() -
				button4.getHeight() -
				button5.getHeight());
		button5.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent changeEvent, Actor actor) {
				Gdx.app.exit();
			}
		});

		stage = new Stage();
		stage.addActor(button1);
		stage.addActor(button2);
		stage.addActor(button3);
		stage.addActor(button4);
		stage.addActor(button5);

	}

	public void dispose() {
		batch.dispose();
	}

	public void render(float delta) {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(background,
				0,
				0,
				Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight());
		batch.draw(logo,
				Gdx.graphics.getWidth() / 2 - logo.getWidth() / 2,
				Gdx.graphics.getHeight() - logo.getHeight() - BORDER,
				logo.getWidth(),
				logo.getHeight());
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
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

}