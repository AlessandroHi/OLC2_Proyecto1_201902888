using System.Globalization;
using analyzer;
using Antlr4.Runtime.Misc;
using Microsoft.Extensions.Logging.Console;

public class CompilerVisitor : LanguageBaseVisitor<ValueWrapper>
{

    public ValueWrapper defaultVoid = new VoidValue();

    public string output = "";
    public Environment currentEnvironment;

    public CompilerVisitor()
    {
        currentEnvironment = new Environment(null);
        Embeded.Generate(currentEnvironment);
    }

    // VisitProgram
    public override ValueWrapper VisitProgram(LanguageParser.ProgramContext context)
    {
        foreach (var dcl in context.dcl())
        {
            Visit(dcl);
        }
        return defaultVoid;
    }


    // VisitVarDcl
    public override ValueWrapper VisitVarDcl(LanguageParser.VarDclContext context)
    {
        string id = context.ID().GetText();

        // Caso: Declaración explícita con tipo y valor (var <identificador> <Tipo> = <Expresión>;)
        if (context.expr() != null && context.type() != null)
        {
            string type = context.type().GetText();
            ValueWrapper value = Visit(context.expr());

            // Validación de tipos directamente
            if (!((type == "int" && value is IntValue) ||
                (type == "float64" && value is FloatValue) || // Permitir int -> float
                (type == "string" && value is StringValue) ||
                (type == "bool" && value is BoolValue)))
            {
                throw new SemanticError($"Error: No se puede asignar un valor de tipo {value.GetType().Name} a una variable de tipo {type}", context.Start);
            }

            currentEnvironment.DeclareVariable(id, value, context.Start);
        }

        // Caso: Declaración explícita con tipo y sin valor (var <identificador> <Tipo>;)
        else if (context.expr() == null && context.type() != null)
        {
            // Obtener el tipo de la variable
            string type = context.type().GetText();

            // Declarar la variable con tipo explícito, pero sin valor
            // Aquí podrías usar un valor por defecto según el tipo, por ejemplo null o un valor cero
            ValueWrapper defaultValue = type switch
            {
                "int" => new IntValue(0),
                "float" => new FloatValue(0.0f),
                "string" => new StringValue(""),
                "bool" => new BoolValue(false),
                _ => throw new SemanticError($"Tipo desconocido: {type}", context.Start)
            };

            currentEnvironment.DeclareVariable(id, defaultValue, context.Start);
        }
        // Caso: Declaración implícita infiriendo el tipo (<identificador> := <Expresión> ;)
        else if (context.expr() != null && context.type() == null)
        {
            // Evaluar la expresión y obtener su tipo
            ValueWrapper value = Visit(context.expr());

            // Declarar la variable con el valor inferido
            currentEnvironment.DeclareVariable(id, value, context.Start);
        }

        return defaultVoid;
    }


    // VisitAssign
    public override ValueWrapper VisitAssign(LanguageParser.AssignContext context)
    {
        string id = context.ID().GetText();
        ValueWrapper value = Visit(context.expr());
        return currentEnvironment.AssignVariable(id, value, context.Start);
    }


    // VisitExprStmt
    public override ValueWrapper VisitExprStmt(LanguageParser.ExprStmtContext context)
    {
        return Visit(context.expr());
    }


    // VisitIdentifier
    public override ValueWrapper VisitIdentifier(LanguageParser.IdentifierContext context)
    {
        string id = context.ID().GetText();
        return currentEnvironment.GetVariable(id, context.Start);
    }

    //VISITEMBEDDED
    public override ValueWrapper VisitEmbedded([NotNull] LanguageParser.EmbeddedContext context)
    {
        string id = context.EMBEDDED().GetText();
        return currentEnvironment.GetEmbeded(id, context.Start);
    }

    // VisitParens
    public override ValueWrapper VisitParens(LanguageParser.ParensContext context)
    {
        return Visit(context.expr());
    }


    // VisitInt
    public override ValueWrapper VisitInt(LanguageParser.IntContext context)
    {
        return new IntValue(int.Parse(context.INT().GetText()));
    }


    // VisitFloat
    public override ValueWrapper VisitFloat(LanguageParser.FloatContext context)
    {

        return new FloatValue(float.Parse(context.FLOAT().GetText(), CultureInfo.InvariantCulture));
    }


    // VisitBoolean
    public override ValueWrapper VisitBoolean(LanguageParser.BooleanContext context)
    {
        return new BoolValue(bool.Parse(context.BOOL().GetText()));
    }


    // VisitString
    public override ValueWrapper VisitString(LanguageParser.StringContext context)
    {
        return new StringValue(context.STRING().GetText());
    }

    
    //VisitRune
    public override ValueWrapper VisitRune(LanguageParser.RuneContext context)
    {
        return new RuneValue(context.RUNE().GetText()[1]);
    }

    // VisitAddSub
    public override ValueWrapper VisitAddSub(LanguageParser.AddSubContext context)
    {
        ValueWrapper left = Visit(context.GetChild(0));
        ValueWrapper right = Visit(context.expr(1));
        var op = context.op.Text;

        return (left, right, op) switch
        {
            (IntValue l, IntValue r, "+") => new IntValue(l.Value + r.Value), // int + int
            (IntValue l, FloatValue r, "+") => new FloatValue(l.Value + r.Value), //int + float64
            (FloatValue l, FloatValue r, "+") => new FloatValue(l.Value + r.Value), //float64 + float64
            (FloatValue l, IntValue r, "+") => new FloatValue(l.Value + r.Value), //float64 + int
            (StringValue l, StringValue r, "+") => new StringValue(l.Value + r.Value), //string + string


            (IntValue l, IntValue r, "-") => new IntValue(l.Value - r.Value), //int - int
            (IntValue l, FloatValue r, "-") => new FloatValue(l.Value - r.Value), //int - float64
            (FloatValue l, FloatValue r, "-") => new FloatValue(l.Value - r.Value), //float64 - float64
            (FloatValue l, IntValue r, "-") => new FloatValue(l.Value - r.Value), //float64 - int
            _ => throw new SemanticError("Operacion Invalida", context.Start)
        };
    }


    // VisitMulDiv
    public override ValueWrapper VisitMulDivMod(LanguageParser.MulDivModContext context)
    {
        ValueWrapper left = Visit(context.expr(0));
        ValueWrapper right = Visit(context.expr(1));
        var op = context.op.Text;

        try
        {
            return (left, right, op) switch
            {
                (IntValue l, IntValue r, "*") => new IntValue(l.Value * r.Value), // int * int
                (IntValue l, FloatValue r, "*") => new FloatValue(l.Value * r.Value), // int * float64
                (FloatValue l, FloatValue r, "*") => new FloatValue(l.Value * r.Value), // float64 * float64
                (FloatValue l, IntValue r, "*") => new FloatValue(l.Value * r.Value), // float64 * int

                (IntValue l, IntValue r, "/") when r.Value != 0 => new IntValue(l.Value / r.Value), // int / int
                (IntValue l, FloatValue r, "/") when r.Value != 0 => new FloatValue(l.Value / r.Value), // int / float64
                (FloatValue l, FloatValue r, "/") when r.Value != 0 => new FloatValue(l.Value / r.Value), // float64 / float64
                (FloatValue l, IntValue r, "/") when r.Value != 0 => new FloatValue(l.Value / r.Value), // float64 / int

                (IntValue l, IntValue r, "%") when r.Value != 0 => new IntValue(l.Value % r.Value), // int % int

                (IntValue _, IntValue r, ("/" or "%")) when r.Value == 0 => throw new SemanticError("Error: División o módulo entre cero", context.Start),
                (IntValue _, FloatValue r, ("/" or "%")) when r.Value == 0 => throw new SemanticError("Error: División o módulo entre cero", context.Start),
                (FloatValue _, FloatValue r, ("/" or "%")) when r.Value == 0 => throw new SemanticError("Error: División o módulo entre cero", context.Start),
                (FloatValue _, IntValue r, ("/" or "%")) when r.Value == 0 => throw new SemanticError("Error: División o módulo entre cero", context.Start),

                _ => throw new SemanticError("Operacion Invalida", context.Start)
            };
        }
        catch (DivideByZeroException)
        {
            throw new SemanticError("Error: División o módulo entre cero", context.Start);
        }
    }


    //VisitIncDec
    public override ValueWrapper VisitIncDecAssign(LanguageParser.IncDecAssignContext context)
    {
        string variableName = context.ID().GetText();
        ValueWrapper right = Visit(context.expr());
        string op = context.op.Text;
        ValueWrapper left;

        try
        {
            left = currentEnvironment.GetVariable(variableName, context.Start); // Se ovbtiene el valor de la variable
        }
        catch (SemanticError)
        {
            throw new SemanticError($"Error: La variable '{variableName}' no está definida", context.Start);
        }

       
        ValueWrapper result = (left, right, op) switch
        {
            (IntValue l, IntValue r, "+=") => new IntValue(l.Value + r.Value), // int + int
            (FloatValue l, FloatValue r, "+=") => new FloatValue(l.Value + r.Value), //float64 + float64
            (FloatValue l, IntValue r, "+=") => new FloatValue(l.Value + r.Value), //float64 + int
            (StringValue l, StringValue r, "+=") => new StringValue(l.Value + r.Value), //string + string

            (IntValue l, IntValue r, "-=") => new IntValue(l.Value + r.Value), // int - int
            (FloatValue l, FloatValue r, "-=") => new FloatValue(l.Value + r.Value), //float64 - float64
            (FloatValue l, IntValue r, "-=") => new FloatValue(l.Value + r.Value), //float64 - int
            _ => throw new SemanticError($"Error: No se puede aplicar '{op}' entre {left.GetType().Name} y {right.GetType().Name}.", context.Start)
        };

        // Actualizar la variable en el entorno
        currentEnvironment.AssignVariable(variableName, result, context.Start);

        return result;
    }


    // VisitNegate
    public override ValueWrapper VisitNegate(LanguageParser.NegateContext context)
    {
        ValueWrapper value = Visit(context.expr());
        return value switch
        {
            IntValue i => new IntValue(-i.Value),
            FloatValue f => new FloatValue(-f.Value),
            _ => throw new SemanticError("Operacion invalida", context.Start)
        };
    }
   

    // VisitEquality
    public override ValueWrapper VisitEquality(LanguageParser.EqualityContext context)
        {
            ValueWrapper left = Visit(context.expr(0));
            ValueWrapper right = Visit(context.expr(1));
            var op = context.op.Text;

            return (left, right, op) switch
            {
                (IntValue l, IntValue r, "==") => new BoolValue(l.Value == r.Value), // int == int
                (IntValue l, IntValue r, "!=") => new BoolValue(l.Value != r.Value), // int != int
                (FloatValue l, FloatValue r, "==") => new BoolValue(l.Value == r.Value), // float64 == float64
                (FloatValue l, FloatValue r, "!=") => new BoolValue(l.Value != r.Value), // float64 != float64
                (IntValue l, FloatValue r, "==") => new BoolValue(l.Value == r.Value), // int == float64
                (IntValue l, FloatValue r, "!=") => new BoolValue(l.Value != r.Value), // int != float64
                (FloatValue l, IntValue r, "==") => new BoolValue(l.Value == r.Value), // float64 == int
                (FloatValue l, IntValue r, "!=") => new BoolValue(l.Value != r.Value), // float64 != int
                (BoolValue l, BoolValue r, "==") => new BoolValue(l.Value == r.Value), // bool == bool
                (BoolValue l, BoolValue r, "!=") => new BoolValue(l.Value != r.Value), // bool != bool
                (StringValue l, StringValue r, "==") => new BoolValue(l.Value == r.Value), // string == string
                (StringValue l, StringValue r, "!=") => new BoolValue(l.Value != r.Value), // string != string
                (RuneValue l, RuneValue r, "==") => new BoolValue(l.Value == r.Value), // rune == rune
                (RuneValue l, RuneValue r, "!=") => new BoolValue(l.Value != r.Value), // rune != rune
                
                _ => throw new SemanticError("Operacion Invalida", context.Start)
            };
        }


    // VisitRelational
    public override ValueWrapper VisitRelational(LanguageParser.RelationalContext context)
    {
        ValueWrapper left = Visit(context.expr(0));
        ValueWrapper right = Visit(context.expr(1));
        var op = context.op.Text;

        return (left, right, op) switch
        {
            (IntValue l, IntValue r, "<") => new BoolValue(l.Value < r.Value), //int < int
            (IntValue l, IntValue r, "<=") => new BoolValue(l.Value <= r.Value), //int <= int
            (IntValue l, IntValue r, ">") => new BoolValue(l.Value > r.Value), //int > int
            (IntValue l, IntValue r, ">=") => new BoolValue(l.Value >= r.Value), //int >= int
 
            (FloatValue l, FloatValue r, "<") => new BoolValue(l.Value < r.Value), // float64 < float64
            (FloatValue l, FloatValue r, "<=") => new BoolValue(l.Value <= r.Value), // float64 <= float64
            (FloatValue l, FloatValue r, ">") => new BoolValue(l.Value > r.Value), // float64 > float64
            (FloatValue l, FloatValue r, ">=") => new BoolValue(l.Value >= r.Value), // float64 >= float64

            (IntValue l, FloatValue r, "<") => new BoolValue(l.Value < r.Value), // int < float64
            (IntValue l, FloatValue r, "<=") => new BoolValue(l.Value <= r.Value), // int <= float64
            (IntValue l, FloatValue r, ">") => new BoolValue(l.Value > r.Value),    // int > float64
            (IntValue l, FloatValue r, ">=") => new BoolValue(l.Value >= r.Value), // int >= float64

            (FloatValue l, IntValue r, "<") => new BoolValue(l.Value < r.Value), // float64 < int
            (FloatValue l, IntValue r, "<=") => new BoolValue(l.Value <= r.Value),  //  float64 <= int
            (FloatValue l, IntValue r, ">") => new BoolValue(l.Value > r.Value),   // float64 > int
            (FloatValue l, IntValue r, ">=") => new BoolValue(l.Value >= r.Value), // float64 >= int


            (RuneValue l, RuneValue r, "<") => new BoolValue(l.Value < r.Value), // Rune < Rune
            (RuneValue l, RuneValue r, "<=") => new BoolValue(l.Value <= r.Value),  //  Rune <= Rune
            (RuneValue l, RuneValue r, ">") => new BoolValue(l.Value > r.Value),   // Rune > Rune
            (RuneValue l, RuneValue r, ">=") => new BoolValue(l.Value >= r.Value), // Rune >= Rune

            _ => throw new SemanticError("Operacion Invalida", context.Start)
        };
    }

    //VisitLogical
    public override ValueWrapper VisitLogical(LanguageParser.LogicalContext context)
    {
        ValueWrapper left = Visit(context.expr(0));
        ValueWrapper right = Visit(context.expr(1));
        var op = context.op.Text;

        return (left, right, op) switch
        {
            (BoolValue l, BoolValue r, "&&") => new BoolValue(l.Value && r.Value), // bool && bool
            (BoolValue l, BoolValue r, "||") => new BoolValue(l.Value || r.Value), // bool || bool
            _ => throw new SemanticError("Operacion Invalida", context.Start)
        };
    }


    // VisitNot
    public override ValueWrapper VisitNot(LanguageParser.NotContext context)
    {
        ValueWrapper value = Visit(context.expr());
        if (value is not BoolValue)
        {
            throw new SemanticError("Operacion Invalida", context.Start);
        }
        return new BoolValue(!(value as BoolValue).Value);
    }


    // VisitIfStmt
    public override ValueWrapper VisitIfStmt(LanguageParser.IfStmtContext context)
    {
        ValueWrapper condition = Visit(context.expr());

        if (condition is not BoolValue)
        {
            throw new SemanticError("Condicion invalida", context.Start);
        }

        if ((condition as BoolValue).Value)
        {
            Visit(context.stmt(0));
        }
        else if (context.stmt().Length > 1)
        {
            Visit(context.stmt(1));
        }

        return defaultVoid;
    }


    //VisitSwitchStmt
    public override ValueWrapper VisitSwitchStmt(LanguageParser.SwitchStmtContext context)
    {
        ValueWrapper condition = Visit(context.expr());

        // ejecuta el primero que coincida
        foreach (var caseClause in context.caseClause())
        {
            ValueWrapper caseValue = Visit(caseClause.expr());

            if (condition.Equals(caseValue))
            {
                foreach (var stmt in caseClause.stmt())
                {
                    Visit(stmt);  
                }
                return defaultVoid;  
            }
        }

        // Si no hubo coincidencia, procesamos el 'default' si existe
        if (context.defaultStmt() != null)
        {
            Visit(context.defaultStmt());
        }

        return defaultVoid;
    }


    // VisitCaseClause
    public override ValueWrapper VisitCaseClause(LanguageParser.CaseClauseContext context)
    {
        // sentencias dentro de un 'case'
        foreach (var stmt in context.stmt())
        {
            Visit(stmt);  // ejecuta cada instrucción del case
        }

        return defaultVoid;
    }
        

    //VisitDefaultCase
    public override ValueWrapper VisitDefaultStmt(LanguageParser.DefaultStmtContext context)
    {
        // sentencia del 'default'
        foreach (var stmt in context.stmt())
        {
            Visit(stmt); 
        }

        return defaultVoid;
    }


    // VisitForStmt
    public override ValueWrapper VisitForStmt(LanguageParser.ForStmtContext context)
    {
        Environment previousEnvironment = currentEnvironment;
        currentEnvironment = new Environment(currentEnvironment);

        Visit(context.forInit());
        VisitForBody(context);

        //VISITForBody
        currentEnvironment = previousEnvironment;
        return defaultVoid;
    }

    public void VisitForBody(LanguageParser.ForStmtContext context)
    {
        ValueWrapper condition = Visit(context.expr(0));
        var lastEnvironment = currentEnvironment;

        if (condition is not BoolValue)
        {
            throw new SemanticError("Invalid condition", context.Start);
        }

        try
        {
            while (condition is BoolValue boolCondition && boolCondition.Value)
            {
                Visit(context.stmt());
                Visit(context.expr(1));
                condition = Visit(context.expr(0));
            }
        }
        catch (BreakException)
        {
            currentEnvironment = lastEnvironment;
        }
        catch (ContinueException)
        {
            currentEnvironment = lastEnvironment;
            Visit(context.expr(1));
            VisitForBody(context);
        }

    }

    // VisitBlockStmt
    public override ValueWrapper VisitBlockStmt(LanguageParser.BlockStmtContext context)
    {
        Environment previousEnvironment = currentEnvironment;
        currentEnvironment = new Environment(currentEnvironment);

        foreach (var stmt in context.dcl())
        {
            Visit(stmt);
        }

        currentEnvironment = previousEnvironment;
        return defaultVoid;
    }


    // VisitWhileStmt
    public override ValueWrapper VisitWhileStmt(LanguageParser.WhileStmtContext context)
    {
        ValueWrapper condition = Visit(context.expr());

        if (condition is not BoolValue)
        {
            throw new SemanticError("Invalid condition", context.Start);
        }

        while ((condition as BoolValue).Value)
        {
            Visit(context.stmt());
            condition = Visit(context.expr());
        }

        return defaultVoid;
    }


    //VisitBreakStmt
    public override ValueWrapper VisitBreakStmt(LanguageParser.BreakStmtContext context)
    {
        throw new BreakException();
    }

    //VisitContinueStmt
    public override ValueWrapper VisitContinueStmt(LanguageParser.ContinueStmtContext context)
    {
        throw new ContinueException();
    }

    //VisitReturnStmt
    public override ValueWrapper VisitReturnStmt(LanguageParser.ReturnStmtContext context)
    {
        ValueWrapper value = defaultVoid;
        if (context.expr() != null)
        {
            value = Visit(context.expr());
        }
        throw new ReturnException(value);
    }


    //VisitCallee
    public override ValueWrapper VisitCallee(LanguageParser.CalleeContext context)
    {
        ValueWrapper callee = Visit(context.expr());

        foreach (var call in context.call())
        {
            if (callee is FunctionValue functionValue)
            {
                callee = Visitcall(functionValue.invocable, call.args());
            }
            else
            {
                throw new SemanticError("Invalid function call", context.Start);
            }
        }
        return callee;
    }

    public ValueWrapper Visitcall(Invocable invocable, LanguageParser.ArgsContext context)
    {
        List<ValueWrapper> arguments = new List<ValueWrapper>();
        if (context != null)
        {
            foreach (var expr in context.expr())
            {
                arguments.Add(Visit(expr));
            }
        }

        // if(context != null && arguments.Count != invocable.Arity())
        // {
        //     throw new SemanticError("Invalid number of arguments", context.Start);
        // }

        return invocable.Invoke(arguments, this);
    }
}