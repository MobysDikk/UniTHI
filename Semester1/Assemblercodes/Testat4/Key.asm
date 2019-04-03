
global right
extern printf

section .data

key: db 0x25              ; i lost they key .. sad story tho
strd: db "%c",0
nl: db 10,0

section .text

right:   
  mov edx, [esp+4]    
    mov ecx, [esp+8]    

    schleife:
      mov eax, 0
        mov al, byte [edx]      
          xor al, [key]           
            mov byte [edx], al      

              push edx
                push ecx
                  push eax
                    push strd
                      call printf
                        add esp,4
                          pop eax
                            pop ecx
                              pop edx
                                

                                  INC edx
                                    DEC ecx
                                      
                                        TEST ecx,ecx
                                          JNZ schleife

                                            push nl
                                              call printf
                                                add esp,4
                                                  ret

                                                            
