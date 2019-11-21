#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#define FILEPATH "/home/lars/new"

void init_sem(int sem_id, int nsem) {
  int i;
  for(i = 0; i < nsem; i++) {
    semctl(sem_id, i, SETVAL, 1);
  }
}

void P(int sem_id, int sem1) {
  struct sembuf sops[2];
  sops[0].sem_num = (short) sem1 ;
  sops[0].sem_op = -1;
  sops[0].sem_flg = SEM_UNDO;
  sops[1].sem_num = (short) (sem1 + 1) % 5;
  sops[1].sem_op = -1;
  sops[1].sem_flg = SEM_UNDO;

  if(0 != semop(sem_id, sops, 2))
    printf("P did not work.\n");
}

void V(int sem_id, short sem1) {
  struct sembuf sops[2];
  sops[0].sem_num = (short) sem1;
  sops[0].sem_op = 1;
  sops[0].sem_flg = SEM_UNDO;
  sops[1].sem_num = (short) (sem1 + 1) % 5;
  sops[1].sem_op = 1;
  sops[1].sem_flg = SEM_UNDO;
  
  if(0 != semop(sem_id, sops, 2))
    printf("V did not work.\n");
}

int main (void){  
  //Creating n Childs
  int i,pid;
  if(open(FILEPATH, O_CREAT)<0) 
    printf("Opend has not succeeded.\n");

  key_t key = ftok(FILEPATH, 2);
  if(key < 0) {
    perror("Erzeugen ftok fehlgeschlagen.");
    exit(1);
  }

  int sem_id = semget(key, 5, IPC_CREAT | 0666);
  init_sem(sem_id, 5);

  for (i = 0; i < 5; ++i) {
    pid = fork();
    if (pid > 0) {   /* I am the parent, create more children */
    } else if (pid == 0) { /* I am a child, get to work */
      //Critical section
        P(sem_id, i);
        printf("%d: Gehe jetzt etwas essen.\n\n", i);
        sleep(1);
        printf("%d: Gehe jetzt wieder denken.\n\n", i);
        V(sem_id, i);
      break;
    } else {
      perror("fork error\n");
      exit(1);
    }
  }
  exit(0);
}
