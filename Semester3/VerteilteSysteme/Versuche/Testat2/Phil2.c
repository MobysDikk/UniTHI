#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#define FILEPATH1 "/tmp"
#define FILEPATH2 "/tmp"

void init_sem(int sem_id, int n, int values[]) {
  int i;
  for(i = 0; i < n; i++) {
    semctl(sem_id, i, SETVAL, values[i]);
  }
}

void P(int sem_id, int index) {
  struct sembuf* sops;
  sops->sem_num = index;
  sops->sem_op = -1;
  sops->sem_flg = SEM_UNDO;

  if(0 != semop(sem_id, sops, 1))
    printf("P did not work.\n");
}

void V(int sem_id, int index) {
  struct sembuf* sops;
  sops->sem_num = index;
  sops->sem_op = 1;
  sops->sem_flg = SEM_UNDO;

  if(0 != semop(sem_id, sops, 1))
    printf("P did not work.\n");
}

int main (void){ 

  //Semaphores for the forks
  if(open(FILEPATH1, O_CREAT)<0){
      printf("");
  } 
    

  key_t forks_key = ftok(FILEPATH1, 2);
  if(forks_key < 0) {
    perror("Creating a key with ftok has not succeeded.");
    exit(1);
  }

  int forks = semget(forks_key, 5, IPC_CREAT | 0666);
  int forks_values[5] = {1, 1, 1, 1, 1};
  init_sem(forks, 5, forks_values);

  //Semaphore for the table
  if(open(FILEPATH2, O_CREAT)<0){
    printf("");
  } 
    

  key_t table_key = ftok(FILEPATH2, 3);
  if(table_key < 0) {
    perror("Creating a key with ftok has not succeeded.");
    exit(1);
  }

  int table = semget(table_key, 1, IPC_CREAT | 0666);
  int table_values[1] = {2};
  init_sem(table, 1, table_values);

  //Creating n Childs
  int i,pid;
  for (i = 0; i < 5; ++i) {
    pid = fork();
    if (pid > 0) {   /* I am the parent, create more children */
    } else if (pid == 0) { /* I am a child, get to work */
      //Critical section
      P(table, 0);

        P(forks, i);
        P(forks, (i+1) % 5);
        
        printf("%d: Gehe jetzt etwas essen.\n\n", i+1);
        sleep(1);
        printf("%d: Gehe jetzt wieder denken.\n\n", i+1);
        
        V(forks, (i+1) % 5);
        V(forks, i);
      
      V(table, 0);
      break;
    } else {
      perror("fork error\n");
      exit(1);
    }
  }
  exit(0);
}
