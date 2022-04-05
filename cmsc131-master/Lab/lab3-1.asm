TITLE LAB3-2 (EXE)
;---------------------------------------------
STACKSEG SEGMENT PARA 'Stack'
STACKSEG ENDS
;---------------------------------------------
DATASEG SEGMENT PARA 'Data'
    prompt1 DB 0AH, 0DH,'> Enter character: $'
    inputChar DB 0AH, 0DH, ?, '$'
    upperCase DB 0AH, 0DH,'Uppercase Letter$'
    lowerCase DB 0AH, 0DH,'Lowercase Letter$'
    inputDelim DB '$'
    inputESC DB ' ESC$'
    digit DB 0AH, 0DH, 'Digit$'
    special DB 0AH, 0DH,'Special Character$'
    prompt2 DB 0AH, 0DH, 'Program exits...$'

DATASEG ENDS
;--------------MACRO-------------------------
BEGINM MACRO
    mov ax, dataseg
    mov ds, ax
    ENDM
;---------------------------------------------
CODESEG SEGMENT PARA 'Code'
    ASSUME DS:DATASEG, CS:CODESEG
START:
    BEGINM
loop1:
    ; Display prompt1
    mov ah, 09h
    lea dx, prompt1 
    int 21h

    ; Get input
    mov ah, 10h
    int 16h
    mov inputChar, al

    ; Display input character
    mov ah, 02h
    mov dx, inputChar
    int 21h

    ;-------Compare input if its either upperCase, lowerCase, digit or special--------------
    mov dl, inputChar

    ; Compare if input is equal to ESC = 1bh
    cmp dl, 1bh
    je typeESC

   ; Compare if input is before 0, to display special
    cmp dl, "0"
    jb typeSpecial

   ; Compare if input is less than or equal to 9, to display digit
    cmp dl, "9"
    jbe typeDigit

    ; Compare if input is before A, to display special
    cmp dl, "A"
    jb typeSpecial

    ; Compare if input is equal to $, to display $
    ;cmp dl, "$"
    ;je typeDelimiter

 
    ; Compare if input is before or equal to Z, to display upperCase
    cmp dl, "Z"
    jbe typeUpperCase


    ; Compare if input is before a, to display special
    cmp dl, "a"
    jb typeSpecial


    ; Compare if input is before or equal to z, to display lowerCase
    cmp dl, "z"
    jbe typeLowerCase

 
 
    ; Compare if input is before 0, to display special
    cmp dl, "{"
    jae typeSpecial


;-----Display input type & loop-----
typeDelimiter:
    mov ah, 09h
    lea dx, inputDelim
    int 21h
    jmp loop1
typeUpperCase:
    mov ah, 09h
    lea dx, upperCase
    int 21h
    jmp loop1
typeLowerCase:
    mov ah, 09h
    lea dx, lowerCase
    int 21h
    jmp loop1
typeDigit:
    mov ah, 09h
    lea dx, digit
    int 21h
    jmp loop1
typeSpecial:
    mov ah, 09h
    lea dx, special
    int 21h
    jmp loop1
typeESC:
    ; Display "ESC"
    mov ah, 09h
    lea dx, inputESC
    int 21h
    ; Display prompt2
    mov ah, 09h
    lea dx, prompt2
    int 21h   
    jmp exit
;-----END---Display input type-----

EXIT:
    mov ah, 4ch
    int 21h

CODESEG ENDS
END START