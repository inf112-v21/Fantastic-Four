package inf112.skeleton.app.cards;

import com.badlogic.gdx.graphics.Texture;

public class Card implements ICard {

	
	private String name;
	private int point;
	//Texture image;

	public Card(String name, int point) {
		this.point = point;
		this.name = name;
		//this.image = image;

	}

	//Not used for now
//	public Texture getImage() {
//		return image;
//	}

	@Override
	public int getPoint() {
		return point;
	}

	@Override
	public String getDescription() {
		return name;
	}
	

}
