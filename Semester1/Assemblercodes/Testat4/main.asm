
global main
extern scanf
extern printf
extern pwd
extern right

section .data

M1: db 10, "Gib das richtige Passwort ein um den Text zu entschlüsseln: ",10,0
Intvar: times 4 db 0
Intval db"%d",0

mempos_1: db 47,109,76,9,5,76,67,5,92,74,80,5,70,68,75,5,87,64,68,65,5,81
mempos_2: db 77,76,86,5,77,76,66,77,73,92,5,64,75,70
mempos_3: db 87,92,85,81,64,65,5,81,64,93,81,5,81,77
mempos_4: db 64,75,5,92,74,80,87,5,65,74,75,64,11,47,37,75,64,5,67,80
mempos_5: db 75,67,68,70,81,5,76,86,81,5,81,77,68,81,5,92,74
mempos_6: db 10,0,0,0,0,0,0,0,0,0,0,0,0,0,0
mempos_7: db "%d",10,0

section .text


main:

Nochmal:
 push mempos_1
; call printf
 add esp,4
         
 
 push 65 ;87 zu viel
 push mempos_2-0x16

;mov eax,1
;cmp eax, 0
;je L
; call pwd
; add esp,8

;push M1
;call printf
;add esp,4

;push Intvar
;push Intval
;call scanf
;add esp, 8

;mov eax,[Intvar]
;sub eax, 2879
;cmp eax, 0
;je Nochmal
;jne Ende
;L:
call right
add esp, 8
;jmp Ende

Ende:

; push mempos_3-0x24
; call printf
 ;add esp,4
 ret



