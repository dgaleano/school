/*
* Blackjack : Cards
* Nick 'r3n' Bolton
* njb4@aber.ac.uk
* Module: CS12320
*
* Dealer inherits player because dealer is a player.
*
* Has extra functionality to use AI.
*/

import java.util.Random;

class Dealer extends Player {

    // randInt : Object
    // Random number generator.
    private Random randInt = new Random();

    // randSuiteOrd : Integer
    // Holds a random number.
    private int randSuiteOrd = 0;

    // randCardOrd : Integer
    // Holds a random number.
    private int randCardOrd = 0;

    // cardScore : String
    // Holds the score of the last drawn card.
    private String cardScore;

    // suiteName : String
    // Holds the suite name of the last drawn card.
    private String suiteName;

    // cardNumber : String
    // Last drawn card number.
    private String cardNumber;

    // theDeck : Array of String
    // Holds a deck of cards as an array.
    private String[][][] theDeck = new String[4][13][2];

    // cardsHandler : Object
    // Holds the cards handler object.
    private Cards cardsHandler = null;

    /*
    * Dealer : Constructor
    * New deck will be created...
    */
    Dealer() {
        
		// Dealer dies every time we start a new game, so when
        // the new Dealer comes to life, we make a new deck.
        Cards.newDeck();

        // Set the player type.
        setPlayerType("AI");
	}

    /*
    * dealOneCard : Array of String
    * Draws one random card for player/dealer to choose.
    */
    public String[] dealOneCard() {

        // returnValue will only ever be used here.
        String[] returnValue = new String[5];
        String cardName = "";

        do {
            // Loop till we find a card that exists.
            randCardOrd = randInt.nextInt(13);
            randSuiteOrd = randInt.nextInt(4);
            cardName = Cards.getCardName(randCardOrd, randSuiteOrd);

		} while (cardName == "CARD_DOSENT_EXIST");

		// This is the bit we show the user.
        suiteName = Cards.getSuiteName(randSuiteOrd);


		//randomCard[0] =  cardNumber + " of " + suiteName;
        returnValue[0] = Cards.getCardInfo(randCardOrd, randSuiteOrd);
        returnValue[1] = Cards.getCardValue(randCardOrd);
        returnValue[2] = Integer.toString(randCardOrd);
        returnValue[3] = Integer.toString(randSuiteOrd);

        // This fith element specifies if the card is special.
        switch (randCardOrd) {
            case 0:
                returnValue[4] = "IS_ACE";
                break;

            case 10:
            case 11:
            case 12:
                returnValue[4] = "IS_FACE";
                break;
		}

        return returnValue;
	}

    /*
	* dealCard : Array of String
	* Deals two cards.
    */
    public String[][] dealCards() {

        // returnValue will only ever be used here.
        String[][] returnValue = new String[2][5];
        
        // Get two cards.
		returnValue[0] = dealOneCard();
        returnValue[1] = dealOneCard();

        return returnValue;
	}

    /*
    * chooseCard : void
    * Overrides parental method, and uses AI this time.
    */
    public void chooseCard(String[][] cardsInfo) {

        Random randIntObject = new Random();
        String[] chosenCard = cardsInfo[0];
        int gamble = randIntObject.nextInt(Config.GAMBLE_LEVEL);

        if (getMinScore() <= 10) {
		    
			// It's always safe to add under this number.
            while (getMinScore() <= 10) {
		        System.out.println(" Dealer looks happy. :)");
                twist(chosenCard);
                break;
		    }

        } else if (getMinScore() < 21) {

            // Hmm, getting risky... Dealer is a dogey
            // bloke, so most of the time he will gamble.
            while (getMinScore() < 21) {

                // Dealer isn't allowed to stick under 17!
                // Thanks to Osme for the reminder.
				if ((getMinScore() < 17) || (getMaxScore() < 17)) {
    			    if (gamble == 0) {
                        // Don't gamble!
    		            System.out.println(" Dealer looks calm. :|");
                        stick();
                        break;
                    }
    			}
				
				// Decided not to bail out, dealer shall gamble.
    		    System.out.println(" Dealer looks dodgy. :S");
                twist(chosenCard);
                break;
    		}

        } else {

            // At this point i have either won or lost.
    		// Either way I have to stick.
            if ((getMinScore() >= 21) || (getMaxScore() == 21)) {
    		    
                if ((getMaxScore() == 21) || (getMinScore() == 21)) {
                    System.out.println(" Dealer looks excited. :D");

    			} else {
                    System.out.println(" Dealer looks sad. :(");
    			}

                stick();
    		}
        }
		
		Table.pause();
	}

    /*
    * generateGraphic : void
    * This isn't in the spec, but I like the idea of having some sort
    * of graphical representation of the cards; so I'll stick this bit
    * in :)
    */
    public void generateGraphic() {

        /*
        String graphicTemplate;
        graphicTemplate =  "+------+";
        graphicTemplate += "| King |";
        graphicTemplate += "|  /\  |";
        graphicTemplate += "|  \/  |";
        graphicTemplate += "|Dimnds|";
        graphicTemplate += "+------+";

        graphicTemplate =  "+------+";
        graphicTemplate += "|Queen |";
        graphicTemplate += "|  @@  |";
        graphicTemplate += "|  \/  |";
        graphicTemplate += "|Hearts|";
        graphicTemplate += "+------+";

        graphicTemplate =  "+------+";
        graphicTemplate += "| Ace  |";
        graphicTemplate += "|  /\  |";
        graphicTemplate += "|  ()  |";
        graphicTemplate += "|Spades|";
        graphicTemplate += "+------+";

        graphicTemplate =  "+------+";
        graphicTemplate += "|  10  |";
        graphicTemplate += "|  @@  |";
        graphicTemplate += "|  ()  |";
        graphicTemplate += "|Clubs |";
        graphicTemplate += "+------+";
        */

	}
}