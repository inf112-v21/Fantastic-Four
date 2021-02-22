package inf112.skeleton.app.assets.cards;

import java.util.Stack;

public class ProgramDeck extends AbstractDeck {

    Stack<ProgramCard> discardedCards;

    public ProgramDeck() {
        super();
        createDeck();
    }

    private Stack<ProgramCard> create(String type, int quantity, int cardValue, int deltaValue) {
        Stack<ProgramCard> cards = new Stack<>();
        for (int i = 0; i < quantity; i++)  {
            cards.add(new ProgramCard(type, cardValue));
            cardValue += deltaValue;
        }
        return cards;
    }

    public Stack<ICard> createDeck() {
        // move 1 (18)
        deck.addAll(create("Move 1", 18, 490, 10));
        // move 2 (12)
        deck.addAll(create("Move 2", 12, 670, 10));
        // move 3 (6)
        deck.addAll(create("Move 3", 6, 790, 10));
        // BackUP (6)
        deck.addAll(create("BackUp", 6, 430, 10));
        // rotate right // TODO mismatch between comment and following line
        deck.addAll(create("Rotate left", 18, 80, 20));
        // rotate left // TODO mismatch between comment and following line
        deck.addAll(create("Rotate right", 18, 70, 20));
        // u-turn
        deck.addAll(create("U-turn", 6, 10, 10));
        return deck;
    }
}
