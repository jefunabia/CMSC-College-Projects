TITLE LAB (EXE)
;---------------------------------------------
DATASEG SEGMENT PARA 'Data'
    ROW1 DB           '       *     *     * *     *  * * * *  *        *        * * * *$'
    ROW2 DB 0AH, 0DH, '       *    * *    * *     *  *        *        *        *$'
    ROW3 DB 0AH, 0DH, '       *   *   *   *  *    *  *        *        *        *$'
    ROW4 DB 0AH, 0DH, '       *  *     *  *   *   *  *        *        *        *$'
    ROW5 DB 0AH, 0DH, '       *  * * * *  *    *  *  * * *    *        *        * * *$'
    ROW6 DB 0AH, 0DH, ' *     *  *     *  *     * *  *        *        *        *$'
    ROW7 DB 0AH, 0DH, ' *     *  *     *  *      **  *        *        *        *$'
    ROW8 DB 0AH, 0DH, ' *     *  *     *  *       *  *        *        *        *$'
    ROW9 DB 0AH, 0DH, '  * * *   *     *  *       *  * * * *  * * * *  * * * *  * * * *$'
DATASEG ENDS
;---------------------------------------------
CODESEG SEGMENT PARA 'Code'
  ASSUME DS:DATASEG, CS:CODESEG
START: ;label
    MOV AX, DATASEG
    MOV DS, AX

    MOV AX,0600H
    MOV BH,71H
    MOV CX,0000H
    MOV DX,184FH
    INT 10H

    MOV AH,02H
    MOV BH,00
    MOV DX,0803H
    INT 10H

    LEA DX, ROW1
    MOV AH, 9H
    INT 21H
  
    LEA DX, ROW2
    MOV AH, 9H
    INT 21H

    LEA DX, ROW3
    MOV AH, 9H
    INT 21H

    LEA DX, ROW4
    MOV AH, 9H
    INT 21H

    LEA DX, ROW5
    MOV AH, 9H
    INT 21H

    LEA DX, ROW6
    MOV AH, 9H
    INT 21H

    LEA DX, ROW7
    MOV AH, 9H
    INT 21H

    LEA DX, ROW8
    MOV AH, 9H
    INT 21H

    LEA DX, ROW9
    MOV AH, 9H
    INT 21H

    MOV AH, 4CH
    INT 21H
CODESEG ENDS
END START