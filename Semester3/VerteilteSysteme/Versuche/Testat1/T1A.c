#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <unistd.h>

// Da keine Semaphore initialiseirt wurden und somit die Prozesse nicht Synchronisisert sind,
// greifen die processe mehrfach auf eine Ressource zu 
// (in dem Fall die Schleife mit sleep). Somit Überschneiden sie sich und Teminieren nicht
// obwohl der Kritische Bereich wieder verlassen worden ist.

int main(void){

int pid;

for(int i =0; i<3;i++){

  pid = fork();

//3 Kindprozesse erzeugen
  if(pid == 0){

    printf("Ich bin Prozess Nummer %d mit der pid = %d \n", i+1, getpid());
    for(int l=0; l<3;l++){
      printf("Prozess %d mit pid = %d duxrchläuft gerade einen kritischen Bereich \n",i+1,getpid() );
      sleep(1);
    }
      printf("Prozess %d mit pid = %d verlässt einen kritischen Bereich\n\n ", i+1,getpid());
      sleep(1);


    exit(0);
  } else if (pid < 0){
    
    perror("Erzeugung von Kindprozess fehlgeschlagen\n ");
    exit(1);
  }
}
}
