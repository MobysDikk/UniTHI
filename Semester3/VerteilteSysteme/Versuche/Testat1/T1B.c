#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <unistd.h>
#include <sys/types.h>

void initSema (int sem_id, int sem_num){

  union semnum {      // Eine Union belegt mindestens so viel Speicher, wie ihre Größte Komponente
    int val;
    struct semid_ds *buf;
    unsigned short *array;
  } argument;
  argument.val=1;   // 1 = erster Ankommende Process wird nicht geblockt
  if(semctl(sem_id,sem_num,SETVAL,1)<0){

    perror("Semaphore konnte nicht erstellt werden!");

  }

}

// P und V Operatonene. Bei valid = -1 wird eine P Operatoin durchgeführt. Bei valid = 1 eine V Operation
void semGate(int sem_id, int sem_num, int valid){

  struct sembuf semaphore;

  semaphore.sem_num= sem_num;
  semaphore.sem_op= valid;
  semaphore.sem_flg=~(IPC_NOWAIT|SEM_UNDO); 

  if(semop(sem_id, &semaphore,1)<0){ 
    if( valid == -1 ){
       perror("Fehler in P Operatoin");
       exit(1);

     }

    if(valid == 1 ){
       perror("Fehler in V Operation");
      exit(1);

     }
  }
}

void loop (int pidNr, int sem_id){

  for(int i =0; i<3; i++){
    semGate(sem_id, 0, -1);    
    printf("Prozess %d durchläuft gerade eienen Kritischen Prozess \n", pidNr);
    sleep(1);
    printf("Prozess %d verlaess gerade eienen Kritischen Prozess \n", pidNr);
    semGate(sem_id, 0, 1);
  }
}

int main(void){

// Erzeugung eines eindeutigen Schlüssels um ein Semaphor erstellen zu können
// ftok wandelt den Pfadnamen einer existierenden Date zusammen mit einem Projektbezeichner
// in einen IPC-Systemschlüssel um

  key_t semKey = ftok("/tmp", 1);
  if(semKey < 0){
    perror("ftok war fehlerhaft");
    exit(1);
  }

// Erzeugung einer Semaphore

  int sem_id = semget(semKey, 1, IPC_CREAT | 0666);
  if(sem_id < 0){
    perror("Semaphor konnte nicht erstellt werden");
    exit(1);
  }

// Initialisierung der Semaphore

initSema(sem_id, 0);


int pid;

for(int i =0; i<3;i++){

  pid = fork();

  int pidNr=getpid();

//Kindprozesse erzeugen
  if(pid == 0){

    printf("Ich bin Prozess Nummer %d mit der pid = %d \n", i+1, pidNr);
    loop(pidNr, sem_id);
    printf("Prozess %d verlässt einen kritischen Bereich \n", pidNr);
    exit(0);
  } else if (pid < 0){
    
    perror("Erzeugung von Kindprozess fehlgeschlagen");
    exit(1);
  } else if(pid >0){
    printf("Sohn erzeugt\n");
  }


}


}
