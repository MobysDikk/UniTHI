#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

#define FILEPATH1 "/home/lars/mutex"
#define FILEPATH2 "/home/lars/reader"
#define FILEPATH3 "/home/lars/writer"

void setVal(int sem_id, int init_val) {
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
  P(mutex);
  //printf("%d a\n", getVal(reader));
    if(getVal(reader) == 0){
      P(writer);
    }
    setVal(reader, getVal(reader) + 1);
  V(mutex);

  printf("%d begins reading.\n", pid);
  sleep(1);
  printf("%d stops reading.\n", pid);
  // printf("reader = %d\n", getVal(reader));

  P(mutex);
 // printf("%d e\n", getVal(reader));
    if(getVal(reader) == 1){
      V(writer);
    }
    setVal(reader, getVal(reader) - 1);
  V(mutex);
}

void write(int mutex, int reader, int writer, int pid) {
  P(writer);
    printf("%d begins writing.\n", pid);
    sleep(1);
    printf("%d stops writing.\n", pid);
  V(writer);
}

int main (void){  
  //Creating the three semaphores
  if(open(FILEPATH1, O_CREAT|O_RDWR, 0666)<0) 
    printf("Open has not succeeded.\n");

  key_t mutex_key = ftok(FILEPATH1, 2);
  if(mutex_key < 0) {
    perror("Creating a new key for the 'mutex' has not succeeded.");
    exit(1);
  }

  int mutex = semget(mutex_key, 1, IPC_CREAT | 0666);
  setVal(mutex, 1);

  if(open(FILEPATH2, O_CREAT|O_RDWR, 0666)<0) 
    printf("Open has not succeeded.\n");

  key_t reader_key = ftok(FILEPATH2, 2);
  if(reader_key < 0) {
    perror("Creating a new key for the 'writer' has not succeeded.");
    exit(1);
  }

  int reader = semget(reader_key, 1, IPC_CREAT | 0666);
  setVal(reader, 0);


  if(open(FILEPATH3, O_CREAT|O_RDWR, 0666)<0) 
    printf("Open has not succeeded.\n");

  key_t writer_key = ftok(FILEPATH3, 2);
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
    if (pid > 0) {   /* I am the parent, create more children */
    } else if (pid == 0) { /* I am a child, get to work */
      if(i < 5) {
        //5 readers
        //Critical section
        read(mutex, reader, writer, getpid());
      } else {
        //2 writers
        //Critical section
        sleep(2);
        write(mutex, reader, writer, getpid());
      }
      break;
    } else {
      perror("fork error\n");
      exit(1);
    }
  }
  
  exit(0);
}
