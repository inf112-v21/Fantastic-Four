package inf112.skeleton.app.assets.cards;

import java.util.List;
import java.util.Stack;

public abstract class AbstractDeck implements IDeck {
	public List<ICard> deck;

	public AbstractDeck() {
		this.deck = new Stack<>();
	}

	public void add(ICard card) {
		deck.add(card);
	}

	@Override
	public Iterable<? extends ICard> getCards() {
		return this.deck;
	}
}
