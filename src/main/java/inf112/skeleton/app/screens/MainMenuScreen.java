package inf112.skeleton.app.screens;

	import com.badlogic.gdx.ApplicationListener;
	import com.badlogic.gdx.Gdx;
	import com.badlogic.gdx.graphics.GL30;
	import com.badlogic.gdx.graphics.Texture;
	import com.badlogic.gdx.graphics.g2d.SpriteBatch;
	import com.badlogic.gdx.scenes.scene2d.Stage;
	import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

	public class MainMenuScreen implements ApplicationListener {
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

	    @Override
	    public void create() {
	       batch = new SpriteBatch();
	       logo = new Texture(Gdx.files.internal("logo.png"));
	       background = new Texture(Gdx.files.internal("background.png"));
	       b1 = new Texture(Gdx.files.internal("button_singleplayer.png"));
	       b2 = new Texture(Gdx.files.internal("button_multiplayer.png"));
	       b3 = new Texture(Gdx.files.internal("button_rules.png"));
	       b4 = new Texture(Gdx.files.internal("button_settings.png"));


	    }

	    @Override
	    public void dispose() {
	        batch.dispose();
	        
	        
	    }

	    @Override
	    public void render() {
	        Gdx.gl.glClearColor(1, 1, 1, 1);
	        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
	        batch.begin();
	        batch.draw(background, 0, 0, 500, 700);
	        batch.draw(logo, 50, 500, 400, 150);
	        batch.draw(b1, 100, 400, 300, 50);
	        batch.draw(b2, 100, 300, 300, 50);
	        batch.draw(b3, 100, 200, 300, 50);
	        batch.draw(b4, 100, 100, 300, 50);
	        batch.end();
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

}
