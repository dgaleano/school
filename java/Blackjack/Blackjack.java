/*
* Blackjack
* Nick 'r3n' Bolton
* njb4@aber.ac.uk
* Module: CS12320
*
* This is a simplified version of the game
* Blackjack (or Pontoon).
*
* Please see Changelog.txt for version info
* and changes to the game.
*
* Thanks to Lewis and Loftus for their
* Keyboard class.
*/

class Blackjack {

    // gamesPlayed : integer
    // Just some randomly worthless info...
    private static int gamesPlayed = 0;

    // gamesWon : integer
    // And some more random info :p
    public static int gamesWon = 0;

    public static void main(String[] args) {

        try {
		    Runtime.getRuntime().exec("cmd /k cls");
        } catch(Exception ex) {
            System.out.print("");
		}

		System.out.println("\n *********************");
        System.out.println(" Welcome to Blackjack!");
        System.out.println(" Created by r3n");
        System.out.println(" Version " + Config.VERSION);
        System.out.println(" *********************\n");
        System.out.println(" Q or E will exit the game.");

        boolean newGame = false;
        Table table = null;
        
        do {
			
            gamesPlayed++;
			table = new Table();

            System.out.print("\n Play again? (Y/N) ");
			char responce = Keyboard.readChar();

            switch (Character.toLowerCase(responce)) {
                
				// User will sometimes press t, user will twist a lot.
                // It can get irritating if the user accidentally
                // presses t instead of y.
				case 'y':
                case 't':
                    
                    // Start a new game.
    				newGame = true;
        			System.out.println("\n *********************");
                    System.out.println(" Welcome to Blackjack!");
                    System.out.println(" Starting new game...");
                    System.out.println(" *********************\n");
                    System.out.println(" Q or E will exit the game.");
                    break;

                default:
                    newGame = false;
                    break;
            }

        } while(newGame == true);

        if (gamesPlayed > 1) {
            System.out.print(" You played " + gamesPlayed);
            System.out.println(" games!");
            System.out.println(" You won " + gamesWon + " of them.");
        }
	}
}