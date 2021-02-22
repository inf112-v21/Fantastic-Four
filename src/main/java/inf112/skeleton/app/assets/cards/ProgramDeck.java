package inf112.skeleton.app.assets.cards;

import java.util.Stack;

public class ProgramDeck extends AbstractDeck {

    //static Stack

    Stack<ProgramCard> discardedCards;
    Stack<ProgramCard> cardsInPlay;

    public ProgramDeck() {
    }

    @Override
    public void add(ICard card) {

    }

    @Override
    public Iterable<? extends ICard> getCards() {
        return null;
    }
}
