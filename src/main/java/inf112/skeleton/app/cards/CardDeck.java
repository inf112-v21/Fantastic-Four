package inf112.skeleton.app.cards;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Stack;

import com.badlogic.gdx.Input;

// Description: we have 2 cards types: move and rotate.
// in () I wrote how many do we have in deck (Robo Rally - Rules.pdf s.6.
// card value is different for every card. I used interval and start value from Daniel rule book, 
// The last method deal given number of cards from deck (we have to pay attention to thin after player dies).
// Print only in console; start from `MainCards.java`.

public class CardDeck {

	Stack<Card> deck = new Stack<>();
	static Stack<Card> dealtDeck = new Stack<>();
	int size = 84;

	public Stack<Card> addCardToDeck() {

		deck = new Stack<>();

		// move?; name; initial point/ cards from one group has value interval defined
		// in rule book

		for (int i = 0; i < size; i++) {
			if (i <= 18) {
				deck.add(new Card(1, "move1", 490 + (10 * i)));
			} else if (i <= 30) {
				deck.add(new Card(2, "move2", 670 + (10 * (i - 18))));
			} else if (i <= 36) {
				deck.add(new Card(3, "move3", 670 + (10 * (i - 30))));
			} else if (i <= 42) {
				deck.add(new Card(-1, "backup", 430 + (10 * (i - 36))));
			} else if (i <= 60) {
				deck.add(new Card(0, "rotateRight", 80 + (20 * (i - 42))));
			} else if (i <= 78) {
				deck.add(new Card(0, "rotateLeft", 70 + (20 * (i - 60))));
			} else if (i <= 84) {
				deck.add(new Card(0, "uTurn", 10 + (10 * (i - 78))));
			}
		}

		// u-turn
		Collections.shuffle(deck);
		return deck;

	}

	public static Stack<Card> dealNumberOfCards(int numberofCards, Stack<Card> deck) {
		if (numberofCards >= deck.size()) {
			throw new IndexOutOfBoundsException(
					"Don't have enough cards. I have only " + deck.size() + " you want " + numberofCards);
		}

		for (int i = 1; i <= numberofCards; i++) {
			dealtDeck.add(deck.pop());

		}
		return dealtDeck;
	}

}
