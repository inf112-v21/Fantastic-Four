package inf112.skeleton.app.assets.cards;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class CardUI {
	static Texture cardTexture;
	static TextureRegion cardTextureRegion;
	static TextureRegionDrawable cardTextureDrawable;
	static ImageButton texture;

	public static ImageButton createTextureButton(String name) {
		cardTexture = new Texture(Gdx.files.internal("src/main/resources/" + name + ".png"));
		cardTextureRegion = new TextureRegion(cardTexture);
		cardTextureDrawable = new TextureRegionDrawable(cardTextureRegion);
		texture = new ImageButton(cardTextureDrawable);
		texture.setSize(300, 200);

		return texture;
	}
}
