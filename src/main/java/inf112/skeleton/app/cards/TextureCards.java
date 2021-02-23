
// some idea how to print card on the screen after we decide to introduce
//graphic interface for cards. //It requires additional parameter (Texture) in
//card class.

package inf112.skeleton.app.cards;

import java.util.ArrayList;
import java.util.Stack;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextureCards implements ApplicationListener {

	CardDeck startDeck;
	Stack<Card> cardDeck;
	Texture cardTex;
	SpriteBatch batch; // #2
	ArrayList<Texture> texturesCards;

	@Override
	public void create() {
		texturesCards = new ArrayList<Texture>();
		CardDeck startDeck = new CardDeck();
		Stack<Card> cardDeck = startDeck.addCardToDeck();
		Stack<Card> picked = CardDeck.dealNumberOfCards(5, cardDeck);
		for (Card c : picked) {
			System.out.println("Card name: " + c.getName());
			System.out.println("Card point: " + c.getPoint());

			texturesCards.add(new Texture(c.getName() + ".png"));
		}
		batch = new SpriteBatch();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void render() {
		batch.begin();
		int x = 10;
		int y = 10;
		for (Texture t : texturesCards) {
			batch.draw(t, x, y);
			x += 190;
			y += 0;
		}
		batch.end();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
