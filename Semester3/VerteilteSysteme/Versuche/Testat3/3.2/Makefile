CC = gcc -Wall 

math.h math_svc.c math_clnt.c math_xdr.c:	math.x
	rpcgen   -DTEST math.x

math_server:	math_svc.o math_xdr.o serv.o 
	$(CC) -o math_server math_svc.o math_xdr.o serv.o -lnsl

remo_math:	math_clnt.o math_xdr.o client.o
	$(CC) -o remo_math math_clnt.o math_xdr.o client.o -lnsl


all: math_server remo_math

clean:	
	rm -f math.h math_svc.c math_clnt.c math_xdr.c math_server math_svc.o math_xdr.o serv.o remo_math math_clnt.o client.o 
