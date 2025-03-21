using analyzer;

public class ForeneaFuncion : Invocable
{

    private Environment clousure;
    private LanguageParser.FuncDclContext context;

    public ForeneaFuncion(Environment clousure, LanguageParser.FuncDclContext context)
    {
        this.clousure = clousure;
        this.context = context;
    }

    public int Arity()
    {
        if (context.@params() == null)
        {
            return 0;
        }

        return context.@params().ID().Length;
    }


    public ValueWrapper Invoke(List<ValueWrapper> arguments, CompilerVisitor visitor)
    {
        var newEnv = new Environment(clousure);

        var beforEnv = visitor.currentEnvironment;

        visitor.currentEnvironment = newEnv;

        if (context.@params() != null)
        {
            for (int i = 0; i < context.@params().ID().Length; i++)
            {
                newEnv.DeclareSymbol(context.@params().ID(i).GetText(), arguments[i], null);
            }
        }

        try
        {
            foreach (var statement in context.dcl())
            {
                visitor.Visit(statement);
            }
        }
        catch (ReturnException e)
        {
            visitor.currentEnvironment = beforEnv;
            return e.Value;
        }

        visitor.currentEnvironment = beforEnv;
        return visitor.defaultVoid;


    }

    public ForeneaFuncion Bind(Instancia instancia)
    {
        var hiddEnv = new Environment(clousure);
        hiddEnv.DeclareSymbol("this", new InstanciaValue(instancia), null);
        return new ForeneaFuncion(hiddEnv, context);

    }

}