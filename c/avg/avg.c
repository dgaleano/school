#include <stdio.h>

main() {

	float gr1, gr2, gr3, avg;

	printf("What grade did the first student get? ");
	scanf(" %f", &gr1);
	printf("What grade did the second student get? ");
	scanf(" %f", &gr2);
	printf("What grade did the third student get? ");
	scanf(" %f", &gr3);
	
	avg = (gr1 + gr2 + gr3) / 3;
	printf("\nThe student average is %.0f", avg);
	
	printf("\n"); // New line for 'press any key to cont...'
	return 0;
}