/*
 Author: Nick "r3n" Bolton
 Filename: atm.c
 Started: 18 April 03 (r3n)
 Ammended: 18 April 03 (r3n)
 Version: 1.0
*/

#include <stdio.h>
#include <stdlib.h>

main() {
	
	char choice[1], yn[1], withdrawOpt[1];
	int pins[5] = { 1234, 5519, 7894, 4456, 1124 }; // valid pin numbers
	int withdrawAmts[6] = { 0, 10, 20, 50, 100, 200 }; // pre-defined withdraw amounts
	int i, pin;
	float balance;
	
	//srand(); // seed random balance
	//balance = rand();
	balance = 10.00;

	printf("\n");
		
	for(i = 0; i < 27; i++) { // print 27 spaces to center title
			printf(" ");
	}
	
	printf("Welcome to the Sexy ATM!\n\n");

	printf("Please enter your 4 digit PIN: ");
	scanf(" %d", &pin);

	for(i = 0; i < 5; i++) { // searches pins for matching value
		(pin == pins[i]) ? (yn[0] = 'Y') : (choice[0] = '1');
	}
	
	while((yn[0] == 'Y') || (yn[0] == 'y')) { // loop until user chooses !y or !Y
	
		printf("\n");
		
		for(i = 0; i < 27; i++) { // print 27 spaces to center title
				printf(" ");
		}
		
		printf("Welcome to the Sexy ATM!\n\n");
	
		printf("Please choose an option...\n\n");
		printf(" 1. View Balance\n");
		printf(" 2. Withdraw Cash\n");
		printf(" 3. Print Mini-Statement\n");
		printf(" 4. Order Statement\n");
		printf(" 5. Exit program\n\n");
		printf("Please enter your choice: ");
		scanf(" %s", choice);
		printf("\n");
		
		switch(choice[0]) {
			
			case('1'): // choose 'withdraw cash'
				printf("Your balance is $%.2f!", balance);
			break;
			
			case('2'): // choose 'withdraw cash'
				
				printf("How much would you like to withdraw?\n\n");
				
				for(i = 1; i < 6; i++) {
					printf(" %.0d. $%.0d\n", i, withdrawAmts[i]);
				}

				printf("\n");
				scanf(" %s", withdrawOpt);

				balance = (balance - withdrawAmts[withdrawOpt[0]]);
				printf("\n$%.2d has been deducted from your balance.\n",
					withdrawAmts[withdrawOpt[0]]);
				printf("You now have $%.2f in your account.", balance);

			break;
			
			case('3'): // choose 'withdraw cash'
				printf("Your mini-statement has been printed, Thankyou.");
			break;
			
			case('4'): // choose 'withdraw cash'
				printf("Your statement has been ordered, Thankyou.");
			break;

			case('5'): // exits program
				printf("Goodbye!");
				exit(1);
			break;
		}

		printf("\n\nWould you like to perform another transaction? (Y/N) ");
		scanf(" %s", yn);
	}
	
	printf("\nGoodbye!\n\n");
	return 0;
}