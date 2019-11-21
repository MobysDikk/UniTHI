/* C. Facchi 2.4.06 Example for Mutex; solution using IPC semaphore */

#include <stdio.h>
#include <stdlib.h>

# include <sys/types.h>
# include <sys/ipc.h>
# include <sys/sem.h>



#define NUMBER_OF_PROCESSES	3

key_t sem_key;
int sem_id;


void V(int sem_id,int sem_num) {
  struct sembuf semaphore;

  semaphore.sem_num=sem_num;
  semaphore.sem_op=1;
  semaphore.sem_flg=~(IPC_NOWAIT|SEM_UNDO);
  if(semop(sem_id,&semaphore,1)){
    perror("Error in semop V()");
    exit(1);
  }
}

void P(int sem_id,int sem_num) {
  struct sembuf semaphore;

  semaphore.sem_num=sem_num;
  semaphore.sem_op=-1;
  semaphore.sem_flg=~(IPC_NOWAIT|SEM_UNDO);
  if(semop(sem_id,&semaphore,1)){
    perror("Error in semop P()");
    exit(1);
  }
}

void init_sem(int sem_id,int sem_num,int val) {
  union semnum {
    int val;
    struct semid_ds *buf;
    unsigned short *array;
  }argument;
  argument.val=val;

  if(semctl(sem_id,sem_num,SETVAL,argument)<0){
    perror("Error in semctl");
    exit(1);
  }
}

void child_loop(int process){

  #define NUMBER_OF_CHILD_LOOPS	3
  int i;

  for( i=0; i<NUMBER_OF_CHILD_LOOPS; i++){
    P(sem_id,0);
    printf( "PROCESS %d (%d) enters critical section\n", process, getpid());
    sleep(1);
    printf( "PROCESS %d (%d) leaves critical section\n", process, getpid());
    V(sem_id,0);
    printf( "PROCESS %d (%d) enters NONcritical section\n", process, getpid());
    sleep (1);
    printf( "PROCESS %d (%d) leaves NONcritical section\n", process, getpid());
  }
}


main(){

  int process;		

  if ((sem_key = ftok ("/tmp",'1'))<0){
    perror("Error in ftok");
    exit(1);
  }


  if ((sem_id = semget( sem_key,1, IPC_CREAT|0666))<0){	/* only one sem needed */
    perror("Error in semget");
    exit(1);
  }

  init_sem( sem_id, 0, 1);	/* init sem with one, so first process will not be blocked */

  for(process=0; process< NUMBER_OF_PROCESSES; process++) {
    switch(fork()) {
      case -1:	perror("FORK failed");
    		break;
    
      case 0:	/*child*/
  		printf("child process %d (%d) started\n", process, getpid());
  		child_loop(process);
  		printf("child process %d (%d) stopped\n", process, getpid());
  		exit(0);
    
      default:	/*father*/
  				;
    }
  }
  
  printf("Father process stopped");
	
}
