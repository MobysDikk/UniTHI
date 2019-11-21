#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <sys/stat.h>
#include <fcntl.h>

void init(int sem_id, int init_val){

  //semctl(int semid, int semnum, int cmd, union semun arg);
  //semid = die ID des Semapohr (Semaphorensatzes)
  //semnum = Semaphor Nummer welche behandlet werden soll
  //cmd = SETVAL => steuerkommando das die Semaphore auf einen anfgangswert initialisert
  //semun arg = Semaphore wird auf wert arg gesetzt 

  semctl(sem_id, 0, SETVAL, init_val);
}

int main(){

  // Keys für die Semaphoren erstellen

  key_t writerKey = ftok("/tmp",1);
  if(writerKey < 0){
    perror("Key für writer nicht erzeugt!\n");
    exit(1);
  }

  key_t readerKey = ftok("/tmp",2);
  if(readerKey < 0){
    perror("Key für reader nicht erzeugt!\n");
    exit(1);
  }

  key_t mutexKey = ftok("/tmp",3);
  if(mutexKey < 0){
     perror("Key für mutex nicht erzegt!\n");
     exit(1);
  }

  // 3 Semaphoren erzeugen
  
    
  int writer_id = semget(writerKey,1,IPC_CREAT | 0666);
  if(writer_id < 0){
    perror("Writer Semaphore konnte nicht erzeugt werden!\n");
  }

  int reader_id = semget(readerKey,1,IPC_CREAT | 0666);
  if(reader_id < 0){
    perror("Reader Semaphore konnte nicht erzeugt werden!\n");
  }

  int mutex_id = semget(mutexKey,1,IPC_CREAT | 0666);
  if(mutex_id < 0){
    perror("Mutex Semaphore konnte nicht erzeugt werden!\n");
  }

  // Semaphoren initialisieren
  
  init(writer_id,1); // 1 = Semaphore kann passiert werden
  init(reader_id,0); // 0 = ??
  init(mutex_id,1);  // 1 = Semaphore kann passiert werden

  // Prozesse erzeugen 

  int pid;

  for(int i = 0; i<7; i++){
    pid = forck();
    
    if(pid>0){ // Eltern Prozess

    }else if ( pid == 0) {

      if(i<5){ //reader

      }else {  //writer

      }

     exit(0);

    } else {
      perror("fork fehlerhaft\n");
      exit(1);
      
    }

  }








}
