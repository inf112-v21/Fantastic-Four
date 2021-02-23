package inf112.skeleton.app.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import inf112.skeleton.app.game.RoboGame;

public class MultiplayerSetupScreen implements Screen {
    SpriteBatch batch;
    Texture logo;
    Texture background;
    Stage stage;
    RoboGame roboGame;
    private BitmapFont font;
    private TextField ipTextField;
    private TextField nameTextField;
    private TextField.TextFieldStyle textFieldStyle;
    private TextButton okButton, cancelButton;
    private TextButton.TextButtonStyle textButtonStyle;
    private Skin skin;

    public MultiplayerSetupScreen(RoboGame roboGame) {
        this.roboGame = roboGame;

        // Initiate key variables
        batch = new SpriteBatch();
        logo = new Texture(Gdx.files.internal("logo.png"));
        background = new Texture(Gdx.files.internal("background.png"));

        font = new BitmapFont();
        font.setColor(Color.ORANGE);

        textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.font = new BitmapFont();
        textFieldStyle.font.setColor(Color.WHITE);
        textFieldStyle.messageFontColor = new Color(Color.GRAY);
        textFieldStyle.fontColor = new Color(Color.RED);

        ipTextField = new TextField("Enter IP", textFieldStyle);
        ipTextField.setX(200);
        ipTextField.setY(200);

        nameTextField = new TextField("Enter name", textFieldStyle);
        nameTextField.setX(200);
        nameTextField.setY(250);

        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = font;


        okButton = new TextButton("OK", textButtonStyle);
        okButton.setX(100);
        okButton.setY(100);
        okButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                System.out.println(nameTextField.getText() + " has IP " + ipTextField.getText());
            }
        });

        cancelButton = new TextButton("Cancel", textButtonStyle);
        cancelButton.setX(150);
        cancelButton.setY(100);
        cancelButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent changeEvent, Actor actor) {
                Gdx.app.exit();
            }
        });
        skin = new Skin();

        stage = new Stage();
        stage.addActor(ipTextField);
        stage.addActor(nameTextField);
        stage.addActor(okButton);
        stage.addActor(cancelButton);

        Gdx.input.setInputProcessor(stage);
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
        batch.draw(background, 0, 0, Gdx.app.getGraphics().getWidth(), Gdx.app.getGraphics().getHeight());
        batch.draw(logo, (Gdx.app.getGraphics().getWidth() / 2) - 200, Gdx.app.getGraphics().getHeight() - 200, 400, 150);
        font.draw(batch, "IP address:", 100, 220);
        font.draw(batch, "Nickname:", 100, 270);
        batch.end();

        stage.draw();
        stage.act();
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
