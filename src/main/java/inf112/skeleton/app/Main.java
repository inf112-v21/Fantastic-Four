package inf112.skeleton.app;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import inf112.skeleton.app.game.RoboRally;

public class Main {
	public static void main(String[] args) {
		Lwjgl3ApplicationConfiguration cfg = new Lwjgl3ApplicationConfiguration();
		cfg.setTitle("RoboRally");

		RoboRally game = new RoboRally();

		cfg.setWindowedMode(500, 700);

		new Lwjgl3Application(game, cfg);
	}
}