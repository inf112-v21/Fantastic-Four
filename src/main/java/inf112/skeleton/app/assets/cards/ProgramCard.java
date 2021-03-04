package inf112.skeleton.app.assets.cards;

public class ProgramCard extends AbstractCard {

    private ProgramCardType programCardType;
    private int priorityNumber;

    public ProgramCard(ProgramCard.ProgramCardType programCardType, int priorityNumber) {
        this.programCardType = programCardType;
        this.priorityNumber = priorityNumber;
    }

    enum ProgramCardType {
        MOVE1,
        MOVE2,
        MOVE3,
        BACKUP,
        ROTATELEFT,
        ROTATERIGHT,
        UTURN
    }

    public int getPriorityNumber() {
        return priorityNumber;
    }

    @Override
    public String toString() {
        return "ProgramCard{" +
                "type='" + programCardType + '\'' +
                ", cardValue=" + priorityNumber +
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
