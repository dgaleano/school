/*
* Blackjack : Cards
* Nick 'r3n' Bolton
* njb4@aber.ac.uk
* Module: CS12320
*
* This is where the game is actually played.
*/

class Table {

    // theDealer : Object
    // Holds the dealer.
    private Dealer theDealer = null;

    // thePlayer : Object
    // Holds the player.
    private Player thePlayer = null;

    // currentPlayer : Object
    // Holds the current player.
    private Player currentPlayer = null;

    // lastPlayer : Object
    // Holds the last player.
    private Player lastPlayer = null;

    // gameOutcome : String
    // Why has the game ended?
    private String gameOutcome = "";

    /*
    * Table : Constructor
    * This is where the game takes place.
    * Every time a new game is played, this
	* class needs to be re-instanciated.
    */
    Table() {

        // Call dealer and player to table.
        thePlayer = new Player();
        theDealer = new Dealer();

        // This will be swapped to player.
        currentPlayer = theDealer;

        // Holds the two cards and their info.
        String[][] newCards = new String[2][5];

        // Switched to yes if special tricks such as
        // Five Card or Blackjack are used.
        boolean specialTrick = false;
        
        // This will loop until the user decides they want to quit.
        do {

            // Now swap the players.
            swapPlayers();

            // Who is playing?!
            showCurrentPlayer();

            // Loop till the current player sticks.
            do {

                // Check for special card tricks and such.
                if (blackjackHand()) {
                    specialTrick = true;
                    break;

                } else if (fiveCardTrick()) {
                    specialTrick = true;
				    break;

				} else if (twentyOne()) {
                    break;
				}
			    
				// Get the array with some info about a random card.
			    newCards = theDealer.dealCards();

                // Present the user with this new card.
			    currentPlayer.chooseCard(newCards);

                // AI knows when he's had enough; humans are dumb.
                if ((currentPlayer.getPlayerType() == "HUMAN")
    				&& (currentPlayer.getMinScore() > 21)
					    || ((currentPlayer.getMinScore() == 0)
						    && !currentPlayer.endGameCheck()
							    && !currentPlayer.hasBluffedQuit())) {
    				    
    				// Force the user to stick.
    				currentPlayer.stick();
                    currentPlayer.setForceQuit(true);

                    if (currentPlayer.getMinScore() > 0) {
                        gameOutcome = "You went bust.";
    				} else {
                        gameOutcome = "You gave up.";
					}

    				// Pause so user can see what happened.
                    pause();
                }

            } while ((currentPlayer.hasStuck() == false)
			    && (currentPlayer.endGameCheck() == false));

            // Both players have had their turn.
            if (theDealer.hasStuck() && thePlayer.hasStuck()) {
                break;
			}

        } while (currentPlayer.endGameCheck() == false);

		if (!specialTrick) {
            gameOver();
            compareScores();
		}

        if (gameOutcome != "") {
            System.out.println("\n " + gameOutcome);
        }
    }

    /*
    * compareScores : boolean
    *
    * Compare scores, so we give the current  player a
	* chance to draw with the last player.
    *
    * This can only happen if the player has a twentyOne.
    */
    private boolean compareScores() {

        boolean currentHasTwentyOne = currentPlayer.hasTwentyOne();
        boolean lastHasTwentyOne = lastPlayer.hasTwentyOne();
        
        if (currentHasTwentyOne && lastHasTwentyOne) {
            // Players draw.
            gameOutcome = "Players draw.";
            return true;

		} else if (lastHasTwentyOne || currentHasTwentyOne) {
			// Last player wins the game.
            return true;
        }

        int playerMinScore = thePlayer.getMinScore();
        int playerMaxScore = thePlayer.getMaxScore();
        int dealerMinScore = theDealer.getMinScore();
        int dealerMaxScore = theDealer.getMaxScore();

        // Player max score safe.
        boolean pmsSafe = false;

        // Dealer max score safe.
        boolean dmsSafe = false;

        // Is the player's max score safe?
        if (playerMaxScore < 21) {
            pmsSafe = true;
		}

        // Is the dealer's max score safe?
        if (dealerMaxScore < 21) {
            dmsSafe = true;
		}

        if ((playerMinScore < 21) && (dealerMinScore < 21)) {
            // At this point somebody has to win, because both
			// players are stuck. Neither players should have 21.

            if (thePlayer.hasStuck() && theDealer.hasStuck()) {

                if ((playerMinScore > dealerMinScore)
				|| (pmsSafe && (playerMaxScore > dealerMaxScore))) {
                    gameOutcome = "You are the winner!";
                    Blackjack.gamesWon++;

    			} else if ((playerMinScore < dealerMinScore)
				|| (dmsSafe && (playerMaxScore < dealerMaxScore))) {
                    gameOutcome = "Dealer wins!";

    			} else {
                    gameOutcome = "Players draw. Equal scores.";
    			}

                return true;
    		}
        }

        return false;
	}

    /*
    * swapPlayers : Player
    * Changes the current player.
    */
    private void swapPlayers() {
        
        // Pitty I can't use a switch here. :(
		if (currentPlayer.getPlayerType() == "HUMAN") {
            lastPlayer = thePlayer;
            currentPlayer = theDealer;
        } else {
            lastPlayer = theDealer;
		    currentPlayer = thePlayer;
		}
    }

    /*
    * showCurrentPlayer : void
    * Prints the current player to the screen.
    */
    private void showCurrentPlayer() {

        if (currentPlayer.getPlayerType() == "HUMAN") {
            System.out.println("\n You are the current player.");

		} else {
            System.out.println("\n Dealer is current player.");
		}
	}

    /*
    * blackjackHand : boolean
    *
    * Checks if the player's hand is a blackjack (pontoon).
    *
    * A blackjack hand consists of an Ace and one face
    * card which, as luck would have it, adds upto 21.
    *
    * Unlike an ordinary hand of twenty one, if the user gets
    * this then the other player isnt given the chance to draw.
    */
    public boolean blackjackHand() {

	    if (currentPlayer.getMaxScore() == 21) {

            // So, the user is still in play.
            int aceCount = currentPlayer.getAceCount();
            int faceCount = currentPlayer.getFaceCount();

            if ((aceCount > 0) && (faceCount > 0)) {
                
                String playerType = currentPlayer.getPlayerType();
                
                // Don't let other player draw.
				gameOver();

                // Explain why game is over.
                if (playerType == "HUMAN") {
    			    gameOutcome = "Blackjack! You win.";
                    Blackjack.gamesWon++;
                    return true;

                } else if (playerType == "AI") {
				    gameOutcome = "Dealer won. Got Blackjack.";
                    return true;
                }
			}
		}

        return false;
	}

    /*
    * fiveCardTrick : boolean
    * Checks if the has a five card trick.
    * A five card trick is when the player's hand
    * sums to lower than or equal to 21, and has
    * (yep, you guessed it) -- 5 cards!
    */
    public boolean fiveCardTrick() {
        
        int minScore = currentPlayer.getMinScore();
        int cardCount = currentPlayer.getCardCount();

        if ((minScore <= 21) && (cardCount >= 5)) {
			
			gameOver();

            // Explain why game is over.
            if (currentPlayer.getPlayerType() == "HUMAN") {
    		    gameOutcome = "You win with a Five Card Trick!";
                Blackjack.gamesWon++;
                return true;

            } else if (currentPlayer.getPlayerType() == "AI") {
			    gameOutcome = "Dealer won. Got a Five Card Trick.";
                return true;
            }
		}

		return false;
	}

    /*
    * twentyOne : boolean
    *
    * Checks if the player has a Twenty One.
    * This is when the player's and sums to 21.
    *
    * As this is the last called method in the loop
    * we will check if the score is over 21 aswell.
    */
    public boolean twentyOne() {

        int minScore = currentPlayer.getMinScore();
        int maxScore = currentPlayer.getMaxScore();

		if ((minScore >= 21) || (maxScore == 21)) {

            // These variables will be used.
            boolean gotTwentyOne = false;
            String playerType = currentPlayer.getPlayerType();

            // Have the won or lost...?
            if ((minScore == 21) || (maxScore == 21)) {
                currentPlayer.setTwentyOne();
                currentPlayer.stick();
                gotTwentyOne = true;
            }
            
            // Somebody has either won or lost, what happned?
			if ((playerType == "HUMAN") && gotTwentyOne) {
				System.out.println("\n You got 21!");
			    gameOutcome = "You've won with 21! :D";
                Blackjack.gamesWon++;
                return true;

            } else if ((playerType == "AI") && gotTwentyOne) {
				gameOutcome = "Haha, dealer won with 21! :p";
                return true;

            } else if ((playerType == "HUMAN") && !gotTwentyOne) {
				gameOutcome = "You are bust, dealer wins.";
                currentPlayer.setForceQuit(true);
                return true;

			}
            
            // The only outcome left!
            gameOutcome = "Dealer is bust, you win!";
            currentPlayer.setForceQuit(true);
            Blackjack.gamesWon++;
            return true;
    	}

        // Nothing happens.
        return false;
	}

    private void gameOver() {

		// Silently quit the game at end of turn. Don't even consider
        // that other player may want a go, just go with what we have
        // so far. This is usually the case if a special trick has
		// been performed.
    	currentPlayer.setForceQuit(true);
        
		// Incase the user can't do the math...
        System.out.println();
        theDealer.printScore("The dealer's score is");
        thePlayer.printScore("And your score is");
	}

    public static void pause() {
        
		// Just to give the user a bit of suspense.
        try {
            Thread.sleep(500);
        } catch (Exception ex) {
            System.out.println(" Error: " + ex);
		}
	}
}