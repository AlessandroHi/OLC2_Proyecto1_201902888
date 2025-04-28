public class Environment
{

    public Dictionary<string, ValueWrapper> Symbols = new Dictionary<string, ValueWrapper>();
    public Dictionary<string, ValueWrapper> funcEmbeded = new Dictionary<string, ValueWrapper>();
    private Environment? parent;

    public Environment(Environment? parent)
    {
        this.parent = parent;
    }

    public void DeclareEmbeded(string id, ValueWrapper value, Antlr4.Runtime.IToken token)
    {

        if (funcEmbeded.ContainsKey(id))
        {
            if(token != null) throw new SemanticError("Embeded " + id + " already declared", token);
        }
        else
        {
            funcEmbeded[id] = value;
        }
    }

    public ValueWrapper GetEmbeded(string id, Antlr4.Runtime.IToken token)
    {
        if (funcEmbeded.ContainsKey(id))
        {
            return funcEmbeded[id];
        }

        if (parent != null)
        {
            return parent.GetEmbeded(id, token);
        }
        throw new SemanticError("Embeded " + id + " not found", token);
    }


    public ValueWrapper GetSymbol(string id, Antlr4.Runtime.IToken token)
    {
        if (Symbols.ContainsKey(id))
        {
            return Symbols[id];
        }

        if (parent != null)
        {
            return parent.GetSymbol(id, token);
        }

        throw new SemanticError("Variable " + id + " no encontrada", token);
    }

    public void DeclareSymbol(string id, ValueWrapper value, Antlr4.Runtime.IToken token)
    {
       
        if (Symbols.ContainsKey(id))
        {
            if(token != null) throw new SemanticError("Variable " + id + " ya declarada", token);
        }
        else
        {
           
            Symbols[id] = value;
            
        }
    }

    public ValueWrapper AssignSymbol(string id, ValueWrapper value , Antlr4.Runtime.IToken token)
    {
        if (Symbols.ContainsKey(id))
        {
            Symbols[id] = value;
            return value;
        }

        if (parent != null)
        {
            return parent.AssignSymbol(id, value, token);
        }

        throw new SemanticError("Variable " + id + " no encontrada", token);
    }



}