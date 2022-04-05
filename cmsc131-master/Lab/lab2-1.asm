TITLE LAB2-1 (EXE)
;---------------------------------------------
DATASEG SEGMENT PARA 'Data'
    ROW1 DB 0AH, 0DH, '              *     *     * *     *  * * * *  *        *        * * * *$' ;\nxt line \resets to left position of screen
    ROW2 DB 0AH, 0DH, '              *    * *    * *     *  *        *        *        *$'
    ROW3 DB 0AH, 0DH, '              *   *   *   *  *    *  *        *        *        *$'
    ROW4 DB 0AH, 0DH, '              *  *     *  *   *   *  *        *        *        *$'
    ROW5 DB 0AH, 0DH, '              *  * * * *  *    *  *  * * *    *        *        * * *$'
    ROW6 DB 0AH, 0DH, '       *      *  *     *  *     * *  *        *        *        *$'
    ROW7 DB 0AH, 0DH, '       *      *  *     *  *      **  *        *        *        *$'
    ROW8 DB 0AH, 0DH, '       *      *  *     *  *       *  *        *        *        *$'
    ROW9 DB 0AH, 0DH, '        * * *   *     *  *       *  * * * *  * * * *  * * * *  * * * *$'
DATASEG ENDS
;---------------------------------------------
CODESEG SEGMENT PARA 'Code'
  ASSUME DS:DATASEG, CS:CODESEG
START:
    mov ax, DATASEG ; start
    mov ds, ax

    mov ax, 0600h ; clear the screen
    mov bh, 02h ; black bg and green (02)
    mov cx, 0000h
    mov dx, 184fh
    int 10h

    mov ah, 02h  ; setting the cursor to the center
    mov bh, 00
    mov dx, 0700h
    int 10h

    ;Display all rows
    lea dx, row1
    mov ah, 9h
    int 21h
  
    lea dx, row2
    mov ah, 9h
    int 21h

    lea dx, row3
    mov ah, 9h
    int 21h

    lea dx, row4
    mov ah, 9h
    int 21h

    lea dx, row5
    mov ah, 9h
    int 21h

    lea dx, row6
    mov ah, 9h
    int 21h

    lea dx, row7
    mov ah, 9h
    int 21h

    lea dx, row8
    mov ah, 9h
    int 21h

    lea dx, row9
    mov ah, 9h
    int 21h

    mov ah, 4ch ; end
    int 21h

CODESEG ENDS
END START