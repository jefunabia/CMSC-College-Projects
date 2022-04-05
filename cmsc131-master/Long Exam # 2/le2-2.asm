TITLE LE2-2 (EXE)
;---------------------------------------------
STACKSEG SEGMENT PARA 'Stack'
STACKSEG ENDS
;---------------------------------------------
DATASEG SEGMENT PARA 'Data'
    prompt DB 0AH, 0DH, 'Enter String: $'
    stringInput DB 50 dup(?)
DATASEG ENDS
;---------------------------------------------
CODESEG SEGMENT PARA 'Code'
    ASSUME DS:DATASEG, CS:CODESEG

    nextChar MACRO
        mov [si], al
        inc si
    ENDM

START:  
    mov ax, dataseg
    mov ds, ax
  
    mov ah, 09h ;display prompt
    lea dx, prompt
    int 21h

    lea si, stringInput 
    mov ah, 01h ;char read

READ:
    int 21H
    mov bl, al

    cmp al, 0DH ;carriage return
    je  DISPLAY
    
    cmp al, "0"
    jb typeSpecial

    cmp al, "9"
    jbe typeDigit

    cmp al, "A"
    jb typeSpecial

    cmp al, "Z"
    jbe typeLetter

    cmp al, "{"
    jae typeSpecial

    cmp al, "a"
    jb typeSpecial

    cmp al, "z"
    jbe typeLetter

typeLetter:
    xor al, 20H ;swap
    ;The difference between upper and lower case in ASCII characters is bit 5.
    ;"D" = 44H = 0100 0100
    ;"d" = 64H = 0110 0100
    ; 20H = 0010 0000
    nextChar
    ;CMP BL,0DH
    jmp READ

typeDigit:
    nextChar        
    ;cmp bl, 0DH
    jmp READ

typeSpecial:
    mov al, " "
    nextChar
    ;cmp bl, 0DH
    jmp READ

DISPLAY:
    mov al,'$'
    mov [si], al
    
    lea dx, stringInput
    mov ah, 09H
    int 21H

EXIT:
    mov ah, 4ch
    int 21h

CODESEG ENDS
END START