
using System.Security.Cryptography.X509Certificates;
using System.Text.RegularExpressions;
using System;
using System.Globalization;
using System.Collections.Generic;


public class Embeded
{
    public static void Generate(Environment env)
    {
        //funciones embeded
        env.DeclareEmbeded("reflect.TypeOf", new FunctionValue(new ReflecType(), "reflect.TypeOf"), null);
        env.DeclareEmbeded("strconv.ParseFloat", new FunctionValue(new StrconFloat(), "strconv.ParseFloat"), null);
        env.DeclareEmbeded("strconv.Atoi", new FunctionValue(new StrconvAtoi(), "strconv.Atoi"), null);
        env.DeclareEmbeded("fmt.Println", new FunctionValue(new PrintEmbeded(), "fmt.Println"), null);



        // Implementacion de las funciones de estructura de datos
        env.DeclareEmbeded("slices.Index", new FunctionValue(new SliceIndex(), "slices.index"), null);
        env.DeclareEmbeded("strings.Join", new FunctionValue(new StringsJoin(), "strings.Join"), null);
        env.DeclareSymbol("len", new FunctionValue(new Len(), "len"), null);
        env.DeclareSymbol("append", new FunctionValue(new Append(), "append"), null);


    }

}//Funciones embeded
public class PrintEmbeded : Invocable
{
    public int Arity()
    {
        return 1;
    }

    public ValueWrapper Invoke(List<ValueWrapper> args, CompilerVisitor visitor)
    {

        var output = "";
        foreach (var arg in args)
        {
            output += arg switch
            {
                IntValue i => i.Value.ToString() + " ",
                FloatValue f => f.Value.ToString() + " ",
                StringValue s => Regex.Unescape(s.Value.Trim('"')) + " ",
                BoolValue b => b.Value.ToString() + " ",
                VoidValue v => "void ",
                FunctionValue fn => fn.name + "> ",
                ArrayValue subArray => "[ " + string.Join(", ", subArray.Value.Select(sub => sub switch
                {
                    IntValue i => i.Value.ToString(),
                    FloatValue f => f.Value.ToString(),
                    StringValue s => Regex.Unescape(s.Value.Trim('"')),
                    BoolValue b => b.Value.ToString(),
                    _ => throw new SemanticError("Error Semantico: Datos invalidos para el arreglo", null)
                })) + " ]",


                MatrixValue matrix => "  " + string.Join("  ", matrix.Value.Select(row =>
                    "[ " + string.Join(", ", row.Select(value => value switch
                    {
                        IntValue i => i.Value.ToString(),
                        FloatValue f => f.Value.ToString(),
                        StringValue s => Regex.Unescape(s.Value.Trim('"')),
                        BoolValue b => b.Value.ToString(),
                        _ => throw new SemanticError("Error Semantico: Datos invalidos en la matriz", null)
                    })) + " ]\n"
                )) + "",

                StructValue structValue => structValue.languageStruct.Name + " { " + string.Join(", ", structValue.languageStruct.Props.Select(p => p.Key + ": " + p.Value)) + " }",

                _ => throw new SemanticError("Error Semantico: parametros invalidos", null)
            };
        }

        output += "\n";
        visitor.output += output;
        return visitor.defaultVoid;
    }


    

    
}

public class StrconvAtoi : Invocable
{
    public int Arity()
    {
        return 1;
    }

    public ValueWrapper Invoke(List<ValueWrapper> args, CompilerVisitor visitor)
    {
        if (args[0] is not StringValue stringValue)
        {
            throw new SemanticError("Error Semantico: argumento invalido valor no aceptado en strconv.Atoi", null);
        }

        try
        {
            if (int.TryParse(stringValue.Value, out int result))
            {
                return new IntValue(result);
            }
            else
            {
                throw new SemanticError("Error Semantico: la funcion srtcon.Atoi no convierte los valores float", null);
            }
        }
        catch (SemanticError ex)
        {
             throw new SemanticError("Error Semantico: argumento invalido valor no aceptado en strconv.Atoi", null);
        }
    }
}

public class StrconFloat : Invocable
{
    public int Arity()
    {
        return 1;
    }

    public ValueWrapper Invoke(List<ValueWrapper> args, CompilerVisitor visitor)
    {
        if (args[0] is not StringValue stringValue)
        {
            throw new SemanticError("Error Semántico: El argumento debe ser una cadena de texto.", null);
        }

        if (decimal.TryParse(stringValue.Value, NumberStyles.Float, CultureInfo.InvariantCulture, out decimal result))
        {
            return new FloatValue(result);
        }

        throw new SemanticError($"Error Semántico: No se pudo convertir '{stringValue.Value}' en un número flotante.", null);
    }
}

public class ReflecType : Invocable
{
    public int Arity()
    {
        return 1;
    }

    public ValueWrapper Invoke(List<ValueWrapper> args, CompilerVisitor visitor)
    {
        if (args[0] is not ValueWrapper value)
        {
            throw new SemanticError("Error Semantico: argumento invalido", null);
        }

        return value switch
        {
            IntValue _ => new StringValue("int"),
            FloatValue _ => new StringValue("float64"),
            StringValue _ => new StringValue("string"),
            BoolValue _ => new StringValue("bool"),
            RuneValue _ => new StringValue("rune"),
            ArrayValue arrayValue => TyperArray(arrayValue),
            _ => throw new SemanticError("Error Semantico: argumento invalido", null)
        };
    }

    private StringValue TyperArray(ArrayValue arrayValue)
    {
        if (arrayValue.Value.Count == 0)
        {
            return new StringValue("array<empty>");
        }

        var firstElement = arrayValue.Value[0];

        string elementType = firstElement switch
        {
            IntValue _ => "int",
            FloatValue _ => "float64",
            StringValue _ => "string",
            BoolValue _ => "bool",
            RuneValue _ => "rune",
            _ => "unknown"
        };

        return new StringValue("[]" + elementType);
    }
}




// Implementacion de las funciones de las estructuras de datos ARRAY y MATRIX
public class SliceIndex : Invocable
{
    public int Arity()
    {
        return 1;
    }

    public ValueWrapper Invoke(List<ValueWrapper> args, CompilerVisitor visitor)
    {
        if (args[0] is not ArrayValue array || args[1] is not ValueWrapper valor)
        {
            throw new SemanticError("Erro Semantico: argumentos invalidos", null);
        }

        // validacion de tipo de datos y comparacion
        int index = array.Value.FindIndex(v => v.GetType() == valor.GetType() && (v, valor) switch
        {
            (IntValue intA, IntValue intB) => intA.Value == intB.Value,
            (FloatValue floatA, FloatValue floatB) => floatA.Value == floatB.Value,
            (StringValue stringA, StringValue stringB) => stringA.Value == stringB.Value,
            (BoolValue boolA, BoolValue boolB) => boolA.Value == boolB.Value,
            _ => false
        });

        return new IntValue(index);
    }

}

public class StringsJoin : Invocable
{
    public int Arity()
    {
        return 2;  // Cambiado a 2, ya que ahora tiene dos argumentos
    }

    public ValueWrapper Invoke(List<ValueWrapper> args, CompilerVisitor visitor)
    {
        if (args[0] is not ArrayValue array || args[1] is not StringValue delimiter)
        {
            throw new SemanticError("Error Semantico: argumentos invalidos", null);
        }


        // Unir los elementos del arreglo con el delimitador
        var output = string.Join(Regex.Unescape(delimiter.Value.Trim('"')), array.Value.Select(v => v switch
        {
            IntValue i => i.Value.ToString(),
            FloatValue f => f.Value.ToString(),
            StringValue s => Regex.Unescape(s.Value.Trim('"')),
            BoolValue b => b.Value.ToString(),
            _ => throw new SemanticError("Error Semantico: Datos invalidos en el arreglo", null)
        }));

        return new StringValue(output);
    }
}

public class Len : Invocable
{
    public int Arity()
    {
        return 1;
    }

    public ValueWrapper Invoke(List<ValueWrapper> args, CompilerVisitor visitor)
    {
    
        if (args[0] is not ArrayValue array && args[0] is not MatrixValue matrix)
        {
            throw new SemanticError("Error Semantico: argumento erroneo ", null);
        }
        else if (args[0] is MatrixValue MatrixValue)
        {
            return new IntValue(MatrixValue.Value.Count);
        }
        else if (args[0] is ArrayValue arrayValue)
        {
            return new IntValue(arrayValue.Value.Count);
        }

        return visitor.defaultVoid;
    }
}

public class Append : Invocable
{
    public int Arity()
    {
        return 2; // Cambiado a 2, ya que ahora tiene dos argumentos
    }

    public ValueWrapper Invoke(List<ValueWrapper> args, CompilerVisitor visitor)
    {
        if (args[0] is ArrayValue array && args[1] is ValueWrapper value)
        {
            array.Value.Add(value);
        }
        else if (args[0] is MatrixValue matrix && args[1] is ArrayValue arrayValue)
        {
            matrix.Value.Add(arrayValue.Value);
        }
        else
        {
            throw new SemanticError("Error Semantico: argumentos invalidos", null);
        }

        return visitor.defaultVoid;
    }
}