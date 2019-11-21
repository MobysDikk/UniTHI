#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <sys/ipc.h>
#include <sys/sem.h>

void init_sem(int sem_id) {
  semctl(sem_id, 0, SETVAL);
}

void P(int sem_id) {
  struct sembuf* sops = (struct sembuf*) malloc(sizeof(struct sembuf));
  sops->sem_num = (short) 0;
  sops->sem_op = -1;
  sops->sem_flg = SEM_UNDO;
  semop(sem_id, sops, 1);
}

void V(int sem_id) {
  struct sembuf* sops = (struct sembuf*) malloc(sizeof(struct sembuf));
  sops->sem_num = (short) 0;
  sops->sem_op = 1;
  sops->sem_flg = SEM_UNDO;
  semop(sem_id, sops, 1);
}

int main (void){  
  //Creating n Childs
  int i,pid;
  key_t key = ftok("/tmp", 1);
  if(key < 0) {
    perror("Erzeugen ftok fehlgeschlagen.");
    exit(1);
  }
  int sem_id = semget(key, 1, IPC_CREAT | 0666);
  init_sem(sem_id);

  for (i = 0; i < 3; ++i) {
    pid = fork();
    if (pid > 0) {   /* I am the parent, create more children */
      continue;
    } else if (pid == 0) { /* I am a child, get to work */
      printf("Hallo, ich bin der %d. Prozess mit der PID: %d.\n\n", i+1, getpid());
      int j;
      for(j = 0; j < 3; j++) {
        //Critical section
        P(sem_id);
        printf("%d betrete kritischen Bereich.\n\n", getpid());
        sleep(1);
        printf("%d verlasse kritischen Bereich.\n\n", getpid());
        V(sem_id);
      }
      //Uncritical section
      printf("%d betrete unkritischen Bereich.\n\n", getpid());
      sleep(1);
      printf("%d verlasse unkritischen Bereich.\n\n", getpid());

      break;
    } else {
      perror("fork error\n");
      exit(1);
    }
  }

  return EXIT_SUCCESS;
}
