package inf112.skeleton.app.assets.cards;

import java.util.Stack;

public interface IDeck {
    /**
     * Create a deck of the appropriate ICards
     */
    Stack<ICard> createDeck();

    /**
     * Add a single card to this deck
     * @param card The card to be added
     */
    void add(ICard card);

    /**
     * Shuffle this deck.
     */
    void shuffle();

    /**
     * Draw 1 or more cards from this deck
     */
    Stack<ICard> draw(int quantity);

    /**
     *
     * @return An iterable version of this deck
     */
    Iterable<? extends ICard> getCards();
}
