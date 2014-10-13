/*
* Blackjack : Cards
* Nick 'r3n' Bolton
* njb4@aber.ac.uk
* Module: CS12320
*
* The idea behind this class is to handle
* everything related to the deck of cards.
*
* The majoirty of this class' functionality
* is static because there can only be one pack
* of cards in play at any one time.
*/

class Cards {

    // cards : Array of String
    // Holds all the cards.
	private static String[][][] cards = new String[4][13][2];

    // suites : Array of String
    // Tells us what each of the suites are called.
    private static String[] suites = new String[4];

    /*
    * getCards : Array of String
    * Just returns the pack of cards regardless of if the pack is
	* empty or not. This probably won't get used much, but I'll
    * leave it in for the test class.
    */
	public String[][][] getCards() {
        return cards;
	}

    /*
    * removeCard : void
    * This function dosen't actually remove the card as such, but it
    * does wipe the card's name making it useless.
    */
    public static void removeCard(int card, int suit) {
        cards[suit][card][0] = "";
	}

    /*
    * getSuiteName : String
    * Tells us what a suite is given the ordinal number of the
	* element.
	*/
    public static String getSuiteName(int ord) {
        
		if (suites[ord] != "") {
		    return suites[ord];
        }

        return "SUIT_DOSENT_EXIST";
	}

    /*
    * getCardName : String
    * Gets the card's name value.
	*/
    public static String getCardName(int card, int suit) {
        
		if (cards[suit][card][0] != "") {
		    return cards[suit][card][0];
        }

        return "CARD_DOSENT_EXIST";
	}

    /*
    * getCardValue : String
    * Returns the score value as string. We can safely use the first
	* suite, as they are all the same anyway. Cards will always
    * have a value even if the face is blank.
	*/
    public static String getCardValue(int ord) {
		
        String cardName = cards[0][ord][1];

        if (cardName != "") {
		    return cardName;
        }

        // Silly dealer, he forgot...
        return "Pack hasn't been created.";
	}

    /*
    * getCardInfo : String
    * Gets the info of the card in the format of "Card of Suit".
    */
    public static String getCardInfo(int card, int suit) {
        
		String cardName = getCardName(card, suit);

        if (cardName != "CARD_DOSENT_EXIST") {
            // Find the suit name and return info.
			String suitName = getSuiteName(suit);
            return cardName + " of " + suitName;
        }

        // If we are still here, it may be because the card
		// has been removed or just simply not created yet.
        // The user may see this if there is a bug in the
		// game, so lets make it a little bit friendly.
        return "Card dosen't exsist.";
	}

    /*
    * newDeck : void
    * Makes us a band new pack of cards!
    */
    public static void newDeck() {

        // Lets make these hard coded for now :)
		suites[0] = "Clubs";
        suites[1] = "Spades";
        suites[2] = "Hearts";
        suites[3] = "Diamonds";

        // Iterate through each suite...
        for (int i = 0; i < cards.length; i++) {

            // To make things look nicer, we shall use a loop to
			// do this bit. Start at 1, as 0 is taken by the Ace.
            for (int cNum = 1; cNum < 10; cNum++) {

				// Card numbers start at 2, so we will just add 1.
				// The score is the same as the card number.
                cards[i][cNum][0] = Integer.toString(cNum + 1);
                cards[i][cNum][1] = Integer.toString(cNum + 1);
			}

            // I think we'll have these hard coded aswell.
            cards[i][0][0] = "Ace";
            cards[i][10][0] = "Jack";
            cards[i][11][0] = "Queen";
            cards[i][12][0] = "King";

            // Now to set the card values.
            cards[i][0][1] = "1";
            cards[i][10][1] = "10";
            cards[i][11][1] = "10";
            cards[i][12][1] = "10";
		}
	}
}