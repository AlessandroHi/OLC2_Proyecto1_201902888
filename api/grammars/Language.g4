grammar Language;

program: dcl*;

dcl: varDcl |funcDcl| structDcl | slice | matrix |stmt;

varDcl: 'var' ID type '=' expr ';'?   
      | 'var' ID type  ';'?             
      | ID ':=' expr ';'?
      | ID  type ';'?;
      


matrix: ID ':=' '[' ']' '[' ']' type '{' '{' args '}'',' ( '{' args '}' ',')* '}' ';'?;



slice:  'var' ID '[' ']' type ';'? ; 

funcDcl: 'func' ID '(' params? ')' type? '{' dcl* '}' ';'? ;

structDcl: 'type' ID 'struct' '{' varDcl* '}' ;


params: ID type (',' ID type)*;

stmt:
    expr                                               # ExprStmt
    | '{' dcl* '}'                                     # BlockStmt
    | 'if'  expr  stmt ('else' stmt)?                  # IfStmt
    | 'switch' expr '{' cases*  defaultSwitch? '}'     # SwitchStmt
    | 'for'  forInit expr ';' expr  stmt               # ForStmt
    | 'for'  expr  stmt                                # ForCondStmt
    | 'for' ID ',' ID ':=' 'range' expr stmt           # ForRange
    | 'break'  ';'?                                         # BreakStmt
    | 'continue'  ';'?                                      # ContinueStmt
    | 'return' expr? ';'?                                   # ReturnStmt; 

cases: 'case' expr ':' stmt*; 
defaultSwitch: 'default' ':' stmt*;

forInit: varDcl ';' | expr ';';

expr:
    '-' expr                                    # Negate
    | expr call+                                # Callee
    | expr op = ('*' | '/' | '%') expr          # MulDivMod
    | expr op = ('+' | '-') expr                # AddSub
    | expr op = ('>' | '<' | '>=' | '<=') expr  # Relational
    | expr op = ('==' | '!=') expr              # Equality
    | expr op = ('&&' | '||' ) expr             # Logical
    | '!' expr                                  # Not
    | ID op = ('+=' | '-=') expr                # IncDecAssign
    | expr '=' expr ';'?                              # Assign
    | '['  ']' type '{' args '}'                # Slices
    | ID '[' expr ']'                           # Index
    | ID '[' expr ']' '[' expr ']'              # MatrixIndex
    | ID '++'                                   # Increment
    | ID '--'                                   # Decrement
    | ID '{' props '}'                          # InStruct
    | BOOL                                      # Boolean
    | FLOAT                                     # Float
    | STRING                                    # String
    | INT                                       # Int
    | ID                                        # Identifier
    | RUNE                                      # Rune
    | EMBEDDED                                  # Embedded
    | '(' expr ')'                              # Parens;

call: '(' args? ')' #FuncCall | '.' ID #Get;

args: expr (',' expr)*;

props: ( ID ':' expr ',' ( ID ':' expr ',' )*); 


type: 'int' | 'float64' | 'string' | 'bool' | 'rune';

EMBEDDED:  ID '.' ID;
INT: [0-9]+;
BOOL: 'true' | 'false';
FLOAT: [0-9]+ '.' [0-9]+;
STRING: '"' ('\\"' | ~'"')* '"';
RUNE: '\'' . '\'';
ID: [a-zA-Z_][a-zA-Z0-9_]*;


// COMENTARIOS 
WS: [ \t\r\n]+ -> skip;
LINE_COMMENT: '//' ~[\r\n]* -> skip; 
BLOCK_COMMENT: '/*' .*? '*/' -> skip;
