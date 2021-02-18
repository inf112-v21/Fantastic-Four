package inf112.skeleton.app.assets.cards;

public interface IDeck {
    /**
     * Add a single card to this deck
     * @param card The card to be added
     */
    void add(ICard card);

    /**
     *
     * @return An iterable version of this deck
     */
    Iterable<? extends ICard> getCards();
}
