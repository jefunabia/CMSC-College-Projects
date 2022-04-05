TITLE LAB5 (EXE)
;---------------------------------------------
STACKSEG SEGMENT PARA 'Stack'
STACKSEG ENDS
;---------------------------------------------
DATASEG SEGMENT PARA 'Data'
    message1 db 13,10,"Enter first integer A: $"
    message2 db 13,10,"Enter second integer B: $"
    messageSum db 13,10, "Sum (A + B): $"
    messageDifference db 13, 10, "Difference (A - B): $"
    messageProduct db 13, 10, "Product (A * B): $"
    messageQuotient db 13, 10, "Quotient (A / B): $"
    num1 dd ?
    num2 dd ? 
    result dd ?
    a dw 1000
    b db 100
    c db 10
DATASEG ENDS
;---------------------------------------------
;---------------------------------------------
;   Display message MACRO
;---------------------------------------------
DISPLAY MACRO MSG
    mov ah, 09h
    lea dx, msg
    int 21h
ENDM
;---------------------------------------------
;   Change ASCII to decimal MACRO
;---------------------------------------------
toDecimal MACRO
    mov ah, 01h ;After the interrupt, AL contains the ASCII code of the input character. The character is echoed (displayed on the screen). Use function code 8 instead of 1 for no echo.
    int 21h
    sub al, 30h
ENDM
;---------------------------------------------
;   Place each digit to its corresponding place MACRO
;---------------------------------------------
putToPlace MACRO p, n
    mul p
    add n, ax
ENDM
;---------------------------------------------
CODESEG SEGMENT PARA 'Code'
    ASSUME DS:DATASEG, CS:CODESEG
START:
    mov ax, dataseg
    mov ds, ax

    DISPLAY message1 ; to enter 1st number

    toDecimal
    mov ah, 0
    mul a ;1st digit
    mov num1, ax

    toDecimal
    putToPlace b, num1

    toDecimal
    putToPlace c, num1

    toDecimal
    mov ah, 0 ;4th digit
    add num1, ax

    DISPLAY message2 ; to enter 2nd num

    toDecimal
    mov ah, 0
    mul a ;1st digit
    mov num2, ax

    toDecimal
    putToPlace b, num2

    toDecimal
    putToPlace c, num2

    toDecimal
    mov ah, 0 ;4th digit
    add num2, ax

;---------------------------------------------
;   Display RESULTS
;---------------------------------------------
    ;public addFunction
    ;addFunction PROC FAR

    DISPLAY messageSum
    call addFunction

    DISPLAY messageDifference
    call subFunction

    DISPLAY messageProduct
    call mulFunction

    DISPLAY messageQuotient


EXIT:
    mov ah, 4ch
    int 21h

;---------------------------------------------
;   Arithmetic FUNCTIONS
;---------------------------------------------
addFunction PROC NEAR
    mov  ax,num2
    add  ax,num1

    xor  cx,cx   ;Counts the digits
    mov  bx,10   ;Fixed divider
    
    more:
    xor  dx,dx   ;Word division needs to zero DX!
    div  bx
    push dx      ;Remainder [0,9]
    inc  cx      ;One digit more
    test ax,ax
    jnz  more    ;Continue until AX is empty
    
    next:
    pop  dx
    add  dl,48   ;Turn into a character, from [0,9] to ["0","9"]
    mov  ah,02h  ;DOS DisplayCharacter function
    int  21h
    loop next    ;Continue for all digits (*)

    ret
    
mulFunction PROC NEAR
    mov  ax,num2
    mul  num1

    xor  cx,cx   ;Counts the digits
    mov  bx,10   ;Fixed divider
    
    more2:
    xor  dx,dx   ;Word division needs to zero DX!
    div  bx
    push dx      ;Remainder [0,9]
    inc  cx      ;One digit more
    test ax,ax
    jnz  more2    ;Continue until AX is empty
    
    next2:
    pop  dx
    add  dl,48   ;Turn into a character, from [0,9] to ["0","9"]
    mov  ah,02h  ;DOS DisplayCharacter function
    int  21h
    loop next2    ;Continue for all digits (*)

    ret

subFunction PROC NEAR
    mov  ax,num1
    sub  ax,num2

    xor  cx,cx   ;Counts the digits
    mov  bx,10   ;Fixed divider
    
    more3:
    xor  dx,dx   ;Word division needs to zero DX!
    div  bx
    push dx      ;Remainder [0,9]
    inc  cx      ;One digit more
    test ax,ax
    jnz  more3    ;Continue until AX is empty
    
    next3:
    pop  dx
    add dl,48   ;Turn into a character, from [0,9] to ["0","9"]
    mov  ah,02h  ;DOS DisplayCharacter function
    int  21h
    loop next3    ;Continue for all digits (*)

    ret

CODESEG ENDS
END START