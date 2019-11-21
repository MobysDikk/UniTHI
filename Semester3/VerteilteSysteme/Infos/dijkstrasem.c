#include "dijkstrasem.h"

// These need to be initialized !!
int psems_id;
extern int psems_count;

union semun {
  int              val;    /* Value for SETVAL */
  struct semid_ds *buf;    /* Buffer for IPC_STAT, IPC_SET */
  unsigned short  *array;  /* Array for GETALL, SETALL */
  struct seminfo  *__buf;  /* Buffer for IPC_INFO
			      (Linux-specific) */
};

int psem_init(int size, key_t key) {
  int id = semget(key, size, IPC_CREAT | IPC_EXCL | 0666 );
  psems_id = id;
  printf("id: %d\n", id);
  fflush(stdout);
  for(int i=0; i<psems_count; i++) {
    union semun un;
    un.val = 1;
    semctl(id, i, SETVAL, un);
  }
  return id;
}

int psems_close(int id) {
  return semctl(id,0,IPC_RMID);
}

int psem_P(int size, int* semas) {
  struct sembuf sb[size];
  for (int i = 0; i < size; i++) {
    sb[i].sem_num=semas[i];
    sb[i].sem_op=-1;
    sb[i].sem_flg=~(IPC_NOWAIT|SEM_UNDO);
  }
  return semop(psems_id, sb, size);
  
}

int psem_V(int size, int* semas) {
  struct sembuf sb[size];
  for (int i = 0; i < size; i++) {
    sb[i].sem_num=semas[i];
    sb[i].sem_op=1;
    sb[i].sem_flg=~(IPC_NOWAIT|SEM_UNDO);
  }
  return semop(psems_id, sb, size);
}

char* psems_toString() {
  char *ret = malloc(sizeof(char)*50);
  union semun una;
  una.array = malloc(sizeof(int)*5);

  semctl(psems_id, 0, GETALL, una);
  strcat(ret, "semaphor status: ");
  for(int i=0; i<psems_count; i++) {
    char str[3];
    sprintf(str, "%d ", una.array[i]);
    strcat(ret, str);
  }
  strcat(ret, "\n");
  return ret;
}

void psems_print() {
  char *ret = malloc(sizeof(char)*50);
  ret = psems_toString();
  printf("%s", ret);
}

int psem_get(int num) {
  return semctl(psems_id, num, GETVAL);
}
