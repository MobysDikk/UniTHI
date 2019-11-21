#include <stdio.h>
main (argc, argv)
int argc;
char *argv[];
{
struct intpair numbers;
int result;
if (argc != 3) {
fprint(stderr,"%s:usage:%s num1 num2 \n",
argv[0], argv[0]);
exit(1);
}

numbers.a = atoi(argv[1]);
numbers.b = atoi(argv[2]);
result = add(numbers);
printf("The add (%d, %d) procedure returned %d\n", numbers.a,numbers.b,result);

result = multiply(numbers);
printf("The multiply (%d, %d) procedure 
returned%d\n",numbers.a,
numbers.b,result);

result = cube(numbers.a);
printf("The cube (%d) procedure returned %d\n",
numbers.a, result);
exit(0);
}
