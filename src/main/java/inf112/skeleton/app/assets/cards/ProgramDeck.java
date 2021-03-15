package inf112.skeleton.app.assets.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import inf112.skeleton.app.assets.cards.ProgramCard.ProgramCardType;

public class ProgramDeck {
    public List<ProgramCard> deck;

    public ProgramDeck() {
        deck = new ArrayList<>();
    }

    public List<ProgramCard> createDeck() {
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
        Collections.shuffle(deck);
        return deck;
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

    public void add(ProgramCard card) {
        deck.add((ProgramCard) card);
    }

    public List<ProgramCard> draw(int quantity) {
        ArrayList<ProgramCard> cards = new ArrayList<>();
        for (int card = 0; card < quantity; card++) {
            cards.add(deck.remove(0));
        }
        return cards;
    }

    public Iterable<? extends ProgramCard> getCards() {
        return deck;
    }
}
