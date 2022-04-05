TITLE LAB3-1 (EXE)
;---------------------------------------------
STACKSEG SEGMENT PARA 'Stack'
STACKSEG ENDS
;---------------------------------------------
DATASEG SEGMENT PARA 'Data'
    message1 db 'Too hot! Give yourself a shower.$'
    message2 db "You're good. Stay alert.$"
    message3 db "Oh no! You're freezing.$"
    message4 db "Invalid input.$"
    temp_reading dw -273
    alertA dw 80
    alertC dw 30
    lowerLimit dw -273
    upperLimit dw 100

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

    ; Move temp_reading to ax to compare
    mov ax, temp_reading

   ; If out of bounds with the upperLimit, display error message
    cmp ax, upperLimit
    jg limit

    ; If out of bounds with the lowerLimit, display error message
    cmp ax, lowerLimit
    jl limit

    ; Compare alertA with current temp reading. If greater than or equal to 80, display tooHot
    cmp ax, alertA
    jge tooHot

    ; Compare alertC with current temp reading. If less than or equal to 30, display tooCold
    cmp ax, alertC
    jle tooCold

    ; If neither, display youreGood

 
youreGood:
    mov ah, 09h
    lea dx, message2
    int 21h
    jmp EXIT

tooHot:
    mov ah, 09h
    lea dx, message1
    int 21h
    jmp EXIT

tooCold:
    mov ah, 09h
    lea dx, message3
    int 21h
    jmp EXIT

limit:
    mov ah, 09h
    lea dx, message4
    int 21h
    jmp EXIT

EXIT:
    mov ah, 4ch
    int 21h

CODESEG ENDS
END START