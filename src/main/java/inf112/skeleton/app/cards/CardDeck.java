package inf112.skeleton.app.cards;

import java.util.Stack;

// Description: we have 2 cards types: move and rotate.
// in () I wrote how many do we have in deck (Robo Rally - Rules.pdf s.6.
// card value is different for every card. I used interval and start value from Daniel rule book, 
// The last method deal given number of cards from deck (we have to pay attention to thin after player dies).
// Print only in console; start from `MainCards.java`.

public class CardDeck {

	Stack<Card> deck = new Stack<>();
	static Stack<Card> dealtDeck = new Stack<>();

	public Stack<Card> addCardToDeck() {

		deck = new Stack<>();
		// move 1 (18)

		int cardValue = 490;
		for (int i = 1; i <= 18; i++) {
			deck.add(new Card("Move 1", cardValue));
			cardValue += 10;
		}

		// move 2 (12)
		cardValue = 670;
		for (int i = 1; i <= 12; i++) {
			deck.add(new Card("Move 2", cardValue));
			cardValue += 10;

		}

		// move 3 (6)
		cardValue = 790;
		for (int i = 1; i <= 6; i++) {
			deck.add(new Card("Move 3", cardValue));
			cardValue += 10;

		}

		// BackUP (6)
		cardValue = 430;
		for (int i = 1; i <= 6; i++) {
			deck.add(new Card("BackUp", cardValue));
			cardValue += 10;

		}

		// ROTATE CARDS
		// rotate Right
		cardValue = 80;
		for (int i = 1; i <= 18; i++) {
			deck.add(new Card("Rotate left", cardValue));
			cardValue += 20;

		}

		// rotate left
		cardValue = 70;
		for (int i = 1; i <= 18; i++) {
			deck.add(new Card("rotate right", cardValue));
			cardValue += 20;

		}
		cardValue = 10;
		for (int i = 1; i <= 6; i++) {
			deck.add(new Card("u-turn", cardValue));
			cardValue += 10;

		}
		// u-turn
		return deck;

	}

	public static Stack<Card> dealNumberOfCards(int numberofCards, Stack<Card> deck) {
		for (int i = 1; i <= numberofCards; i++) {
			System.out.println(deck.get(i).getDescription());
			dealtDeck.add(deck.pop());
		}
		return dealtDeck;
	}

}
