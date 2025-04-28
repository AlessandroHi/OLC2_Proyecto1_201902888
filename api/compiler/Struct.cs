using analyzer;
using System;
using System.Collections.Generic;
using System.Linq;

public class LanguageStruct : Invocable
{
    public string Name { get; set; }
    public Dictionary<string, LanguageParser.VarDclContext> Props { get; set; }

    public LanguageStruct(string name, Dictionary<string, LanguageParser.VarDclContext> props)
    {
        Name = name;
        Props = props;
    }

    public int Arity()
    {
        return Props.Count;
    }

    public ValueWrapper Invoke(List<ValueWrapper> arguments, CompilerVisitor visitor)
    {
        var newInstancia = new Instancia(this);

        foreach (var prop in Props)
        {
            var valuede = visitor.Visit(prop.Value); 


            newInstancia.SeT(prop.Key, valuede);
        }

        // Asignar valores de los argumentos
        if (Props.Count != arguments.Count)
        {
            throw new SemanticError($"Error Semántico: Se esperaban {Props.Count} argumentos, pero se recibieron {arguments.Count}.", null);
        }

        for (int i = 0; i < Props.Count; i++)
        {
            var prop = Props.ElementAt(i);
            var value = arguments[i];

            newInstancia.SeT(prop.Key, value);
        }

        return new InstanciaValue(newInstancia);
    }
}
