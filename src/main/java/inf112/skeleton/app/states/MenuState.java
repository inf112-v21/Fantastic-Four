package inf112.skeleton.app.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MenuState extends State {
	private Texture background;
	private Texture logo;
	private Stage stage;
	private boolean pressed;
	private ImageButton buttonSinglePlayer;
	private Texture buttonSinglePlayerT;
	TextureRegion buttonSinglePlayerTR;
	TextureRegionDrawable buttonSinglePlayerTRD;

	public MenuState(GameStateManager gsm) {
		super(gsm);
		stage = new Stage();

		background = new Texture("background.png");
		logo = new Texture("logo.png");

		buttonSinglePlayerT = new Texture(Gdx.files.internal("singleplayerbutton.png"));
		buttonSinglePlayerTR = new TextureRegion(buttonSinglePlayerT);
		buttonSinglePlayerTRD = new TextureRegionDrawable(buttonSinglePlayerTR);
		buttonSinglePlayer = new ImageButton(buttonSinglePlayerTRD); // Set the button up
		buttonSinglePlayer.setPosition(100, 350);
		buttonSinglePlayer.setSize(300, 100);
		stage.addActor(buttonSinglePlayer);

		buttonSinglePlayer.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				pressed = true;
				System.out.println("Did you click?" + pressed);
			}
		});
	}

	@Override
	public void handleInput() {
		if (pressed == true) {
			gsm.set(new PlayState(gsm));
			dispose();
		}
	}

	@Override
	public void update(float dt) {
		handleInput();
	}

	@Override
	public void render(SpriteBatch sb) {
		Gdx.input.setInputProcessor(stage);
		sb.begin();
		sb.draw(background, 0, 0, 500, 700);
		sb.draw(logo, 50, 500, 400, 150);
		sb.end();
		stage.draw();
		stage.act();
	}

	@Override
	public void dispose() {
		background.dispose();
		logo.dispose();
	}

}
