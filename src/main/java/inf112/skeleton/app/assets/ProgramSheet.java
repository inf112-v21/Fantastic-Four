package inf112.skeleton.app.assets;

import inf112.skeleton.app.assets.cards.ICard;
import inf112.skeleton.app.assets.cards.ProgramCard;
import inf112.skeleton.app.assets.cards.ProgramDeck;

import java.util.ArrayList;
import java.util.List;

public class ProgramSheet implements IProgramSheet {

    public Boolean poweredDown;
    public int lifeTokens;
    public int damageTokens;
    public List<ICard> programCards;


    public static final int MIN_NUMBER_OF_LIFE_TOKENS = 0;
    public static final int MAX_NUMBER_OF_LIFE_TOKENS = 3; // or 4 if 5 or more players

    public static final int MIN_NUMBER_OF_DAMAGE_TOKENS = 0;
    public static final int MAX_NUMBER_OF_DAMAGE_TOKENS = 9;

    //public static final int MIN_NUMBER_OF_NEW_CARDS = 0;
    //public static final int MAX_NUMBER_OF_NEW_CARDS = 5;


    public ProgramSheet() {
        this.poweredDown = false;
        this.lifeTokens = MAX_NUMBER_OF_LIFE_TOKENS;
        this.damageTokens = MIN_NUMBER_OF_DAMAGE_TOKENS;
        this.programCards = new ArrayList<>();

    }

    public Boolean getPoweredDown() {
        return poweredDown;
    }

    public void setPoweredDown(Boolean poweredDown) {
        this.poweredDown = poweredDown;
    }

    public int getLifeTokens() {
        return this.lifeTokens;
    }

    public void loseLifeTokens(int lifeTokens) throws IllegalArgumentException {
        int updatedLifeTokens = Math.max((this.getLifeTokens() - lifeTokens), this.MIN_NUMBER_OF_LIFE_TOKENS);
        if (MAX_NUMBER_OF_LIFE_TOKENS < updatedLifeTokens)
            throw new IllegalArgumentException("Cannot lose a negative amount of life tokens");
        this.lifeTokens = updatedLifeTokens;
    }

    public int getDamageTokens() {
        return this.damageTokens;
    }

    public void receiveDamageTokens(int damageTokens) throws IllegalArgumentException {
        int updatedDamageTokens = Math.min((this.getDamageTokens() + damageTokens), this.MAX_NUMBER_OF_DAMAGE_TOKENS);
        if (updatedDamageTokens < MIN_NUMBER_OF_DAMAGE_TOKENS)
            throw new IllegalArgumentException("Cannot receive a negative amount of damage tokens");
        this.damageTokens = updatedDamageTokens;
    }

    public List<ICard> getCards() {
        return programCards;
    }

    public void registerCards(List<ICard> cards) {
        this.programCards = cards;
    }

    public void registerCard() {

    }

    @Override
    public ProgramCard getProgramCard(int registerNumber) {
        return (ProgramCard) programCards.get(registerNumber);
    }
}
