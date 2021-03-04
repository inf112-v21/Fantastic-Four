package inf112.skeleton.app.assets.cards;

public class ProgramCard extends AbstractCard {

    private ProgramcardType ProgramcardType;
    private int cardValue;

    public ProgramCard(ProgramcardType ProgramcardType, int cardValue) {
        this.ProgramcardType = ProgramcardType;
        this.cardValue = cardValue;
    }

    enum ProgramcardType {
        MOVE1,
        MOVE2,
        MOVE3,
        BACKUP,
        ROTATELEFT,
        ROTATERIGHT,
        UTURN
    }

    public int getCardValue() {
        return cardValue;
    }

    @Override
    public String toString() {
        return "ProgramCard{" +
                "type='" + ProgramcardType + '\'' +
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
