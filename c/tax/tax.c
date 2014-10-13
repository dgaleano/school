#include <stdio.h>

main() {

	float s, t;
	char comment[35];

	printf("How much do you make in $? ");
	scanf(" %f", &s);

	t = 0.175 * s;
	printf("You owe $%.0f in taxes! ", t);

	if(t >= 10000) {
		printf("Hmm, you must make a lot of money.");
	} else { 
		printf("Bah, thats nothing!");
	}
	
	printf("\n\n");
	return 0;
}
