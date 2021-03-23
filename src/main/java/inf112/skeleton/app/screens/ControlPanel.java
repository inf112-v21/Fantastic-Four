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

import inf112.skeleton.app.game.RoboGame;

public class ControlPanel implements Screen {
	SpriteBatch batch;
	Texture logo;
	Texture background;
	Stage stage;
	RoboGame roboGame;
	ImageTextButton.ImageTextButtonStyle imageLabelButtonStyle;
	ImageTextButton label1, label2;
	BitmapFont fontLabel;
	Skin skin;
	Label.LabelStyle labelStyle;
	ImageTextButton button1;
	ImageTextButton.ImageTextButtonStyle imageTextButtonStyle;
	ScrollPane.ScrollPaneStyle scrollStyle;
	ScrollPane scroll;
	Label label;
	Texture lifeToken, lifeToken2, lifeToken3;


	public ControlPanel(RoboGame roboGame) {
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
		background = new Texture(Gdx.files.internal("background.png"));
		// Font section
		fontLabel = new BitmapFont(Gdx.files.internal("src/main/resources/skin/font-export.fnt"), false);
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
		
		
		lifeToken = new Texture(Gdx.files.internal("greenLife.png"));
		lifeToken2 = new Texture(Gdx.files.internal("greenLife.png"));
		lifeToken3 = new Texture(Gdx.files.internal("greenLife.png"));

		button1 = new ImageTextButton("Return", imageTextButtonStyle);
		button1.setX(250);
		button1.setY(80);
		button1.setSize(150, 60);
		button1.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent changeEvent, Actor actor) {
				Screen mainMenu = new MainMenuScreen(roboGame);
				roboGame.setScreen(mainMenu);

			}
		});

		
		
		
		imageLabelButtonStyle = new ImageTextButtonStyle();
		imageLabelButtonStyle.up = skin.newDrawable("label"); // Set image for pressed
		imageLabelButtonStyle.pressedOffsetX = 1;
		imageLabelButtonStyle.pressedOffsetY = -1;
		imageLabelButtonStyle.font = fontLabel;
		imageLabelButtonStyle.fontColor = Color.WHITE;

		label1 = new ImageTextButton(" Life tokens ", imageLabelButtonStyle);
		label1.setX(Gdx.graphics.getWidth()/2 - label1.getWidth()/2);
		label1.setY(Gdx.graphics.getHeight() - 150);
		label1.setSize(250, 50);

		label2= new ImageTextButton("Damage tokens", imageLabelButtonStyle);
		label2.setX(Gdx.graphics.getWidth()/2 - label2.getWidth()/2);
		label2.setY(Gdx.graphics.getHeight() - 300);
		label2.setSize(250, 50);
		
        Table table = new Table();

		
		scrollStyle = new ScrollPaneStyle();
		scrollStyle.background = skin.newDrawable("panel2", Color.GRAY);
		ScrollPane scroll = new ScrollPane(table, scrollStyle);
        scroll.setScrollingDisabled(true,false);
		
        scroll.setX(50);
        scroll.setY(150);
        scroll.setSize(550, 250);
    
	


		stage = new Stage();
		stage.addActor(button1);
		stage.addActor(label1);
		stage.addActor(label2);


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
		batch.draw(lifeToken, Gdx.graphics.getWidth()/2-120, label1.getY()-150, 270, 200);
		batch.draw(lifeToken2, Gdx.graphics.getWidth()/2, label1.getY()-150, 270, 200);
		batch.draw(lifeToken3, Gdx.graphics.getWidth()/2-220, label1.getY()-150, 270, 200);
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
