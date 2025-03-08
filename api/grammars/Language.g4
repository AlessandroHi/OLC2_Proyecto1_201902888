grammar Language;

program: dcl*;

dcl: varDcl | stmt;

varDcl: 'var' ID type '=' expr   
      | 'var' ID type              
      | ID ':=' expr ;

stmt:
    expr                                     # ExprStmt
    | '{' dcl* '}'                          # BlockStmt
    | 'if' '(' expr ')' stmt ('else' stmt)? # IfStmt
    | 'while' '(' expr ')' stmt             # WhileStmt
    | 'for' '(' forInit expr ';' expr ')' stmt # ForStmt
    | 'break' ';'                            # BreakStmt
    | 'continue' ';'                         # ContinueStmt
    | 'return' expr?';'                      # ReturnStmt;


forInit: varDcl | expr ';';

expr:
    '-' expr                                    # Negate
    | expr call+                                # Callee
    | expr op = ('*' | '/' | '%') expr          # MulDivMod
    | expr op = ('+' | '-') expr                # AddSub
    | expr op = ('>' | '<' | '>=' | '<=') expr  # Relational
    | expr op = ('==' | '!=') expr              # Equality
    | ID op = ('+=' | '-=') expr                # IncDecAssign
    | ID '=' expr                               # Assign
    | BOOL                                      # Boolean
    | FLOAT                                     # Float
    | STRING                                    # String
    | INT                                       # Int
    | ID                                        # Identifier
    | RUNE                                      # Rune
    | EMBEDDED                                  # Embedded
    | '(' expr ')'                              # Parens;

call: '(' args? ')';

args: expr (',' expr)*;


type: 'int' | 'float64' | 'string' | 'bool' | 'rune';

EMBEDDED:  ID '.' ID;
INT: [0-9]+;
BOOL: 'true' | 'false';
FLOAT: [0-9]+ '.' [0-9]+;
STRING: '"' ~'"'* '"';
RUNE: '\'' . '\'';
ID: [a-zA-Z_][a-zA-Z0-9_]*;


// COMENTARIOS 
WS: [ \t\r\n]+ -> skip;
LINE_COMMENT: '//' ~[\r\n]* -> skip; // Comentario de una línea
BLOCK_COMMENT: '/*' .*? '*/' -> skip; // Comentario multilínea
