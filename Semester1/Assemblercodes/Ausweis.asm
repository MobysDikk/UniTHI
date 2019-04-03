global main 
extern printf
extern scanf
extern atoi
segment .data
global Multstr

printAusweis: db 10, "Die Vollständige Nummer lautet: %s%d", 0
printZiffer : db 10, "Die Prüfziffer lautet: %d", 10,10,0
MultArray:    db 7,3

segment .bss

segment .text

;-------------------------------------------------------------------------------------
main:

mov ecx, 9          ; Zählregister auf 9 setzen
mov esi, 0          ; da zwischen ergebnisse alle auf esi addiert werden asi auf 0 setzen
Schleife:
mov eax, esp
add eax, 8
mov eax, [eax]
add eax, 4
mov eax, [eax]      ; Speicher Adresse string

cmp ecx, 8          ; jdeder jump befehl schiebt deine stelle im Eingabe Array weiter (Konsoleneingabe)
je S1
cmp ecx, 7
je S2
cmp ecx, 6
je S3
cmp ecx, 5
je S4
cmp ecx, 4
je S5
cmp ecx, 3
je S6
cmp ecx, 2
je S7
cmp ecx, 1
je S8
Weiter:



mov ebx, [eax]      ; die letzten zwei Zahlen im Register (al) ist der gesuchte Wert
mov eax, 0          ; um in al, bl übertragen zu können muss zuerst das gesammmt Register auf Null gesetzt werden!
mov al, bl          ; Ascii Wert liegt nun in eax z.B: A=65

call Erkennung      ; Schleife zur erkennung ob Buchstabe oder zahl

call Berechnung

add esi, eax        ; alle Zwischenergebnisse werden in esi aufsummiert

dec ecx             ; das ganze wird 9 mal widerholt bevor es auf Ausgabe springt
cmp ecx, 0
je Ausgabe
jne Schleife



Ausgabe:
mov ebx, 10
mov eax, esi
div ebx             ; durch 10 geteilt damit die letzte Ziffer als rest automatisch in edx landet


mov eax, esp        ; Adresse des Strings holen um mit printAusweis ausgeben zu können
add eax, 8
mov eax, [eax]
add eax, 4
mov eax, [eax]
push edx            ; pushen der Prüfziffer
push eax            ; pushen der String Adresse
push printAusweis   ; Ausweisnummer und Prüfziffer werden ausgegeben
call printf
add esp, 8          ; esp auf Prüfziffer legen

push printZiffer    ; Ausgabe der Prüfziffer
call printf
add esp, 8
ret
;------------------------------------------------------------------------------------

Erkennung:

mov ebx, eax
sub ebx, 60   
cmp ebx, 0
jl Zahl         ; liegt Ascii -60 unter null ist es eine Zahl. Darüber ein Buchstabe
jg Buchstabe
Zahl:
sub eax, 48     ; Ascii minus 48 ergiebt gesuchte Zahl
ret
Buchstabe:
sub eax, 55
ret
;----------------------------------------------------------------------------------- 

Berechnung:
cmp ecx, 9
je Mul7
cmp ecx, 6
je Mul7
cmp ecx, 3
je Mul7
cmp ecx, 8
je Mul3
cmp ecx, 5
je Mul3
cmp ecx, 2
je Mul3
cmp ecx, 7
je Mul1
cmp ecx, 4
je Mul1
cmp ecx, 1
je Mul1

Mul7:
mov edx, 0
mov dl, [MultArray]  ; erster wert von MultArray wird in dl gespeichert = 7
mul edx              ; eax wird mit 7 multipliziert
ret

Mul3:
mov edx, 0
mov dl, [MultArray +1]  ; die zweiten werte werden mit der zweiten Zahl im MultArray multipliziert = 3
mul edx
ret
Mul1:                   ; da die Zahl mit 1 Multipliziert wird, wird eax ohne berrechnung weitergegeben 
ret                     
;--------------------------------------------------------------------------------------
S1: add eax, 1
    jmp Weiter
S2: add eax, 2
    jmp Weiter
S3: add eax, 3
    jmp Weiter
S4: add eax, 4
    jmp Weiter
S5: add eax, 5
    jmp Weiter
S6: add eax, 6
    jmp Weiter
S7: add eax, 7
    jmp Weiter
S8: add eax, 8
    jmp Weiter







