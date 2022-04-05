TITLE LAB2-3 (EXE)
;---------------------------------------------
DATASEG SEGMENT PARA 'Data'
PROMPT1 DB 0AH, 0DH, 'Enter first integer A: $'
PROMPT2 DB 0AH, 0DH, 'Enter second integer B: $'
OUTPUT1 DB 0AH, 0DH, 'Sum (A+B): $'
OUTPUT2 DB 0AH, 0DH, 'Difference (A-B): $'
OUTPUT3 DB  0AH, 0DH, 'Product (A*B): $'
OUTPUT4 DB  0AH, 0DH, 'Quotient (A/B): $'
NUM1 DW ?
NUM 2 DW ?
a dw 1000
b db 100
c db 10
SUM DW ?, '$'

DATASEG ENDS
;---------------------------------------------
CODESEG SEGMENT PARA 'Code'
  ASSUME DS:DATASEG, CS:CODESEG
START:

mov ax, dataseg ; start
mov ds, ax
    
mov ah, 09H ; display prompt1
lea dx, prompt1
int 21h

mov ah, 0ah ; get 1st num
int 21h
mov bl,al   ;save the value from input
mov num1,ax


mov ah, 09H ; display prompt2
lea dx, prompt2
int 21h

mov ah,0ah ; get 2nd num
int 21h
mov bh,al   ;save the value from second input
mov num2,ax

; addition proper
    sub num2,'0'
    sub num1,'0'
    mov ax,num1        ;move num1 into ax
    add ax,num2         ;add first and second numbers together
    mov sum,ax           ;move the total sum of numbers in sum
    add sum,'0'
    
;Print Sum
    mov ah,09h
    lea dx, sum + 2  ; print result
    int 21h

        mov ah,4ch
        int 21h
 

CODESEG ENDS
END START