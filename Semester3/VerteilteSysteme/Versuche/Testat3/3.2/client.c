#include <stdio.h>
#include <rpc/rpc.h>
#include "math.h"

main (argc, argv)  
     int argc;
     char * argv[];

{
  CLIENT * c1;			/* client handle */
  intpair numbers;
  int *result;
  if (argc != 4)
    {
      fprintf (stderr, "%s:usage:%s server num1 num2 \n", argv[0], argv[0]);
      exit (1);
    }
  c1 = clnt_create (argv[1], MATHPROG, MATHVERS, "tcp");
  if (c1 == NULL)
    {
      clnt_pcreateerror (argv[1]);
      exit (1);
    }
  numbers.a = atoi (argv[2]);
  numbers.b = atoi (argv[3]);
  result = add_1 (&numbers, c1);
  if (result == NULL)
    {
      clnt_perror (c1, "add_1");
      exit (1);
    }
  printf ("The add (%d, %d) procedure returned %d\n", numbers.a, numbers.b,
	   *result);
  result = multiply_1 (&numbers, c1);
  if (result == NULL)
    {
      clnt_perror (c1, "multiply_1");
      exit (1);
    }
  printf ("The multiply (%d, %d) procedure returned %d\n", numbers.a,
	   numbers.b, *result);
  result = cube_1 (&numbers.a, c1);
  if (result == NULL)
    {
      clnt_perror (c1, "cube_1");
      exit (1);
    }
  printf ("The cube (%d) procedure returned  %d\n", numbers.a, *result);
  exit (0);
}


