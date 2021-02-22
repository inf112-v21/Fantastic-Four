package inf112.skeleton.app.assets.cards;

public class ProgramCard extends AbstractCard {

    private String type;
    private int cardValue;

    public ProgramCard(String type, int cardValue) {
        this.type = type;
        this.cardValue = cardValue;
    }

    public int getCardValue() {
        return cardValue;
    }

    @Override
    public String toString() {
        return "ProgramCard{" +
                "type='" + type + '\'' +
                ", cardValue=" + cardValue +
                '}';
    }
}
