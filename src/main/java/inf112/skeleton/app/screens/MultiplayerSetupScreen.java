package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import inf112.skeleton.app.game.RoboGame;

public class MultiplayerSetupScreen implements Screen {
    SpriteBatch batch;
    Texture logo;
    Texture background;
    Texture b1;
    Texture b2;
    Texture b3;
    Texture b4;
    Texture menu;
    ImageButton b;
    Stage stage;
    RoboGame roboGame;
    private BitmapFont font;
    private TextField ip;
    private TextField name;

    public MultiplayerSetupScreen(RoboGame roboGame) {
        this.roboGame = roboGame;

        // Initiate key variables
        batch = new SpriteBatch();
        logo = new Texture(Gdx.files.internal("logo.png"));
        background = new Texture(Gdx.files.internal("background.png"));
        font = new BitmapFont();
        font.setColor(Color.ORANGE);
        ip = new TextField("IP");
        name = new TextField("Name");
    }

    @Override
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0, 500, 700);
        batch.draw(logo, 50, 500, 400, 150);
        font.draw(batch, "IP adress:", 200, 200);
        ip.draw(batch, 0);
        name.draw(batch, 10);
        batch.end();
    }

    @Override
    public void show() {

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
