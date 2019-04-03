section .data

Ergebnis: db 10, "Die Zwischenergebnisse lauten:",10, 10,0
Ergebnisint: db "%d, ",0
Intvar: times 4 db 0
Intval: db "%d",0
Eingabe:  db 10, "Bitte geben sie eine Zahl ein: " ,0
Letzte: db "%d",10,0

section .text

global main
extern printf
extern scanf

main:

push ebp
mov ebp, esp

push Eingabe
call printf

push Intvar
push Intval
call scanf

push Ergebnis
call printf



mov eax, [Intvar]
Anfang: mov ebx, eax

Testen: sub ebx, 2
	jz Gerade
	cmp ebx, 1
	je Ungerade
	jmp Testen
	
Gerade:	sar eax, 1
        push eax
        push Ergebnisint
        call printf
        mov eax, [esp+4]
        add esp, 8
	cmp eax, 2
	je Fertig
	jmp Anfang
	
Ungerade:
	mov ebx, 3
	mul ebx
	mov ebx, 1
	add eax, ebx
        push eax
        push Ergebnisint
        call printf
        mov eax, [esp+4]
        add esp, 8
        
	cmp eax, 2
	je Fertig
	jmp Anfang
	
Fertig: sub eax, 1
        push eax
        push Letzte
        call printf



mov esp, ebp
pop ebp
ret




