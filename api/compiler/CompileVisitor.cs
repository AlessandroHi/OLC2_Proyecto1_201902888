using System.Diagnostics.Eventing.Reader;
using System.Globalization;
using System.Reflection.Metadata;
using analyzer;
using Antlr4.Runtime.Misc;
using Microsoft.Extensions.Logging.Console;

public class CompilerVisitor : LanguageBaseVisitor<ValueWrapper>
{

    public ValueWrapper defaultVoid = new VoidValue();

    public string output = "";
    public Environment currentEnvironment; //Entorno actual

    public CompilerVisitor()
    {
        currentEnvironment = new Environment(null);
        Embeded.Generate(currentEnvironment);
    }

public void ExecuteMain()
{

    if (!currentEnvironment.Symbols.ContainsKey("main"))
    {
        throw new Exception("Error: No se encontró la función 'main'.");
    }

   
    var mainFunc = currentEnvironment.Symbols["main"] as FunctionValue;  // Obtener la función main registrada

    if (mainFunc == null)
    {
        throw new Exception("Error: 'main' no es una función válida.");
    }

    // Ejecutar la función 'main'
    mainFunc.invocable.Invoke(new List<ValueWrapper>(), this);
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

        
        if (context.expr() is LanguageParser.InStructContext Iscontext){
        
            string id2 = Iscontext.ID().GetText();
            ValueWrapper value = currentEnvironment.GetSymbol(id2, Iscontext.Start);
            if (value is  StructValue){
                Visit(context.expr());
            }
            else{
                throw new SemanticError("Error Semantico: No es una estructura", context.Start);
            }
            currentEnvironment.DeclareSymbol(id, value, context.Start);
            
        }
        else if (context.expr() != null && context.type() != null)
        {
            string type = context.type().GetText();
            ValueWrapper value = Visit(context.expr());

            // Validación de tipos directamente
            if (!((type == "int" && value is IntValue) ||
                (type == "float64" && value is FloatValue) ||
                (type == "string" && value is StringValue) ||
                (type == "bool" && value is BoolValue) ||
                (type == "rune" && value is RuneValue)))
            {
                throw new SemanticError($"Error: el tipo de valor {value.GetType().Name} no coicide a una variable de tipo {type}", context.Start);
            }

            currentEnvironment.DeclareSymbol(id, value, context.Start);
        }

        // var <identificador> <Tipo>
        else if (context.expr() == null && context.type() != null)
        {

            string type = context.type().GetText();


            // Valor por defecto según el tipo
            ValueWrapper defaultValue = type switch
            {
                "int" => new IntValue(0),
                "float64" => new FloatValue(0.0m),
                "string" => new StringValue(""),
                "bool" => new BoolValue(false),
                "rune" => new RuneValue(' '),
                _ => throw new SemanticError($"Error Semantico: tipo no validado: {type}", context.Start)
            };


            currentEnvironment.DeclareSymbol(id, defaultValue, context.Start);
        }
        // <identificador> := <Expresión>
        else if (context.expr() != null && context.type() == null)
        {

            ValueWrapper value = Visit(context.expr());

            currentEnvironment.DeclareSymbol(id, value, context.Start);
        }

        return defaultVoid;
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


    // VisitAssign
    public override ValueWrapper VisitAssign(LanguageParser.AssignContext context)
    {
        var assintation = context.expr(0);
        ValueWrapper value = Visit(context.expr(1));

        
        if (assintation is LanguageParser.IdentifierContext idContext && context.expr(1) is not LanguageParser.CalleeContext)
        {
            string id = idContext.ID().GetText();
            ValueWrapper variable = currentEnvironment.GetSymbol(id, context.Start);
            // Si la variable ya existe, validar que el tipo de dato coincida
            if (variable != null && value.GetType() != variable.GetType())
            {
                throw new SemanticError($"Error Semantico: el tipo {value.GetType()} no coicidi con variable '{id}'", context.Start);
            }

            //Se le pasa el nuevo dato a la variable
            return currentEnvironment.AssignSymbol(id, value, context.Start);
        }
        else if (assintation is LanguageParser.CalleeContext calleeContext)
        {
            ValueWrapper callee = Visit(calleeContext.expr());

            for (int i = 0; i < calleeContext.call().Length; i++)
            {
                var acciones = calleeContext.call(i);

                if (i == calleeContext.call().Length - 1)
                {
                    if (acciones is LanguageParser.GetContext propidades)
                    {
                        if (callee is InstanciaValue instanciaValue)
                        {
                            var instancia = instanciaValue.instancia;
                            var propiedadName = propidades.ID().GetText();
                            instancia.SeT(propiedadName, value);
                        }
                        else
                        {
                            throw new SemanticError("Error Semantico: Propiedad invalida o no encontrada", context.Start);
                        }
                    }
                    else
                    {
                        throw new SemanticError("Error Semantico: Asignacion Invalidad", context.Start);
                    }

                }

                if (acciones is LanguageParser.FuncCallContext funcall)
                {
                    if (callee is FunctionValue functionValue)
                    {
                        callee = Visitcall(functionValue.invocable, funcall.args());
                    }
                    else
                    {
                        throw new SemanticError("Error Semantico: Funcion invalida o no encontrada", context.Start);
                    }
                }
                else if (acciones is LanguageParser.GetContext propidades)
                {
                    if (callee is InstanciaValue instanciaValue)
                    {
                        callee = instanciaValue.instancia.Get(propidades.ID().GetText(), propidades.Start);
                    }
                    else
                    {
                        throw new SemanticError("Error Semantico: Propiedad invalida o no encontrada", context.Start);
                    }
                }

            }
            return callee;
        }

        else if (assintation is LanguageParser.IndexContext indexContext)
        {
            string id = indexContext.ID().GetText();
            ValueWrapper index = Visit(indexContext.expr());
            ValueWrapper array = currentEnvironment.GetSymbol(id, context.Start);


            if (array is not ArrayValue)
            {
                throw new SemanticError("Error Semantico: No es un valor invalido", context.Start);
            }

            if (index is not IntValue)
            {
                throw new SemanticError("Error Semantico: Indice invalido", context.Start);
            }

            List<ValueWrapper> arrayValues = (array as ArrayValue).Value;
            int i = (index as IntValue).Value;

            if (i < 0 || i >= arrayValues.Count)
            {
                throw new SemanticError("Error Semantico: Indice fuera de rango", context.Start);
            }

            arrayValues[i] = value;
            return value;

        }
        else if (context.expr(1) is LanguageParser.CalleeContext calleContext)
        {

        }
        else if (assintation is LanguageParser.MatrixIndexContext matrixIndexContext)
        {
            string id = matrixIndexContext.ID().GetText();
            ValueWrapper rowIndex = Visit(matrixIndexContext.expr(0));
            ValueWrapper colIndex = Visit(matrixIndexContext.expr(1));
            ValueWrapper matrix = currentEnvironment.GetSymbol(id, context.Start);

            if (matrix is not MatrixValue matrixValue)
            {
                throw new SemanticError("Error Semántico: No es una matriz", context.Start);
            }

            if (rowIndex is not IntValue row || colIndex is not IntValue col)
            {
                throw new SemanticError("Error Semántico: Índices de matriz inválidos", context.Start);
            }

            if (row.Value < 0 || row.Value >= matrixValue.Value.Count ||
                col.Value < 0 || col.Value >= matrixValue.Value[row.Value].Count)
            {
                throw new SemanticError("Error Sematico: indice fuera de rango", context.Start);
            }

            matrixValue.Value[row.Value][col.Value] = value;
            return value;
        }
        else
        {
            throw new SemanticError("Error Semantico: Asignacion Invalida", context.Start);
        }

        return defaultVoid;

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
        return currentEnvironment.GetSymbol(id, context.Start);
    }

    //VISITEMBEDDED
    public override ValueWrapper VisitEmbedded( LanguageParser.EmbeddedContext context)
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

        return new FloatValue(decimal.Parse(context.FLOAT().GetText(), CultureInfo.InvariantCulture));
    }


    // VisitBoolean
    public override ValueWrapper VisitBoolean(LanguageParser.BooleanContext context)
    {
        return new BoolValue(bool.Parse(context.BOOL().GetText()));
    }


    // VisitString
    public override ValueWrapper VisitString(LanguageParser.StringContext context)
    {
        string text = context.STRING().GetText();
        text = text.Substring(1, text.Length - 2); // Elimina las comillas inicial y final
        return new StringValue(text);
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
            _ => throw new SemanticError("Error Semanantico: Operacion Invalida", context.Start)
        };
    }


    // VisitMulDiv
    public override ValueWrapper VisitMulDivMod(LanguageParser.MulDivModContext context)
    {
        ValueWrapper left = Visit(context.expr(0));
        ValueWrapper right = Visit(context.expr(1));
        var op = context.op.Text;

        // Verificar división o módulo por cero antes de ejecutar la operación
        if ((op == "/" || op == "%") && right is IntValue { Value: 0 } or FloatValue { Value: 0 })
        {
            throw new SemanticError("Error Semantico: División o módulo entre cero", context.Start);
        }

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


                _ => throw new SemanticError("Error Semantico: Operacion Invalida", context.Start)
            };
        }
        catch (DivideByZeroException)
        {
            throw new SemanticError("Error Semantico: División o módulo entre cero", context.Start);
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
            left = currentEnvironment.GetSymbol(variableName, context.Start); // Se ovbtiene el valor de la variable
        }
        catch (SemanticError)
        {
            throw new SemanticError($"Error Semantico: La variable '{variableName}' no está definida", context.Start);
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
            _ => throw new SemanticError($"Error Semantico: No se puede aplicar '{op}' entre {left.GetType().Name} y {right.GetType().Name}.", context.Start)
        };

        // Actualizar la variable en el entorno que se encuentra
        currentEnvironment.AssignSymbol(variableName, result, context.Start);

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
            _ => throw new SemanticError("Error Semantico: Operacion invalida", context.Start)
        };
    }

    //VisitIncrement
    public override ValueWrapper VisitIncrement(LanguageParser.IncrementContext context)
    {
        string id = context.ID().GetText();
        ValueWrapper left;
        left = currentEnvironment.GetSymbol(id, context.Start); //buscar la variable en el entorno actual

        ValueWrapper result = left switch
        {
            IntValue l => new IntValue(l.Value + 1),
            FloatValue f => new FloatValue(f.Value + 1),
            _ => throw new SemanticError($"Error Semantico: Operacion invalida  {left.GetType().Name}.", context.Start)
        };

        currentEnvironment.AssignSymbol(id, result, context.Start);

        return result;
    }

    //VisitDecrement
    public override ValueWrapper VisitDecrement(LanguageParser.DecrementContext context)
    {
        string id = context.ID().GetText();
        ValueWrapper left;
        //buscar la variable en el entorno actual
        left = currentEnvironment.GetSymbol(id, context.Start);

        ValueWrapper result = left switch
        {
            IntValue l => new IntValue(l.Value - 1),
            FloatValue f => new FloatValue(f.Value - 1),
            _ => throw new SemanticError($"Error Semantico: Operacion invalida  {left.GetType().Name}.", context.Start)
        };


        currentEnvironment.AssignSymbol(id, result, context.Start);

        return result;
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

            _ => throw new SemanticError("Error Semantico: Operacion Invalida", context.Start)
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

            _ => throw new SemanticError("Error Semantico: Operacion Invalida", context.Start)
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
            _ => throw new SemanticError("Error Semantico: Operacion Invalida", context.Start)
        };
    }


    // VisitNot
    public override ValueWrapper VisitNot(LanguageParser.NotContext context)
    {
        ValueWrapper value = Visit(context.expr());
        if (value is not BoolValue)
        {
            throw new SemanticError("Error Semantico: Operacion Invalida", context.Start);
        }
        return new BoolValue(!(value as BoolValue).Value);
    }

    //VisitSlice
    public override ValueWrapper VisitSlice(LanguageParser.SliceContext context)
    {
        string id = context.ID().GetText();
        List<ValueWrapper> Arrayvalues = new List<ValueWrapper>();
        currentEnvironment.DeclareSymbol(id, new ArrayValue(Arrayvalues), context.Start);

        return defaultVoid;
    }

    //VisitSlices retorna la lista de expresiones
    public override ValueWrapper VisitSlices(LanguageParser.SlicesContext context)
    {
        string type = context.type().GetText();
        List<ValueWrapper> Arrayvalues = new List<ValueWrapper>();
        if (context.args() != null)
        {
            var args = context.args();
            foreach (var expr in args.expr())
            {
                var value = Visit(expr);

                if (!((type == "int" && value is IntValue) ||
                      (type == "float64" && value is FloatValue) ||
                      (type == "string" && value is StringValue) ||
                      (type == "bool" && value is BoolValue)))
                {
                    throw new SemanticError($"Error Semantico: el tipo de valor {value.GetType().Name} no coincide con el tipo {type} del Slice", context.Start);
                }

                Arrayvalues.Add(value);
            }
        }

        return new ArrayValue(Arrayvalues); //retorna la lista de expresiones para los slices
    }

    //VisitIndex
    public override ValueWrapper VisitIndex(LanguageParser.IndexContext context)
    {
        string id = context.ID().GetText();
        ValueWrapper index = Visit(context.expr());
        ValueWrapper variable = currentEnvironment.GetSymbol(id, context.Start);

        if (variable is not ArrayValue && variable is not MatrixValue)
        {
            throw new SemanticError("Error Semántico: No es un arreglo ni una matriz", context.Start);
        }

        if (index is not IntValue intIndex)
        {
            throw new SemanticError("Error Semántico: Índice inválido", context.Start);
        }

        int i = intIndex.Value;

        if (variable is ArrayValue arrayValue)
        {
            // Acceso a un array normal
            if (i < 0 || i >= arrayValue.Value.Count)
            {
                throw new SemanticError("Error Semántico: Índice fuera de rango", context.Start);
            }
            return arrayValue.Value[i];
        }
        else if (variable is MatrixValue matrixValue)
        {
            // Acceso a una fila de la matriz (retorna una nueva ArrayValue con la fila seleccionada)
            if (i < 0 || i >= matrixValue.Value.Count)
            {
                throw new SemanticError("Error Semántico: Índice fuera de rango", context.Start);
            }
            return new ArrayValue(matrixValue.Value[i]);
        }

        throw new SemanticError("Error inesperado en VisitIndex", context.Start);
    }


    //VisitMatrix
    public override ValueWrapper VisitMatrix(LanguageParser.MatrixContext context)
    {
        string id = context.ID().GetText();
        string tipo = context.type().GetText();

        List<List<ValueWrapper>> Arrayvalues = new List<List<ValueWrapper>>();  // Matriz vacía

        // filas de la matriz
        if (context.args() != null)
        {
            // expresiones dentro de las llaves
            foreach (var arg in context.args())
            {
                List<ValueWrapper> row = new List<ValueWrapper>();  // Fila vacía


                foreach (var expr in arg.expr())
                {
                    var value = Visit(expr);

                    if (!((tipo == "int" && value is IntValue) ||
                          (tipo == "float64" && value is FloatValue) ||
                          (tipo == "string" && value is StringValue) ||
                          (tipo == "bool" && value is BoolValue)))
                    {
                        throw new SemanticError($"Error Semantico: el tipo de valor {value.GetType().Name} no coincide con el tipo {tipo} de la matriz", context.Start);
                    }

                    row.Add(value);
                }

                Arrayvalues.Add(row); // se agrega la fila a la matriz
            }
        }

        currentEnvironment.DeclareSymbol(id, new MatrixValue(Arrayvalues), context.Start);


        return defaultVoid;
    }

    //VisitMatrixIndex
    public override ValueWrapper VisitMatrixIndex([NotNull] LanguageParser.MatrixIndexContext context)
    {
        string id = context.ID().GetText();
        ValueWrapper index = Visit(context.expr(0));
        ValueWrapper index2 = Visit(context.expr(1));
        ValueWrapper matrix = currentEnvironment.GetSymbol(id, context.Start);

        if (matrix is not MatrixValue)
        {
            throw new SemanticError("Error Semantico: No es una matriz", context.Start);
        }

        if (index is not IntValue || index2 is not IntValue)
        {
            throw new SemanticError("Error Semantico: Indice invalido", context.Start);
        }

        List<List<ValueWrapper>> matrixValues = (matrix as MatrixValue).Value;
        int i = (index as IntValue).Value;
        int j = (index2 as IntValue).Value;

        if (i < 0 || i >= matrixValues.Count || j < 0 || j >= matrixValues[i].Count)
        {
            throw new SemanticError("Error Semantico: Indice fuera de rango", context.Start);
        }

        return matrixValues[i][j];
    }


    // VisitIfStmt
    public override ValueWrapper VisitIfStmt(LanguageParser.IfStmtContext context)
    {
        ValueWrapper condition = Visit(context.expr());

        if (condition is not BoolValue)
        {
            throw new SemanticError("Error Semantico: Condicion invalida", context.Start);
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


        foreach (var cases in context.cases())
        {
            ValueWrapper caseValue = Visit(cases.expr());

            if (condition.Equals(caseValue))
            {
                try
                {
                    foreach (var stmt in cases.stmt())
                    {
                        Visit(stmt);
                    }
                }
                catch (BreakException)
                {
                    return defaultVoid;
                }

                return defaultVoid;
            }
        }

        // Si no hubo coincidencia va al 'default'
        if (context.defaultSwitch() != null)
        {
            try
            {
                Visit(context.defaultSwitch());
            }
            catch (BreakException)
            {
                return defaultVoid;
            }
        }

        return defaultVoid;
    }


    // VisitCases

    public override ValueWrapper VisitCases(LanguageParser.CasesContext context)
    {
        foreach (var stmt in context.stmt())
        {
            Visit(stmt);
        }

        return defaultVoid;
    }



    //VisitDefaultCase
    public override ValueWrapper VisitDefaultSwitch(LanguageParser.DefaultSwitchContext context)
    {

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


    //VisitForCondStmt
    public override ValueWrapper VisitForCondStmt(LanguageParser.ForCondStmtContext context)
    {
       

        ValueWrapper condition = Visit(context.expr());

        if (condition is not BoolValue)
        {
            throw new SemanticError("Error Semántico: Condición inválida", context.Start);
        }

        try
        {
            while (condition is BoolValue boolCondition && boolCondition.Value)
            {
                try
                {
                    Visit(context.stmt());
                }
                catch (BreakException)
                {
                    break;  // Sale del ciclo
                }
                catch (ContinueException)
                {
                    condition = Visit(context.expr());
                    continue;
                }

                condition = Visit(context.expr());
            }
        }
        finally
        {
            
        }

        return defaultVoid;
    }


    //VisitForRangeStmt
    public override ValueWrapper VisitForRange(LanguageParser.ForRangeContext context)
    {
        string indexVar = context.ID(0).GetText();
        string valueVar = context.ID(1).GetText();
        ValueWrapper expresion = Visit(context.expr());

        // Validar que la expresión es una lista o colección iterable
        if (expresion is not ArrayValue)
        {
            throw new SemanticError("Error Semántico: No se puede iterar'", context.Start);
        }

        Environment previousEnvironment = currentEnvironment;
        currentEnvironment = new Environment(previousEnvironment);

        //GUARDAR INDICE Y VALOR
        currentEnvironment.DeclareSymbol(indexVar, new IntValue(0), context.Start);
        currentEnvironment.DeclareSymbol(valueVar, new IntValue(0), context.Start);

        if (expresion is not ArrayValue arrayValue)
        {
            throw new SemanticError("Error Semántico: No se puede iterar, la expresión no es un ArrayValue", context.Start);
        }
        List<ValueWrapper> array = arrayValue.Value;

        for (int i = 0; i < array.Count; i++) // Recorrer la expresion 
        {
            //Actuliza los valores de las variables
            currentEnvironment.AssignSymbol(indexVar, new IntValue(i), context.Start);
            currentEnvironment.AssignSymbol(valueVar, array[i], context.Start);

            try
            {
                Visit(context.stmt());
            }
            catch (BreakException)
            {
                break;
            }
            catch (ContinueException)
            {
                continue;
            }
        }


        return defaultVoid;
    }

    public void VisitForBody(LanguageParser.ForStmtContext context)
    {
        ValueWrapper condition = Visit(context.expr(0));
        var lastEnvironment = currentEnvironment;

        if (condition is not BoolValue)
        {
            throw new SemanticError("Error Semantico:Condicion invalida", context.Start);
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
    //BODY DEL FOR


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

        foreach (var acciones in context.call())
        {
            if (acciones is LanguageParser.FuncCallContext funcall)
            {
                if (callee is FunctionValue functionValue)
                {
                    callee = Visitcall(functionValue.invocable, funcall.args());
                }
                else
                {
                    throw new SemanticError("Error Semantico: Funcion invalida o no encontrada", context.Start);
                }
            }
            else if (acciones is LanguageParser.GetContext propidades)
            {
                if (callee is InstanciaValue instanciaValue)
                {
                    callee = instanciaValue.instancia.Get(propidades.ID().GetText(), propidades.Start);
                }
                else
                {
                    throw new SemanticError("Error Semantico: Propiedad invalida o no encontrada", context.Start);
                }
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

        return invocable.Invoke(arguments, this);
    }

    //VisitFuncDcl
    public override ValueWrapper VisitFuncDcl(LanguageParser.FuncDclContext context)
    {

        var foranea = new ForeneaFuncion(currentEnvironment, context);

        currentEnvironment.DeclareSymbol(context.ID().GetText(), new FunctionValue(foranea, context.ID().GetText()), context.Start);
        return defaultVoid;
    }

    //VisitClassDcl
    public override ValueWrapper VisitStructDcl(LanguageParser.StructDclContext context)
    {
        Dictionary<string, LanguageParser.VarDclContext> props = new Dictionary<string, LanguageParser.VarDclContext>();
       
        
            foreach (var varDcl in context.varDcl())
        {
            if (varDcl != null)
            {   
                
                props.Add(varDcl.ID().GetText(), varDcl);
            }

        }


        LanguageStruct languageStruct = new LanguageStruct(context.ID().GetText(), props);
    
        currentEnvironment.DeclareSymbol(context.ID().GetText(), new StructValue(languageStruct), context.Start);
        return defaultVoid;
    }


    //Visitstrucprop
    public override ValueWrapper VisitInStruct(LanguageParser.InStructContext context)
    {

        ValueWrapper structValue = currentEnvironment.GetSymbol(context.ID().GetText(), context.Start);
        if (structValue is not StructValue)
        {
            throw new SemanticError("Error Semantico: Clase no encontrada", context.Start);
        }

       
        List<ValueWrapper> arguments = new List<ValueWrapper>();

        if (context.props() != null)
        {
            foreach (var arg in context.props().expr())
            {
                arguments.Add(Visit(arg));
              
            }
        }

        var instancia = ((StructValue)structValue).languageStruct.Invoke(arguments, this);

        return instancia;

    }

    //VisitNew
}

