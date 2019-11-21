#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>


void init_sem(int sem_id, int nsem) {
  int i;
  for(i = 0; i < nsem; i++) {
    semctl(sem_id, i, SETVAL, 1);
  }
}

void P(int sem_id, int sem1) {
  struct sembuf sops[2];
  sops[0].sem_num = sem1 ;
  sops[0].sem_op = -1;
  sops[0].sem_flg = SEM_UNDO;
  sops[1].sem_num = (sem1 + 1) % 5;
  sops[1].sem_op = -1;
  sops[1].sem_flg = SEM_UNDO;

  //semop zum setzen und rücksetzen der Semaphoren
  //semop(id des Semaphorensatzes, Zeiger auf Array, Anzahl der Strukturen im sops array)
  if(0 != semop(sem_id, sops, 2))
    printf("P hat nicht funktioniert.\n");
}

void V(int sem_id, short sem1) {
  struct sembuf sops[2];
  sops[0].sem_num = sem1;
  sops[0].sem_op = 1;
  sops[0].sem_flg = SEM_UNDO;
  sops[1].sem_num = (sem1 + 1) % 5;
  sops[1].sem_op = 1;
  sops[1].sem_flg = SEM_UNDO;

  //semop zum setzten und rücksetzen der Semaphoren 
  //semop(id des Semaphorensatzes, Zeiger auf Array, Anzahl der Strukturen im sops array)
  
  if(0 != semop(sem_id, sops, 2))
    printf("V hat nicht funktioniert.\n");
}

int main (void){  
  //Creating n Childs
  int i, pid;

  // Erzeugen eines eindeutigen Schlüssels mit ftok(Pfad zu einem beliebigen Dokument,
  // Char aus dem der Schlüssel geformt wird)
  key_t key = ftok("/tmp", 1);
  if(key < 0) {
    perror("Erzeugen eines Schlüssels fehlgeschlagen.");
    exit(1);
  }

  // Eine Semaphore bzw Semaphorensatz wird angefordert
  // semget(erzeugter key, anzahl der Semaphoren im Semaphorensatz, Zugriffsrechte)
  int sem_id = semget(key, 5, IPC_CREAT | 0666);
  init_sem(sem_id, 5);

  for (i = 0; i < 5; ++i) {
    pid = fork();
    srand(i);//
   // int duration = rand() % 5 ;
    if (pid > 0) {  
  
    } else if (pid == 0) {
      //Kritischer Bereich
        P(sem_id, i);
        printf("Philosoph %d geht jetzt was essen.\n\n", i+1);
        int eatFor = abs(rand())%4; //
        sleep(eatFor);              //
        printf("Philosoph %d: geht wieder denken.\n\n", i+1);
        V(sem_id, i);
        exit(0);// 
     
    } else {
      perror("fork error\n");
      exit(1);
    }
  }
 
 return 0;
}
