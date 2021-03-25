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
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane.ScrollPaneStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton.ImageTextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;

import inf112.skeleton.app.game.RoboGame;

public class RulesScreen implements Screen {
	final SpriteBatch batch;
	final Texture background;
	final Stage stage;
	final RoboGame roboGame;
	final ScrollPane.ScrollPaneStyle scrollStyle;
	final Table container;

	public RulesScreen(RoboGame roboGame) {
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

		// Initiatlize key variables
		batch = new SpriteBatch();
		background = new Texture(Gdx.files.internal("background.png"));

		// Font section
		BitmapFont fontLabel = new BitmapFont(Gdx.files.internal("src/main/resources/skin/font-export.fnt"), false);
		Label.LabelStyle labelStyle = new Label.LabelStyle();
		labelStyle.font = fontLabel;

		// Return button
		ImageTextButtonStyle imageTextButtonStyle = new ImageTextButtonStyle();
		imageTextButtonStyle.up = skin.newDrawable("panel2", Color.GRAY);
		imageTextButtonStyle.down = skin.newDrawable("panel2"); // Set image for pressed
		imageTextButtonStyle.over = skin.newDrawable("panel2", Color.BLUE); // set image for mouse over
		imageTextButtonStyle.pressedOffsetX = 1;
		imageTextButtonStyle.pressedOffsetY = -1;
		imageTextButtonStyle.font = fontLabel;
		imageTextButtonStyle.fontColor = Color.WHITE;

		ImageTextButton button1 = new ImageTextButton("Return", imageTextButtonStyle);
		button1.setX(Gdx.app.getGraphics().getWidth() / 2.0f - (button1.getWidth() / 2.0f));
		button1.setY(Gdx.app.getGraphics().getHeight() - 800);
		button1.setSize(150, 60);
		button1.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent changeEvent, Actor actor) {
				Screen mainMenu = new MainMenuScreen(roboGame);
				roboGame.setScreen(mainMenu);

			}
		});

		// Rules label
		ImageTextButtonStyle imageLabelButtonStyle = new ImageTextButtonStyle();
		imageLabelButtonStyle.up = skin.newDrawable("label"); // Set image for pressed
		imageLabelButtonStyle.pressedOffsetX = 1;
		imageLabelButtonStyle.pressedOffsetY = -1;
		imageLabelButtonStyle.font = fontLabel;
		imageLabelButtonStyle.fontColor = Color.WHITE;

		ImageTextButton label1 = new ImageTextButton("Rules", imageLabelButtonStyle);
		label1.setSize(150, 30);
		label1.setX(Gdx.app.getGraphics().getWidth() / 2.0f - (label1.getWidth() / 2));
		label1.setY(Gdx.app.getGraphics().getHeight() - 200);

		
		stage = new Stage();

		// Scroll field
		container = new Table();
		stage.addActor(container);
		container.setFillParent(true);
		Table table = new Table();

		String text = "			SUMMARY OF PLAY" + "\n"+ "\n"
				+ "Each turn, you will draw random Program cards (instructions for your robot)." + "\n" + "\n"
						+ "Secretly, choose five cards to plan your robot's moves." + "\n" +  "\n"
				+ "Goal: be the first to touch all flags in order." + "\n"  + "\n"
				+ "Robots can get in each other's way, push each other off the board, and shoot each other with lasers.";
				
		
		Label label = new Label(text, labelStyle);
		label.setAlignment(Align.left);
		label.setWrap(true);
		table.add(label).width(Gdx.graphics.getWidth() / 2.0f);
		scrollStyle = new ScrollPaneStyle();
		scrollStyle.background = skin.newDrawable("panel2", Color.GRAY);
		ScrollPane scroll = new ScrollPane(table, scrollStyle);
		scroll.setScrollingDisabled(true, false);
		scroll.setSize(200, 400);
		table.setSize(200, 200);

		// Add elements to stage
		stage.addActor(button1);
		stage.addActor(label1);
		
		

		container.add(scroll).fill();
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