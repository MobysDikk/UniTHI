#include <stdio.h>
#include <stdlib.h>


void child_loop(int process){

	#define NUMBER_OF_CHILD_LOOPS	3
	int i;
	
	for( i=0; i<NUMBER_OF_CHILD_LOOPS; i++){
		printf( "PROCESS %d (%d) enters critical section\n", process, getpid());
		sleep(1);
		printf( "PROCESS %d (%d) leaves critical section\n", process, getpid());
		printf( "PROCESS %d (%d) enters NONcritical section\n", process, getpid());
		sleep (1);
		printf( "PROCESS %d (%d) leaves NONcritical section\n", process, getpid());
	}
}
void main(){


	#define NUMBER_OF_PROCESSES	3
	int process;		
	
	for(process=0; process< NUMBER_OF_PROCESSES; process++) {
		switch(fork()) {
			case -1:	perror("FORK failed");
					exit(1);

			case 0:		/*child*/
					printf("child process %d (%d) started\n", process, getpid());
					child_loop(process);
					printf("child process %d (%d) stopped\n", process, getpid());
					exit(0);
;
			default:	/*father*/
					;
		}
	}
	printf("Father process stopped");
	
}