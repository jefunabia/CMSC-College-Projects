TITLE LE2-3 (EXE)
;---------------------------------------------
DATA SEGMENT
    MSG1 DB 10,13,'Enter String: $'
    MSG4 DB 10,13,'Not a palindrome! $' 
    MSG5 DB 10,13, 'Palindrome! $'
    MSG6 DB 10,13, 'Reverse:  $' 
    P1 LABEL BYTE
    M1 DB 0FFH
    L1 DB ?
    P11 DB 0FFH DUP ('$')
    P22 DB 0FFH DUP ('$')
DATA ENDS 
;---------------------------------------------
DISPLAY MACRO MSG
    mov ah, 09h
    lea dx, msg
    int 21h
ENDM   
;---------------------------------------------
CODE SEGMENT
    ASSUME CS:CODE,DS:DATA

START:
        mov ax, data
        mov ds, ax                
               
        DISPLAY msg1    ;enter string
       
        lea dx, p1
        mov ah, 0ah    
        int 21h
         
        lea si, p11
        lea di, p22
       
        mov dl, l1
        dec dl
        mov dh, 0
        add si, dx
        mov cl, l1
        mov ch, 0
       
REVERSE:   
        mov al,[si]
        mov [di],al
        inc di
        dec si
        loop REVERSE
       
        ;display p22
                      
        lea si, p11
        lea di, p22   
       
        mov cl, l1
        mov ch, 0
       
CHECK:
        mov al,[si]
        cmp [di],al
        jne NOTPALIN

        inc di
        inc si
        loop CHECK
     
        DISPLAY msg5

        jmp EXIT

NOTPALIN:
        mov dx,13   ; display next line
        mov ah,2
        int 21h  

        mov dx,10
        mov ah,2
        int 21h
        
        DISPLAY p22 ; display reversed word
        
               
EXIT:   
        mov ah, 4ch
        int 21h

CODE ENDS
END START