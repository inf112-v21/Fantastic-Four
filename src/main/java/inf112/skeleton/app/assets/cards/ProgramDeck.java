package inf112.skeleton.app.assets.cards;

import java.util.ArrayList;
import java.util.List;
import inf112.skeleton.app.assets.cards.ProgramCard.ProgramCardType;

public class ProgramDeck extends AbstractDeck {

    List<ProgramCard> discardedCards;

    public ProgramDeck() {
        super();
        createDeck();
    }

    private List<ProgramCard> createCardType(ProgramCardType ProgramcardType, int quantity, int cardValue, int deltaValue) {
        int currentValue = cardValue;
        List<ProgramCard> cards = new ArrayList<>();
        for (int i = 0; i < quantity; i++)  {
            cards.add(new ProgramCard(ProgramcardType, currentValue));
            currentValue += deltaValue;
        }
        return cards;
    }

    @Override
    public List<ICard> createDeck() {
        // move 1 (18)
        deck.addAll(createCardType(ProgramCardType.MOVE1, 18, 490, 10));
        // move 2 (12)
        deck.addAll(createCardType(ProgramCardType.MOVE2, 12, 670, 10));
        // move 3 (6)
        deck.addAll(createCardType(ProgramCardType.MOVE3, 6, 790, 10));
        // BackUP (6)
        deck.addAll(createCardType(ProgramCardType.BACKUP, 6, 430, 10));
        // rotate right // TODO mismatch between comment and following line
        deck.addAll(createCardType(ProgramCardType.ROTATELEFT, 18, 80, 20));
        // rotate left // TODO mismatch between comment and following line
        deck.addAll(createCardType(ProgramCardType.ROTATERIGHT, 18, 70, 20));
        // u-turn
        deck.addAll(createCardType(ProgramCardType.UTURN, 6, 10, 10));
        return deck;
    }

    @Override
    public void add(ICard card) {
        deck.add((ProgramCard) card);
    }

    @Override
    public List<ICard> draw(int quantity) {
        return null;
    }

    @Override
    public Iterable<? extends ICard> getCards() {
        return null;
    }
}
