package inf112.skeleton.app.assets.cards;

public class Card implements ICard {

	private String name;
	private int point;
	int move;

	public Card(int move, String name, int point) {
		this.move = move;
		this.name = name;
		this.point = point;

	}

	@Override
	public int getPriorityNumber() {
		return point;
	}

	@Override
	public String getCardName() {
		return name;
	}

}
