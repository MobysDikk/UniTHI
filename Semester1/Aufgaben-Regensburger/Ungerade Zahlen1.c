
#include <stdio.h>

int UngeradeZahlen(int a, int b);


int main()
{
int start, ende;

printf("Geben sie bitte eine untere Grenze ein\n");
scanf("%d", &start);
printf("Geben sie bitte eine oberen Grenze ein\n");
scanf("%d", &ende);

if (start>=ende)
{
    while (start>=ende)
    {
    printf("Die Obere Grenze muss einen Größeren Wert haben als %d\n", start);
    printf("Geben sie eine neuen Wert ein\n");
    scanf("%d", &ende);
    }
}
printf("Das Interwall geht von %d bis %d\n\n", start, ende);

UngeradeZahlen(start, ende);


return 0;
}


int UngeradeZahlen(int a, int b)
{
printf("Die Ungerade Zahlen Lauten:\n");
for (a;a<b;a++)
{
    a%2;
    if (a%2==1)
    {
  printf("%d, ", a);
    }
}
    return 0;
}
