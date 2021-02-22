package inf112.skeleton.app.assets.cards;

import java.util.Collections;
import java.util.Stack;

public abstract class AbstractDeck implements IDeck {
    Stack<ICard> deck;

    public AbstractDeck() {
        this.deck = new Stack<>();
    }

    public void add(ICard card) {
        deck.add(card);
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Stack<ICard> draw(int quantity) {
        Stack<ICard> result = new Stack<>();
        for (int i = 0; i < quantity; i++) result.add(deck.pop());
        return result;
    }

    @Override
    public Iterable<? extends ICard> getCards() {
        return this.deck;
    }
}
