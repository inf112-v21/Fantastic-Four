package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;

import inf112.skeleton.app.game.RoboGame;

public class GameOverScreen implements Screen {
	SpriteBatch batch;
	Texture logo;
	Texture background;
	Stage stage;
	RoboGame roboGame;

	private BitmapFont fontLabel, fontLabelBig;
	private TextField ipTextField;
	private TextField nameTextField;
	private Skin skin;
	private Label.LabelStyle labelStyle;
	private ImageTextButton button1, button2;
	private ImageTextButton.ImageTextButtonStyle imageTextButtonStyle;

	public GameOverScreen(RoboGame roboGame) {
		this.roboGame = roboGame;

		skin = new Skin();

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

		logo = new Texture(Gdx.files.internal("logo.png"));
		background = new Texture(Gdx.files.internal("background.png"));

		// Font section
		fontLabel = new BitmapFont(Gdx.files.internal("src/main/resources/skin/font-export.fnt"), false);
		fontLabelBig = new BitmapFont(Gdx.files.internal("src/main/resources/skin/font-title-export.fnt"), false);

		labelStyle = new Label.LabelStyle();
		labelStyle.font = fontLabel;

		imageTextButtonStyle = new ImageTextButtonStyle();
		imageTextButtonStyle.up = skin.newDrawable("panel2", Color.GRAY);
		imageTextButtonStyle.down = skin.newDrawable("panel2"); // Set image for pressed
		imageTextButtonStyle.over = skin.newDrawable("panel2", Color.BLUE); // set image for mouse over
		imageTextButtonStyle.pressedOffsetX = 1;
		imageTextButtonStyle.pressedOffsetY = -1;
		imageTextButtonStyle.font = fontLabel;
		imageTextButtonStyle.fontColor = Color.WHITE;
		
		button1 = new ImageTextButton("OK", imageTextButtonStyle);
		button1.setX(170);
		button1.setY(80);
		button1.setSize(150, 60);
		button1.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent changeEvent, Actor actor) {
				Gdx.app.exit();

			}
		});

		button2 = new ImageTextButton("Cancel", imageTextButtonStyle);
		button2.setX(320);
		button2.setY(80);
		button2.setSize(150, 60);

		button2.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent changeEvent, Actor actor) {
				Screen mainMenu = new MainMenuScreen(roboGame);
				roboGame.setScreen(mainMenu);

			}
		});

		stage = new Stage();
		stage.addActor(button1);
		stage.addActor(button2);

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
		batch.draw(logo, (Gdx.app.getGraphics().getWidth() / 2) - 200, Gdx.app.getGraphics().getHeight() - 200, 400,
				150);
		fontLabelBig.draw(batch, "GAME OVER", 150, 250);

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
