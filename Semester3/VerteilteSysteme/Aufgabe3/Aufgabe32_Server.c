#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <math.h>
#include <rpc/rpc.h>

int main (void) {

  /* math.x */
  struct intpair {
    int a;
    int b;
  };

  program MATHPROG {
    version MATHVERS {
      int ADD(intpair) = 1;
      int MULTIPLY(intpair) = 2;
      int CUBE(int) = 3;
    }= 1;        /*version*/
  } = 0x2001000; /*id userdefined*/

  int * add_1_svc (pair, rqstp)
    intpair * pair;
    struct svc_req *rqstp; /*not necessary in this example*/

  return EXIT_SUCCESS;
}
