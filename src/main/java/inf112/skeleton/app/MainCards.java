package inf112.skeleton.app;

import inf112.skeleton.app.cards.*;
import java.util.Collections;
import java.util.Stack;

public class MainCards {
	public static void main(String[] args) {
		CardDeck startDeck = new CardDeck();
		Stack<Card> cardDeck = startDeck.addCardToDeck();
		System.out.println("Initial size of deck" + " " + cardDeck.size() + " ");
		
		//Shuffle cards
		Collections.shuffle(cardDeck);
		
		
		//Check 
		Stack<Card> deltCard = CardDeck.dealNumberOfCards(9, cardDeck);
		
		//Check dealt cards points + name
		System.out.println("All dealt cards with name and prioity: ");
		for (Card card : deltCard) {
			System.out.println(card.getDescription() + " " + card.getPoint());
		}

		// Check if size of given deck is as given
		// Check startDeck - number of cards to deal: here 84 - 9
		System.out.println("Card deck size after deal specific number of cards (here (84-9))" + " " + cardDeck.size());

	}

}