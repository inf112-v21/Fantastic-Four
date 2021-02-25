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

	@Override
	public int getPoint() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int move() {
		// TODO Auto-generated method stub
		return 0;
	}
}
