/* C. Facchi 2.4.06 Example for Reader/Writer solution using IPC semaphore. Algorithm see Tanenbaum */

#include <stdio.h>
#include <stdlib.h>

# include <sys/types.h>
# include <sys/ipc.h>
# include <sys/sem.h>



#define NUMBER_OF_READER_PROCESSES	5
#define NUMBER_OF_WRITER_PROCESSES	2
#define NUMBER_OF_CHILD_LOOPS		3

#define MUTEX	0
#define READER	1
#define WRITER	2

key_t sem_key;
int sem_id;
unsigned short array[3]; /*sem array*/


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
void get_sem(int sem_id,int sem_num,unsigned short * list) {
  union semnum {
    int val;
    struct semid_ds *buf;
    unsigned short *array;
  }argument;
  argument.array=list;

  if(semctl(sem_id,0,GETALL,argument)<0){
    perror("Error in semctl");
    exit(1);
  }
}

int get_sem_val(int sem_id,int sem_num) {
  int ret_val;
  
  if((ret_val=semctl(sem_id,sem_num,GETVAL))<0){
    perror("Error in semctl");
    exit(1);
  }
  else
    return(ret_val);
}

void reader_loop(int process){

  int i;

  for( i=0; i< NUMBER_OF_CHILD_LOOPS; i++){

    P(sem_id, MUTEX); /* lock critical section */
    V(sem_id, READER); /* increase number of Reader*/
    if( get_sem_val(sem_id, READER)==1)		/* if first reader, block writer */
      P(sem_id, WRITER);
    V(sem_id, MUTEX); /* unlock critical section */

    get_sem(sem_id, 0, array);
    printf( "\tREADER %d (%d) enters critical section\tMUTEX =\t%d READER =\t%d WRITER =\t%d\n", process, getpid(),array[MUTEX], array[READER], array[WRITER]);

    sleep(1);

    printf( "\tREADER %d (%d) leaves critical section\tMUTEX =\t%d READER =\t%d WRITER =\t%d\n", process, getpid(),array[MUTEX], array[READER], array[WRITER]);

    P(sem_id, MUTEX); /* lock critical section */
    P(sem_id, READER); /*  decrease number of Readers */
    if( get_sem_val(sem_id, READER)==0)		/* if no reader, unblock possible waiting writer */
      V(sem_id, WRITER);
    V(sem_id, MUTEX); /* unlock critical section */

    get_sem(sem_id, 0, array);

    sleep (1);
  }
}

void writer_loop(int process){

  int i;

  for( i=0; i<NUMBER_OF_CHILD_LOOPS; i++){
    P(sem_id, WRITER);
    get_sem(sem_id, 0, array);
    printf( "WRITER %d (%d) enters critical section\tMUTEX =\t%d READER =\t%d WRITER =\t%d\n", process, getpid(),array[MUTEX], array[READER], array[WRITER]);

    sleep(1);

    get_sem(sem_id, 0, array);
    printf( "WRITER %d (%d) leaves critical section\tMUTEX =\t%d READER =\t%d WRITER =\t%d\n", process, getpid(),array[MUTEX], array[READER], array[WRITER]);
    V(sem_id, WRITER);
    sleep (1);
  }
}


main(){

  int process;		

  if ((sem_key = ftok ("/home/facchi",'2'))<0){
    perror("Error in ftok");
    exit(1);
  }


  if ((sem_id = semget( sem_key,3, IPC_CREAT|0666))<0){	/* 3 semphores needed */
    perror("Error in semget");
    exit(1);
  }

  init_sem( sem_id, MUTEX, 1);	/* init MUTEX, so first process will not be blocked */
  init_sem( sem_id, READER, 0);	/* no reader counter */
  init_sem( sem_id, WRITER, 1);	/* initally writer allowed*/

  get_sem(sem_id, 0, array);
printf( "MUTEX =\t%d READER =\t%d WRITER =\t%d\n", array[MUTEX], array[READER], array[WRITER]);

  for(process=0; process< NUMBER_OF_READER_PROCESSES; process++) {
    switch(fork()) {
      case -1:	perror("FORK failed");
    		break;
    
      case 0:	/*child*/
  		//printf("child process %d (%d) started\n", process, getpid());
  		reader_loop(process);
  		//printf("child process %d (%d) stoped\n", process, getpid());
  		exit(0);
    
      default:	/*father*/
  				;
    }
  }

  for(process=0; process< NUMBER_OF_WRITER_PROCESSES; process++) {
    switch(fork()) {
      case -1:	perror("FORK failed");
    		break;
    
      case 0:	/*child*/
  		//printf("child process %d (%d) started\n", process, getpid());
  		writer_loop(process);
  		//printf("child process %d (%d) stoped\n", process, getpid());
  		exit(0);
    
      default:	/*father*/
  		;
    }
  }

  int i;
  for (i=0; i<12;i++){ 
    sleep(1);
    get_sem(sem_id, 0, array);
    printf( "Father ! MUTEX =\t%d READER =\t%d WRITER =\t%d\n", array[MUTEX], array[READER], array[WRITER]);
  }
  printf("Father process stoped");

	
}