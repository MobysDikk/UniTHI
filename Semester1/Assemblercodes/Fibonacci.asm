section .data

msg1: db "Dein Ergebnis lautet = ", "%d", 10,0
msg2: db "Die Zahl muss größer als Null sein", 10,0


section .text

global main
extern printf

main:
      push ebp
      mov ebp, esp

      mov ecx, 0
      cmp ecx, 0
      je Null
      mov ebx, 0
      dec ecx
      cmp ecx, 0
      je Ergebniss2
      mov edx, 1
      dec ecx
      cmp ecx, 0
      je Ergebniss3

      Schleife: add ebx, edx
                mov eax, ebx
                dec ecx
                cmp ecx, 0
                je Ergebniss
                add edx, ebx
                mov eax, edx
                dec ecx
                cmp ecx, 0
                je Ergebniss
                jne Schleife

      Null: push msg2
            call printf
            jmp Ende
      Ergebniss: jmp Weiter
      Ergebniss2: mov eax, ebx
                  jmp Weiter
      Ergebniss3: mov eax, edx
                  jmp Weiter
      Weiter: push eax

      push msg1
      call printf 
Ende:
      mov esp, ebp
      pop ebp
      ret

