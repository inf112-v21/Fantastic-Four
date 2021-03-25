package inf112.skeleton.app.assets.cards;

public class ProgramCard {

    private final ProgramCardType programCardType;
    private final int priorityNumber;

    public ProgramCard(ProgramCard.ProgramCardType programCardType, int priorityNumber) {
        this.programCardType = programCardType;
        this.priorityNumber = priorityNumber;
    }

    public enum ProgramCardType {
        MOVE1,
        MOVE2,
        MOVE3,
        BACKUP,
        ROTATELEFT,
        ROTATERIGHT,
        UTURN
    }

    /**
     *
     * @param programCardType Program card type
     * @return [Number of steps, degrees to turn]
     */
    public static int[] interpretType(ProgramCardType programCardType) {
        if (programCardType.equals(ProgramCardType.MOVE1)) return new int[]{1, 0};
        if (programCardType.equals(ProgramCardType.MOVE2)) return new int[]{2, 0};
        if (programCardType.equals(ProgramCardType.MOVE3)) return new int[]{3, 0};
        if (programCardType.equals(ProgramCardType.BACKUP)) return new int[]{-1, 0};
        if (programCardType.equals(ProgramCardType.ROTATELEFT)) return new int[]{0, -1};
        if (programCardType.equals(ProgramCardType.ROTATERIGHT)) return new int[]{0, 1};
        if (programCardType.equals(ProgramCardType.UTURN)) return new int[]{0, 2};
        return new int[]{0, 0};
    }

    public int getPriorityNumber() {
        return priorityNumber;
    }

    public String toString() {
        return "ProgramCard{" +
                "type='" + programCardType + '\'' +
                ", cardValue=" + priorityNumber +
                '}';
    }

	public ProgramCardType getProgramCardType() {
        return programCardType;
    }
}
