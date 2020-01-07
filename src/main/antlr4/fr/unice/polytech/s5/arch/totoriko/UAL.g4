grammar UAL;

program
    : (comment | lbl | instruction | EOL) +
    | EOF
    ;

///////////// LEXER RULES

LBRACKET
    : '['
    ;

RBRACKET
    : ']'
    ;

DOT
    : '.'
    ;

COMMA
    : ','
    ;

POUND
    : '#'
    ;
IMM
    : POUND ('-')? NUMBER
    ;

REGISTER
    : (R NUMBER)
    | S P
    ;

LSL: L S L (COND | S)?;
LSR: L S R (COND | S)?;
ASR: A S R (COND | S)?;
ADD: A D D (COND | S)?;
SUB: S U B (COND | S)?;
MOV: M O V (COND | S)?;
AND: A N D (COND | S)?;
EOR: E O R (COND | S)?;
ADC: A D C (COND | S)?;
SBC: S B C (COND | S)?;
ROR: R O R (COND | S)?;
TST: T S T (COND | S)?;
RSB: R S B (COND | S)?;
CMP: C M P (COND | S)?;
CMN: C M N (COND | S)?;
ORR: O R R (COND | S)?;
MUL: M U L (COND | S)?;
BIC: B I C (COND | S)?;
MVN: M V N (COND | S)?;
STR: S T R (COND)?;
LDR: L D R (COND)?;
BC: B COND?;

COND
    : (E Q)
    | (N E)
    | (C S)
    | (C C)
    | (M I)
    | (P L)
    | (V S)
    | (V C)
    | (H I)
    | (L S)
    | (G E)
    | (L T)
    | (G T)
    | (L E)
    | (A L)
    ;

NAME
    : [.a-zA-Z] [a-zA-Z0-9._]*
    ;

DOTS
    : ':'
    ;

fragment A
   : ('a' | 'A')
   ;

fragment B
    : ('b' | 'B')
    ;

fragment C
   : ('c' | 'C')
   ;


fragment D
   : ('d' | 'D')
   ;


fragment E
   : ('e' | 'E')
   ;


fragment F
   : ('f' | 'F')
   ;


fragment G
   : ('g' | 'G')
   ;


fragment H
   : ('h' | 'H')
   ;


fragment I
   : ('i' | 'I')
   ;


fragment J
   : ('j' | 'J')
   ;


fragment K
   : ('k' | 'K')
   ;


fragment L
   : ('l' | 'L')
   ;


fragment M
   : ('m' | 'M')
   ;


fragment N
   : ('n' | 'N')
   ;


fragment O
   : ('o' | 'O')
   ;


fragment P
   : ('p' | 'P')
   ;


fragment Q
   : ('q' | 'Q')
   ;


fragment R
   : ('r' | 'R')
   ;


fragment S
   : ('s' | 'S')
   ;


fragment T
   : ('t' | 'T')
   ;


fragment U
   : ('u' | 'U')
   ;


fragment V
   : ('v' | 'V')
   ;


fragment W
   : ('w' | 'W')
   ;


fragment X
   : ('x' | 'X')
   ;


fragment Y
   : ('y' | 'Y')
   ;


fragment Z
   : ('z' | 'Z')
   ;

EOL
   : [\r]? [\n]
   ;

NUMBER
   :  [0-9] +
   ;

COMMMENT : (';' | '@') ~ [\r\n]* -> skip;

WS
   : [ \t] -> skip
   ;

///////////// PARSER RULES

/* symbols */
pound
    : POUND;

comma
    : COMMA
    ;
imm
    : IMM
    ;

lbracket
    : LBRACKET
    ;

rbracket
    : RBRACKET
    ;

lbl
    : label DOTS
    ;

label
    : name
    ;

name
    : NAME
    ;

comment
    : COMMMENT
    ;

register
    : REGISTER
    ;

/* instructions */
lsl
    : LSL register comma register (comma imm)?
    ;

lsr
    : LSR register comma register (comma imm)?
    ;

asr
    : ASR register comma register (comma imm)?
    ;

add
    : ADD register comma register comma (register | imm)
    ;

sub
    : SUB register comma register comma (register | imm)
    ;

mov
    : MOV register comma (register | imm)
    ;

and
    : AND register comma register
    ;

eor
    : EOR register comma register
    ;

adc
    : ADC register comma register
    ;

sbc
    : SBC register comma register
    ;

ror
    : ROR register comma register
    ;

tst
    : TST register comma register
    ;

rsb
    : RSB register comma register (comma pound '0')?
    ;

cmp
    : CMP register comma register
    ;

cmn
    : CMN register comma register
    ;

orr
    : ORR register comma register
    ;

mul
    : MUL register comma register comma register
    ;

bic
    : BIC register comma register
    ;

mvn
    : MVN register comma register
    ;

str
    : STR register comma lbracket register comma imm rbracket
    ;

ldr
    : LDR register comma lbracket register (comma imm)? rbracket
    ;

b
    : BC label
    ;

instruction
    : (lsl
    | lsr
    | asr
    | add
    | sub
    | mov
    | and
    | eor
    | adc
    | sbc
    | ror
    | tst
    | rsb
    | cmp
    | cmn
    | orr
    | mul
    | bic
    | mvn
    | str
    | ldr
    | b) comment?
    ;

