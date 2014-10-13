/*
* Blackjack : Cards
* Nick 'r3n' Bolton
* njb4@aber.ac.uk
* Module: CS12320
*
* Tests that the cards class works properly.
*/

class TestCards {

    public static void main(String[] args) {

		Cards thePack = new Cards();
        printCards(thePack);
	}

    private static void printCards(Cards thePack) {

        // Test that all the cards are cushdy!
        String[][][] cards = thePack.getCards();
        System.out.println("\nThese are what cards we have...\n");
        int i = 1;
        for (int s = 0; s < cards.length; s++) {
			for (int c = 0; c < cards[s].length; c++) {
                System.out.print(Integer.toString(i++) + ": ");
                System.out.print(cards[s][c][0] + " of ");
                System.out.print(thePack.getSuiteName(s));
                System.out.print(" ("+thePack.getCardValue(c)+")\n");
		    }
            System.out.print("\n");
		}
	}
}