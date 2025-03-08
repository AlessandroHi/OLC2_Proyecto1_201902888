public class Embeded 
{
    public static void Generate(Environment env)
    {
        env.DeclareVariable("time",  new FunctionValue(new TimeEmbeded(), "time"), null);
        env.DeclareEmbeded("fmt.Println", new FunctionValue(new PrintEmbeded(), "fmt.Println"), null);


    }

}


public class TimeEmbeded : Invocable
{
    public int Arity()
    {
        return 0;
    }

    public ValueWrapper Invoke(List<ValueWrapper> args, CompilerVisitor visitor)
    {
        return new StringValue(DateTime.Now.ToString());
    }
}

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
            StringValue s => s.Value.Trim('"') + " ",
            BoolValue b => b.Value.ToString() + " ",
            VoidValue v => "void ",
            FunctionValue fn => fn.name + "> ",
            _ => throw new SemanticError("Invalid value", null)
        };


   
       }
      
       output += "\n";
       visitor.output += output;
       return visitor.defaultVoid;
    }
}