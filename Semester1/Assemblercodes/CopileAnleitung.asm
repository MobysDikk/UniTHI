So compilest du eine .asm Datei

nasm -f elf32 Datei.asm

Du bekommst dadurch eine .o File.

diesen in:

gcc -o Auführname .o 

-> Auführbare Datei

