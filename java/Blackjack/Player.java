/*
* Blackjack : Cards
* Nick 'r3n' Bolton
* njb4@aber.ac.uk
* Module: CS12320
*
* Class which control the human player and the AI.
*/

import java.util.Random;

class Player {
	
    // returnString : String
    // Generic return string.
	private String returnString = "";

    // maxScore : integer
    // The player's maximum score.
    private int maxScore = 0;

    // minScore : integer
    // The player's minimum score.
    private int minScore = 0;

    // cardCount : integer
    // Holds the total number of cards.
    private int cardCount = 0;

    // aceCount : integer
    // How many aces does the user hold?
    public int aceCount = 0;

    // faceCount : integer
    // How many face cards (King, Queen or Jack)
    // does the current player hold?
    public int faceCount = 0;

    // endGameSwitch : boolean
    // If this is switched to true, this will cause
	// all loops keeping the game in play to end.
    private boolean endGameSwitch = false;

    // forceQuit : boolean
    // If the endGameCheck() function notices this is
    // set to true, the game will silently quit.
    private boolean forceQuit = false;

    // playerType : String
    // What is this player?
    private String playerType = "";

    // stuck : boolean
    // Defines wether player is stuck.
    private boolean stuck = false;

    // gotFiveCardTrick : boolean
    // Did the user get a gotFiveCardTrick?
    private boolean gotTwentyOne = false;

    // bluffedQuit : boolean
    // If the user changes their mind,
    // this stops the user from giving up.
    private boolean bluffedQuit = false;

    /*
    * Player : Constructor
    * This doesn't do much, apart from call setPlayerType()
    */
    Player() {
        setPlayerType("HUMAN");
	}

    /*
    * setTwentyOne : void
    * The player got a Twenty One.
    */
    public void setTwentyOne() {
        gotTwentyOne = true;
	}

    /*
    * setTwentyOne : boolean
    * Has the player got a Twenty One?
    */
    public boolean hasTwentyOne() {
        return gotTwentyOne;
	}

    /*
    * getCardCount : void
    * How many cards does the player hold?
    */
    public int getCardCount() {
       return cardCount;
	}

    /*
    * setForceQuit : void
    * Sets the forceQuit variable game to the argument value.
    */
    public void setForceQuit(boolean value) {
        forceQuit = value;
    }

    /*
    * getPlayerType : String
    * Tells us if the player is AI or Human.
    */
    public String getPlayerType() {
        return playerType;
	}

    /*
    * setPlayerType : String
    * Set the player type, should only be used internally.
    */
    public void setPlayerType(String type) {
        playerType = type;
	}

    /*
    * hasStuck : boolean
    * Is the player suck with his cards?
    */
    public boolean hasStuck() {
        return stuck;
	}

    /*
    * getAceCount : integer
    * Returns the number of aces the user is holding.
    */
    public int getAceCount() {
        return aceCount;
	}

    /*
    * getFaceCount : integer
    * Returns the number of face cards the user is holding.
    * Face cards are those such as King, Queen and Jack.
    */
    public int getFaceCount() {
        return faceCount;
	}

    /*
    * getMinScore : int
    * Returns the player's minimum score as an integer.
    */
    public int getMinScore() {
        return minScore;
	}

    /*
    * getMaxScore : int
    * Returns the player's maximum score as an integer.
    */
    public int getMaxScore() {
        return maxScore;
	}

    /*
    * hasBluffedQuit : boolean
    * Has the user decided not to quit after all?
    */
    public boolean hasBluffedQuit() {
        return bluffedQuit;
	}

    /*
    * printScore : void
    * Prints the user's score to the screen.
    */
    public void printScore(String stringPrefix) {
        
        // Convert scores (min & max) to integer.
		String stringMinScore = Integer.toString(getMinScore());
        String stringMaxScore = Integer.toString(getMaxScore());
       
        // Print all the scores.
	    System.out.print(" " + stringPrefix);
        System.out.print(" " + stringMinScore);

        if (getMinScore() != getMaxScore()) {
            System.out.println(" or " + stringMaxScore);
        } else {
            System.out.println("");
		}
    }

    /*
    * addToScore : boolean
    * Adds the specified value to totalScore, then
	* returns true if number is greather than one.
    */
    public boolean addToScore(int min, int max) {

        cardCount++;

        if ((min <= 0) && (max <= 0)) {
            return false;
		}

        minScore += min;
        maxScore += max;

        return true;
	}

    /*
    * saidYes : boolean
    * Returns true if the first letter of the user's response
	* is 'Y'. Also gives the ability to exit the game.
    */
    public boolean saidYes(boolean onQuit) {

        System.out.print(" (Y/N) ");
        
		char responce = Keyboard.readChar();
        responce = Character.toLowerCase(responce);

		switch (responce) {
            case 'y':
                return true;

            case 'q':
            case 'e':

                // Is the user is being asked to quit?
                if (onQuit) {
                    return true;
				}

                endGameSwitch = true;
                return false;

            default:
                return false;
        }
	}

    /*
    * chooseCard : void
    * Does the player want to stick or twist with the specified card?
    */
    public void chooseCard(String[][] cardsInfo) {
        
        // Why do we need two cards?!?!
        //System.out.println("Card is " + cardsInfo[0][0]);
        //System.out.println("Card 2 is " + cardsInfo[1][0]);

        System.out.println("");
        if (getMinScore() > 0) {
            printScore("Your score so far is");
        }

        System.out.println(" Dealer offers a card.");
		System.out.print(" Twist or Stick? (T/S) ");

        int choice = 0;
        String[] chosenCard = cardsInfo[choice];

        switch (Character.toLowerCase(Keyboard.readChar())) {

            case 'q':
            case 'e':
                
                // This will ask the user if they want to quit.
				endGameSwitch = true;
                break;
			
			case 't':

                // Adds to score, and offers another card.
                twist(chosenCard);
                System.out.println(" Card was " + chosenCard[0]);
                break;

            default:

                // Dosen't do much, but allows cards to be compared.
    			stick();
                if (getMinScore() > 0) {
                    printScore("Sticking with score of");
                }
                break;
        }
	}

    /*
    * stick : void
    * States the user has decided to stick.
    */
    public void stick() {
        stuck = true;
	}

    /*
    * twist : void
    * This class dosen't actually have much point,
    * however, I will leave it as it may be used later.
    */
    public void twist(String[] cardInfo) {

        // Parse as integers.
        int minValue = Integer.parseInt(cardInfo[1]);
        int card = Integer.parseInt(cardInfo[2]);
        int suit = Integer.parseInt(cardInfo[3]);

        // Incase we have an ace.
        int maxValue = minValue;

		if (cardInfo[4] == "IS_ACE") {
            maxValue += 10;
            aceCount++;

    	} else if (cardInfo[4] == "IS_FACE") {
            faceCount++;
    	}

		// Increment the player score.
		addToScore(minValue, maxValue);

        // Wipe the card.
		Cards.removeCard(card, suit);
	}

    /*
    * endGameCheck : boolean
    *
    * Checks if the player really wants to end the game.
    *
    * This method dosen't actually end the game, we
    * will leave this upto loops and such to decide that.
    *
    * The idea is that we don't want to bother the user
    * unless the endGameSwitch variable has been set to true.
    *
    * I got a bit bored at this point...
    */
    public boolean endGameCheck() {

        if (forceQuit) {
            // Silently quit.
            return true;
		}

        String[][] messages = new String[7][2];
        String[] theMessage = new String[2];
        Random randInt = new Random();
        boolean userSaidYes = false;

        // Just for comedy value :p
        messages[0][0] = "Giving up already?";
        messages[1][0] = "Are you sure you want to quit?";
        messages[2][0] = "Don't you want to play some more?";
        messages[2][1] = "Confusing isn't it? :p";
        messages[3][0] = "Quit? Do you reeeeeeeealy want to do that?";
        messages[4][0] = "You want to quit? Fine be like that...";
        messages[5][0] = "Ugh, you didn't come here to quit did you?";
        messages[6][0] = "Pft, game not good enough for ya!?";
        messages[6][1] = "Oh! There goes another one ;)";

        // Give the user a random message. This is an array
        // which has an element that tells us what polarity
		// the question is.
        theMessage = messages[randInt.nextInt(messages.length)];

        if (endGameSwitch) {

            // Just double check with the user.
            System.out.print("\n " + theMessage[0]);
            userSaidYes = saidYes(true);

            if ((userSaidYes == false) && (theMessage[1] == null)
			|| (userSaidYes == true) && (theMessage[1] != null)) {
                
                // Player has changed their mind! :D
				System.out.println(" You made the right choice :)");
                bluffedQuit = true;
                endGameSwitch = false;
                return false;
			}

            // Player is a big meanie.
            System.out.println("\n Game Over!");
            setForceQuit(true);
            return true;
		}

        // Don't bother the user.
        return false;
	}
}