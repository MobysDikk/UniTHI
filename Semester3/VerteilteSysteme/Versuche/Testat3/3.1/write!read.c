#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>


void setVal(int sem_id, int init_val) {
  
  //semctl(int semid, int semnum, int cmd, union semnum arg);
  //semid = die ID des Semaphoren(Semaphorensatzes)
  //semnum = Semaphor Nummer welche behandelt werden soll
  //cmd = commando => SETVAL setzt Value auf einen bsetimmten wert
  //semun arg = Semaphore wird auf wert arg gesetzt


  semctl(sem_id, 0, SETVAL, init_val);
}

int getVal(int sem_id) {
  return semctl(sem_id, 0, GETVAL);
}

void P(int sem_id) {
  struct sembuf sops[1];
  sops[0].sem_num = 0 ;
  sops[0].sem_op = -1;
  sops[0].sem_flg = SEM_UNDO;
  semop(sem_id, sops, 1);
}

void V(int sem_id) {
  struct sembuf sops[1];
  sops[0].sem_num = 0;
  sops[0].sem_op = 1;
  sops[0].sem_flg = SEM_UNDO;
  semop(sem_id, sops, 1);
}

void read(int mutex, int reader, int writer, int pid) {

  for(int i =0;i<3;i++){
  P(mutex);                  // mutex semaphore um reader counter "nur um eins" zu erhöhen
  V(reader);                 // reader Semaphore/counter wird 1++
    if(getVal(reader) == 1){ // falls ein leser da ist blockier schreiber
      P(writer);
    }

  V(mutex);

  printf(" READER %d starts reading.\n", pid);
  sleep(1);
  printf(" READER %d stops reading.\n", pid);

  P(mutex);
  P(reader);
    if(getVal(reader) == 0){  // falls kein leser mehr da ist ist schreiben wider erlaubt
      V(writer);
    }
  V(mutex);
  sleep(2);
  }
}

void write(int mutex, int reader, int writer, int pid) {
 
  for(int i =0; i<3;i++){
  P(writer);
    printf("WRITER %d starts writing.\n", pid);
    sleep(1);
    printf("WRITER %d stops writing.\n", pid);
  V(writer);
  }
}

int main (void){  
  
  // Key 1 + Semaphore 1 erstellt und initialisiert
  key_t mutex_key = ftok("/tmp", 1);
  if(mutex_key < 0) {
    perror("Creating mutex key did not work.");
    exit(1);
  }

  int mutex = semget(mutex_key, 1, IPC_CREAT | 0666);
  setVal(mutex, 1);
  
  // Key 2 + Semaphore 2 erstellt und initialisiert
  key_t reader_key = ftok("/tmp", 2);
  if(reader_key < 0) {
    perror("Creating a new key for the 'writer' has not succeeded.");
    exit(1);
  }

  int reader = semget(reader_key, 1, IPC_CREAT | 0666);
  setVal(reader, 0);

  // Key 3 + Semaphore 3 erstellt und initialisiert
  key_t writer_key = ftok("/tmp", 3);
  if(writer_key < 0) {
    perror("Creating a new key for the 'reader' has not succeeded.");
    exit(1);
  }

  int writer = semget(writer_key, 1, IPC_CREAT | 0666);
  setVal(writer, 1);
 


  int i, pid;
  //Creating n childs
  for (i = 0; i < 7; ++i) {
    pid = fork();
    if (pid > 0) {   /* Vater macht nichts */
    } else if (pid == 0) { 
      if(i < 5) {
        //5 readers
        read(mutex, reader, writer, i+1);
      } else {
        //2 writers
        sleep(2);
        write(mutex, reader, writer, i-4);
      }
      break; // alternativ auch mit break möglich !!
    } else {
      perror("fork error\n");
      exit(1);
    }
  }
  
  exit(0);
}
