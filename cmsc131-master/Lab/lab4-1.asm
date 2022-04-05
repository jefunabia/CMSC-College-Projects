TITLE LAB4-1 (EXE)
;---------------------------------------------
STACKSEG SEGMENT PARA 'Stack'
STACKSEG ENDS
;---------------------------------------------
DATASEG SEGMENT PARA 'Data'
DATASEG ENDS
;---------------------------------------------
CODESEG SEGMENT PARA 'Code'
    ASSUME DS:DATASEG, CS:CODESEG
START:  
    mov ax, dataseg
    mov ds, ax

; bx - rows
; cx - columns

MAIN:
    mov ah, 02h
    mov bx, 15 ; 15 rows

loop1:
    mov cx, 50 ; 50 columns
    mov dl, '*'

loop2:
    int 21h
    loop loop2

    mov dl, 0dh
    int 21h
    mov dl, 0ah
    int 21h
    
    dec bx ; since 1 row has been printed
    jne loop1

    
EXIT:
    mov ah, 4ch
    int 21h

CODESEG ENDS
END START