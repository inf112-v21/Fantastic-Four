package inf112.skeleton.app.cards;

import com.badlogic.gdx.graphics.Texture;

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
	public int getPoint() {
		return point;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int move() {
		return move;
	}

}
