TITLE LAB2-2 (EXE)
;---------------------------------------------
DATASEG SEGMENT PARA 'Data'
MESSAGE1 DB 0AH, 0DH, '> Enter first name: $'
FIRSTNAME DB ?, '$'
MESSAGE2 DB 0AH, 0DH, '> Enter middle name: $'
MIDDLENAME DB ?, '$'
MESSAGE3 DB 0AH, 0DH, '> Enter last name: $'
LASTNAME DB ?, '$'
MESSAGE4 DB 0AH, 0DH, 'Your first name is $'
MESSAGE5 DB ', middle name is $'
MESSAGE6 DB ' and last name is $'
MESSAGE7 DB '. Welcome to assembly!$'
DATASEG ENDS
;---------------------------------------------
CODESEG SEGMENT PARA 'Code'
  ASSUME DS:DATASEG, CS:CODESEG
START:
    MOV AX, DATASEG ;
    MOV DS, AX
    
    MOV AH, 09H
    LEA DX, MESSAGE1 ;Display Enter first name:
    INT 21H

    MOV AH, 10H ;Get first name
    INT 16H
    MOV FIRSTNAME, AL

    MOV AH, 09H
    LEA DX, MESSAGE2 ;Display Enter middle name:
    INT 21H

    MOV AH, 10H ;Get middle name
    INT 16H
    MOV MIDDLENAME, AL

    MOV AH, 09H
    LEA DX, MESSAGE3 ;Display Enter last name:
    INT 21H

    MOV AH, 10H ;Get last name
    INT 16H
    MOV LASTNAME, AL
    
    MOV AH, 09H
    LEA DX, MESSAGE4 ;Display 'Your first name is'
    INT 21H
    
    MOV AH, 09H
    LEA DX, FIRSTNAME ;Display input - first name
    INT 21H

    MOV AH, 09H
    LEA DX, MESSAGE5 ;Display ', middle name is'
    INT 21H
    
    MOV AH, 09H
    LEA DX, MIDDLENAME ;Display input - middle name
    INT 21H

    MOV AH, 09H
    LEA DX, MESSAGE6 ;Display ' and last name is'
    INT 21H
    
    MOV AH, 09H
    LEA DX, LASTNAME ;Display input - last name
    INT 21H

    MOV AH, 09H
    LEA DX, MESSAGE7 ; Display 'Welcome to assembly!'
    INT 21H

    MOV AH, 4CH ;
    INT 21H

CODESEG ENDS
END START