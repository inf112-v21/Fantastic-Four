package inf112.skeleton.app.assets.cards;

public class ProgramCard extends AbstractCard {

    String type;
    int cardValue;

    public ProgramCard(String type, int cardValue) {
        this.type = type;
        this.cardValue = cardValue;
    }

    @Override
    public String toString() {
        return "ProgramCard{" +
                "type='" + type + '\'' +
                ", cardValue=" + cardValue +
                '}';
    }
}
