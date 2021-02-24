package inf112.skeleton.app.assets.cards;

import java.util.Collections;
import java.util.Stack;

public abstract class AbstractDeck implements IDeck {
	public Stack<ICard> deck;
	public Stack<ICard> cardDeck;
	int size;

	public AbstractDeck() {
		this.cardDeck = new Stack<>();
		this.deck = new Stack<>();
	}

	public Stack<ICard> createDeck() {
		size = 84;

		for (int i = 0; i < size; i++) {
			if (i <= 18) {
				cardDeck.add(new Card(1, "move1", 490 + (10 * i)));
			} else if (i <= 30) {
				cardDeck.add(new Card(2, "move2", 670 + (10 * (i - 18))));
			} else if (i <= 36) {
				cardDeck.add(new Card(3, "move3", 670 + (10 * (i - 30))));
			} else if (i <= 42) {
				cardDeck.add(new Card(-1, "backup", 430 + (10 * (i - 36))));
			} else if (i <= 60) {
				cardDeck.add(new Card(0, "rotateRight", 80 + (20 * (i - 42))));
			} else if (i <= 78) {
				cardDeck.add(new Card(0, "rotateLeft", 70 + (20 * (i - 60))));
			} else if (i <= 84) {
				cardDeck.add(new Card(0, "uTurn", 10 + (10 * (i - 78))));
			}
		}
		Collections.shuffle(cardDeck);
		return cardDeck;

	}

	public void add(ICard card) {
		deck.add(card);
	}

	public Stack<ICard> drawCardsFromDeck(int numberofCards, Stack<ICard> deck) {
		Stack<ICard> selected = new Stack<>();
		if (numberofCards >= deck.size()) {
			throw new IndexOutOfBoundsException(
					"Don't have enough cards. I have only " + deck.size() + " you want " + numberofCards);
		}

		for (int i = 1; i <= numberofCards; i++) {
			selected.add(deck.pop());

		}
		return selected;
	}

	public Stack<ICard> draw(int quantity) {
		Stack<ICard> result = new Stack<>();
		for (int i = 0; i < quantity; i++)
			result.add(cardDeck.pop());
		return result;
	}

	public void shuffle() {
		Collections.shuffle(cardDeck);
	}

	@Override
	public Iterable<? extends ICard> getCards() {
		return this.deck;
	}
}
