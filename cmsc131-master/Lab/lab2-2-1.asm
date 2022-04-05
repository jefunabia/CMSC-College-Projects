TITLE LAB2-2 (EXE)
;---------------------------------------------
DATASEG SEGMENT PARA 'Data'
;0AH requires 3 level format
NAME1       db  100         ;    MAX NUMBER OF CHARACTERS ALLOWED (100).
            db  ?           ;    NUMBER OF CHARACTERS ENTERED BY USER.
            db  100 dup(0)  ;    CHARACTERS ENTERED BY USER.

NAME2       db  100         ;    MAX NUMBER OF CHARACTERS ALLOWED (100).
            db  ?           ;    NUMBER OF CHARACTERS ENTERED BY USER.
            db  100 dup(0)  ;    CHARACTERS ENTERED BY USER.

NAME3       db  100         ;    MAX NUMBER OF CHARACTERS ALLOWED (100).
            db  ?           ;    NUMBER OF CHARACTERS ENTERED BY USER.
            db  100 dup(0)  ;    CHARACTERS ENTERED BY USER.

MESSAGE1 DB 0AH, 0DH, '> Enter first name: $'
MESSAGE2 DB 0AH, 0DH, '> Enter middle name: $'
MESSAGE3 DB 0AH, 0DH, '> Enter last name: $'
MESSAGE4 DB 0AH, 0DH, 'Your first name is $'
MESSAGE5 DB ', middle name is $'
MESSAGE6 DB ' and last name is $'
MESSAGE7 DB '. Welcome to assembly!$'
DATASEG ENDS
;---------------------------------------------
CODESEG SEGMENT PARA 'Code'
  ASSUME DS:DATASEG, CS:CODESEG
START:

  mov ax, dataseg ; start
  mov ds, ax
    
  mov ah, 09h ; display 1st prompt
  lea dx, message1 
  int 21h

  mov ah, 0ah ; get first name
  mov dx, offset name1
  int 21h
    
  ;Changing char(13) = carriage return / space to "$"
  mov si, offset name1 + 1 ; number of characters entered
  mov cl, [si] ; move length to cl.
  mov ch, 0 ;clear ch to use cx. 
  inc cx ; to reach chr(13).
  add si, cx ; now si points to chr(13).
  mov al, '$'
  mov [si], al ; replace chr(x) by '$'.

  mov ah, 09h
  lea dx, message2 ; display 2nd prompt
  int 21h

  mov ah, 0ah ; get middle name
  mov dx, offset name2
  int 21h
    
  ;Changing char(13) = carriage return / space to "$"
  mov si, offset name2 + 1 ; number of characters entered
  mov cl, [si] ; move length to cl.
  mov ch, 0      ; clear ch to use cx. 
  inc cx ; to reach chr(13).
  add si, cx ; now si points to chr(13).
  mov al, '$'
  mov [si] , al ; replace chr(13) by '$'.

  mov ah, 09h
  lea dx, message3    ; display 3rd prompt
  int 21h

  mov ah, 0ah ; get last name
  mov dx, offset name3
  int 21h
  
  ;Changing char(13) = carriage return / space to "$"
  mov si, offset name3 + 1 ; number of characters entered.
  mov cl, [si] ; move length to cl.
  mov ch, 0      ; clear ch to use cx. 
  inc cx ; to reach chr(13).
  add si, cx ; now si points to chr(13).
  mov al, '$'
  mov [si], al ; replace chr(13) by '$'.

  mov ah, 09h
  lea dx, message4 ; display first phrase
  int 21h
    
  mov ah, 09h
  mov dx, offset name1 + 2 ; display first name input
  int 21h

  mov ah, 09h
  lea dx, message5 ; display second phrase
  int 21h
    
  mov ah, 09h
  lea dx, name2 + 2 ; display middle name input
  int 21h

  mov ah, 09h
  lea dx, message6 ; display third phrase
  int 21h
    
  mov ah, 09h
  lea dx, name3 + 2; display last name input
  int 21h

  mov ah, 09h
  lea dx, message7 ; display last phrase
  int 21h

  mov ah, 4ch ; end
  int 21h

CODESEG ENDS
END START