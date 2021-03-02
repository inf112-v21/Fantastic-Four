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

import inf112.skeleton.app.game.RoboGame;

public class MainMenuScreen implements Screen {
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
	RoboGame w ;
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

		// Font section
		fontLabel = new BitmapFont(Gdx.files.internal("src/main/resources/skin/font-export.fnt"), false);
		fontLabelText = new BitmapFont(Gdx.files.internal("src/main/resources/skin/font-export.fnt"), false);
		fontLabelText.setColor(Color.WHITE);

		labelStyle = new Label.LabelStyle();
		labelStyle.font = fontLabel;

		// Cursor for the text field

		
		imageTextButtonStyle = new ImageTextButtonStyle();
		imageTextButtonStyle.up = skin.newDrawable("panel2", Color.GRAY);
		imageTextButtonStyle.down = skin.newDrawable("panel2"); // Set image for pressed
		imageTextButtonStyle.over = skin.newDrawable("panel2", Color.BLUE); // set image for mouse over
		imageTextButtonStyle.pressedOffsetX = 1;
		imageTextButtonStyle.pressedOffsetY = -1;
		imageTextButtonStyle.font = fontLabel;

		button1 = new ImageTextButton("Single Player", imageTextButtonStyle);
		button1.setX(210);
		button1.setY(260);
		button1.setSize(200, 60);
		button1.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent changeEvent, Actor actor) {
				Gdx.app.exit();
			}
		});

		button2 = new ImageTextButton("Multiplayer", imageTextButtonStyle);
		button2.setX(210);
		button2.setY(200);
		button2.setSize(200, 60);

		button2.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent changeEvent, Actor actor) {
				roboGame.initiateMultiplayer();
			}
		});
		button3 = new ImageTextButton("Rules", imageTextButtonStyle);
		button3.setX(210);
		button3.setY(140);
		button3.setSize(200, 60);

		button3.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent changeEvent, Actor actor) {
				Screen ruleScreen = new RulesScreen(roboGame);
				roboGame.setScreen(ruleScreen);
			}
		});
		button4 = new ImageTextButton("Settings", imageTextButtonStyle);
		button4.setX(210);
		button4.setY(80);
		button4.setSize(200, 60);

		button4.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent changeEvent, Actor actor) {
				Gdx.app.exit();
			}
		});
		button5 = new ImageTextButton("Quit", imageTextButtonStyle);
		button5.setX(210);
		button5.setY(20);
		button5.setSize(200, 60);

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
		batch.draw(background, 0, 0, Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight());
		batch.draw(logo, (Gdx.app.getGraphics().getWidth() / 2) - 200, Gdx.app.getGraphics().getHeight() - 150, 400,
				100);

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