TITLE LE2-1 (EXE)
;---------------------------------------------
STACKSEG SEGMENT PARA 'Stack'
STACKSEG ENDS
;---------------------------------------------
DATASEG SEGMENT PARA 'Data'
    prompt1 db 0AH, 0DH, 'Enter Character: $'
    inputChar dw 0ah, 0dh, ?, '$'
    space db '          $'
DATASEG ENDS
;---------------------------------------------
CODESEG SEGMENT PARA 'Code'
    ASSUME DS:DATASEG, CS:CODESEG

nextLine MACRO
    mov dl, 0dh
    int 21h
    mov dl, 0ah
    int 21h
    ENDM

START:  
    mov ax, dataseg
    mov ds, ax

MAIN:
;   display prompt
    mov ah, 09h
    lea dx, prompt1
    int 21h

;   get input
    mov ah, 10h
    int 16h
    mov inputChar, al

   ; display input character
    mov ah, 02h
    mov dx, inputChar
    int 21h

    nextLine
    nextLine
    
;   declare variables
; 10 columns
; 5 spaces
; 10 columns
; 5 rows
    mov cx, 2
loop1:
    push cx
    mov cx, 5 ;rows

loop2:
    push cx
    mov cx, 10  ;columns
    
displayCharLoop:
    mov ah, 02h
    mov dx, inputChar
    int 21h

    loop displayCharLoop
    pop cx ;5
    
    mov ah, 09h
    lea dx, space
    int 21h

    push cx ;5
    mov cx, 10

loop3:
    mov ah, 02h
    mov dx, inputChar
    int 21h

    loop loop3
    pop cx ;5

    nextLine

    loop loop2

    pop cx

    nextLine
    
    loop loop1

EXIT:
    mov ah, 4ch
    int 21h

CODESEG ENDS
END START