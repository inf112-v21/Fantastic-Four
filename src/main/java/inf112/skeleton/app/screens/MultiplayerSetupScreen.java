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
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import inf112.skeleton.app.game.RoboGame;

public class MultiplayerSetupScreen implements Screen {
	SpriteBatch batch;
	Texture logo;
	Texture background;
	Stage stage;
	RoboGame roboGame;

	private BitmapFont fontLabel, fontLabelText;
	private TextField ipTextField;
	private TextField nameTextField;
	private TextField.TextFieldStyle textFieldStyle;
	private Skin skin;
	private Label.LabelStyle labelStyle;
	private Label oneCharSizeCalibrationThrowAway;
	private ImageTextButton okButton, cancelButton, hostButton;
	private ImageTextButton.ImageTextButtonStyle imageTextButtonStyle;

	private ImageTextButton.ImageTextButtonStyle imageLabelButtonStyle;
	private ImageTextButton nameLabel, ipLabel, hostLabel;


	public MultiplayerSetupScreen(RoboGame roboGame) {
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

		logo = new Texture(Gdx.files.internal("src/main/resources/logo2.png"));
		background = new Texture(Gdx.files.internal("background.png"));

		// Font section
		fontLabel = new BitmapFont(Gdx.files.internal("src/main/resources/skin/font-export.fnt"), false);
		fontLabelText = new BitmapFont(Gdx.files.internal("src/main/resources/skin/font-export.fnt"), false);
		fontLabelText.setColor(Color.WHITE);

		labelStyle = new Label.LabelStyle();
		labelStyle.font = fontLabel;

		// Cursor for the text field
		oneCharSizeCalibrationThrowAway = new Label("|", labelStyle);
		Pixmap cursorColor = new Pixmap((int) oneCharSizeCalibrationThrowAway.getWidth(),
				(int) oneCharSizeCalibrationThrowAway.getHeight(), Pixmap.Format.RGB888);
		cursorColor.setColor(Color.GRAY);
		cursorColor.fill();

		// === Styles ===
		imageLabelButtonStyle = new ImageTextButtonStyle();
		imageLabelButtonStyle.up = skin.newDrawable("label"); // Set image for pressed
		imageLabelButtonStyle.pressedOffsetX = 1;
		imageLabelButtonStyle.pressedOffsetY = -1;
		imageLabelButtonStyle.font = fontLabel;
		imageLabelButtonStyle.fontColor = Color.WHITE;

		textFieldStyle = new TextField.TextFieldStyle();
		textFieldStyle.font = fontLabel;
		textFieldStyle.fontColor = new Color(Color.GRAY);
		textFieldStyle.focusedFontColor = new Color(Color.DARK_GRAY);
		textFieldStyle.cursor = new Image(new Texture(cursorColor)).getDrawable();
		textFieldStyle.background = skin.getDrawable("textfield");

		imageTextButtonStyle = new ImageTextButtonStyle();
		imageTextButtonStyle.up = skin.newDrawable("panel2", Color.GRAY);
		imageTextButtonStyle.down = skin.newDrawable("panel2"); // Set image for pressed
		imageTextButtonStyle.over = skin.newDrawable("panel2", Color.BLUE); // set image for mouse over
		imageTextButtonStyle.pressedOffsetX = 1;
		imageTextButtonStyle.pressedOffsetY = -1;
		imageTextButtonStyle.font = fontLabel;
		imageTextButtonStyle.fontColor = Color.WHITE;

		// === Elements creation ===
		nameTextField = new TextField("Enter name", textFieldStyle);
		nameTextField.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				nameTextField.setText("");
			}
		});
		nameTextField.setSize(300, 50);
		nameTextField.setX(Gdx.graphics.getWidth() / 2);
		nameTextField.setY(Gdx.graphics.getHeight() / 2);

		ipTextField = new TextField("Enter IP", textFieldStyle);
		ipTextField.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				ipTextField.setText("");
			}
		});
		ipTextField.setSize(300, 50);
		ipTextField.setX(Gdx.graphics.getWidth() / 2);
		ipTextField.setY(Gdx.graphics.getHeight() / 2 + nameTextField.getHeight());

		nameLabel = new ImageTextButton("Nickname", imageLabelButtonStyle);
		nameLabel.setSize(150, 30);
		nameLabel.setX(Gdx.graphics.getWidth() / 2 - nameTextField.getWidth());
		nameLabel.setY(Gdx.graphics.getHeight() / 2);

		ipLabel = new ImageTextButton("Enter IP", imageLabelButtonStyle);
		ipLabel.setSize(150, 30);
		ipLabel.setX(Gdx.graphics.getWidth() / 2 - ipTextField.getWidth());
		ipLabel.setY(Gdx.graphics.getHeight() / 2 + nameTextField.getHeight());

		cancelButton = new ImageTextButton("Cancel", imageTextButtonStyle);
		cancelButton.setSize(150, 60);
		cancelButton.setX(Gdx.graphics.getWidth() / 2);
		cancelButton.setY(Gdx.graphics.getHeight() / 2 -
				nameTextField.getHeight() -
				ipTextField.getHeight());
		cancelButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent changeEvent, Actor actor) {
				Screen mainMenu = new MainMenuScreen(roboGame);
				roboGame.setScreen(mainMenu);
			}
		});

		okButton = new ImageTextButton("OK", imageTextButtonStyle);
		okButton.setSize(150, 60);
		okButton.setX(Gdx.graphics.getWidth() / 2- cancelButton.getWidth());
		okButton.setY(Gdx.graphics.getHeight() / 2 -
				nameTextField.getHeight() -
				ipTextField.getHeight());
		okButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent changeEvent, Actor actor) {
				roboGame.connectToHost(ipTextField.getText(), nameTextField.getText());
			}
		});


		hostButton = new ImageTextButton("Host", imageTextButtonStyle);
		hostButton.setSize(150, 60);
		hostButton.setX(Gdx.graphics.getWidth() / 2 + cancelButton.getWidth());
		hostButton.setY(Gdx.graphics.getHeight() / 2 -
				nameTextField.getHeight() -
				ipTextField.getHeight());
		hostButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent changeEvent, Actor actor) {
				roboGame.startHost(nameTextField.getText());
			}
		});

		stage = new Stage();
		stage.addActor(okButton);
		stage.addActor(cancelButton);
		stage.addActor(ipTextField);
		stage.addActor(nameTextField);
		stage.addActor(nameLabel);
		stage.addActor(ipLabel);
		stage.addActor(hostButton);
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