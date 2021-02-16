// some idea how to print card on the screen after we decide to introduce graphic interface for cards. 
//It requires additional parameter (Texture) in card class.


//package inf112.skeleton.app.cards;
//
//import java.util.Stack;
//
//import com.badlogic.gdx.ApplicationListener;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//
//public class TextureCards implements ApplicationListener{
//	
//	
//	CardDeck startDeck;
//	Stack<Card> cardDeck;
//	Texture cardTex;
//	private SpriteBatch batch;				// #2
//
//	@Override
//	public void create() {
//		CardDeck startDeck = new CardDeck();
//		Stack<Card> cardDeck = startDeck.addCardToDeck();
//		cardTex = (cardDeck.get(1).geImage());	// #3
//		batch = new SpriteBatch();
//		
//	}
//	@Override
//	public void resize(int width, int height) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void render() {
//		batch.begin();					// #5
//		batch.draw(cardTex, 100, 100);		// #6
//		batch.end();	
//		
//	}
//	@Override
//	public void pause() {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void resume() {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void dispose() {
//		// TODO Auto-generated method stub
//		
//	}
//	
//
//}
