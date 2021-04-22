package inf112.skeleton.app.assets.cards;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import inf112.skeleton.app.assets.cards.ProgramCard.ProgramCardType;

class ProgramDeckTest {

	final ProgramDeck cardDeck = new ProgramDeck();
	final List<ProgramCard> testDeck = cardDeck.createDeck();

	@Test
	void testSizeOfDeck() {
		System.out.println(testDeck.size());
		assertEquals(84, testDeck.size());
	}

	@Test
	void testSizeOfDeckAfterDraw() {
		List<ProgramCard> testDrawDeck = cardDeck.draw(5);
		assertEquals(79, testDeck.size());
		assertEquals(5, testDrawDeck.size());
	}

	@Test
	void testCreateDeckCheckIfContainsAllProgramCardType() {
		ArrayList<ProgramCardType> types = new ArrayList<ProgramCard.ProgramCardType>();
		for (ProgramCard card : testDeck) {
			types.add(card.getProgramCardType());
		}
		assertTrue(types.contains(ProgramCardType.MOVE1));
		assertTrue(types.contains(ProgramCardType.MOVE2));
		assertTrue(types.contains(ProgramCardType.MOVE3));
		assertTrue(types.contains(ProgramCardType.BACKUP));
		assertTrue(types.contains(ProgramCardType.ROTATELEFT));
		assertTrue(types.contains(ProgramCardType.ROTATERIGHT));
		assertTrue(types.contains(ProgramCardType.UTURN));

	}

	@Test
	void testCardPriorityNumberIfInDefinedRange() {
		ArrayList<ProgramCardType> types = new ArrayList<ProgramCard.ProgramCardType>();
		for (ProgramCard card : testDeck) {
			if (card.getProgramCardType().equals(ProgramCardType.MOVE1)) {
				assertTrue(card.getPriorityNumber() <= 660 && card.getPriorityNumber() >= 490);
			} else if (card.getProgramCardType().equals(ProgramCardType.MOVE2)) {
				assertTrue(card.getPriorityNumber() <= 780 && card.getPriorityNumber() >= 670);
			} else if (card.getProgramCardType().equals(ProgramCardType.MOVE3)) {
				assertTrue(card.getPriorityNumber() <= 840 && card.getPriorityNumber() >= 790);
			} else if (card.getProgramCardType().equals(ProgramCardType.BACKUP)) {
				assertTrue(card.getPriorityNumber() <= 480 && card.getPriorityNumber() >= 430);
			} else if (card.getProgramCardType().equals(ProgramCardType.ROTATELEFT)) {
				assertTrue(card.getPriorityNumber() <= 420 && card.getPriorityNumber() >= 80);
			} else if (card.getProgramCardType().equals(ProgramCardType.ROTATERIGHT)) {
				assertTrue(card.getPriorityNumber() <= 410 && card.getPriorityNumber() >= 70);
			} else if (card.getProgramCardType().equals(ProgramCardType.UTURN)) {
				assertTrue(card.getPriorityNumber() <= 60 && card.getPriorityNumber() >= 10);

			}
		}
	}

	@Test
	void testCardNumberOfEachType() {
		ArrayList<ProgramCard> sizeMove1 = new ArrayList<ProgramCard>();
		ArrayList<ProgramCard> sizeMove2 = new ArrayList<ProgramCard>();
		ArrayList<ProgramCard> sizeMove3 = new ArrayList<ProgramCard>();
		ArrayList<ProgramCard> sizeBackup = new ArrayList<ProgramCard>();
		ArrayList<ProgramCard> sizeRotateleft = new ArrayList<ProgramCard>();
		ArrayList<ProgramCard> sizeRotateRight = new ArrayList<ProgramCard>();
		ArrayList<ProgramCard> sizeUturn = new ArrayList<ProgramCard>();

		for (ProgramCard card : testDeck) {
			if (card.getProgramCardType().equals(ProgramCardType.MOVE1)) {
				sizeMove1.add(card);
			} else if (card.getProgramCardType().equals(ProgramCardType.MOVE2)) {
				sizeMove2.add(card);
			} else if (card.getProgramCardType().equals(ProgramCardType.MOVE3)) {
				sizeMove3.add(card);
			} else if (card.getProgramCardType().equals(ProgramCardType.BACKUP)) {
				sizeBackup.add(card);
			} else if (card.getProgramCardType().equals(ProgramCardType.ROTATELEFT)) {
				sizeRotateleft.add(card);
			} else if (card.getProgramCardType().equals(ProgramCardType.ROTATERIGHT)) {
				sizeRotateRight.add(card);
			} else if (card.getProgramCardType().equals(ProgramCardType.UTURN)) {
				sizeUturn.add(card);

			}
		}
		assertEquals(18, sizeMove1.size());
		assertEquals(12, sizeMove2.size());
		assertEquals(6, sizeMove3.size());
		assertEquals(6, sizeBackup.size());
		assertEquals(18, sizeRotateleft.size());
		assertEquals(18, sizeRotateRight.size());
		assertEquals(6, sizeUturn.size());

	}

}