struct intpair {
int a;
int b;
};

int add(intpair)
struct intpair intpair;
{
return (intpair.a+intpair.b);
}

int multiply(intpair)
struct intpair intpair;
{
return (intpair.a*intpair.b);
}

int cube (base)
int base;
{
return (base*base*base);
}
