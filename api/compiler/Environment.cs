public class Environment
{

    public Dictionary<string, ValueWrapper> variables = new Dictionary<string, ValueWrapper>();
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


    public ValueWrapper GetVariable(string id, Antlr4.Runtime.IToken token)
    {
        if (variables.ContainsKey(id))
        {
            return variables[id];
        }

        if (parent != null)
        {
            return parent.GetVariable(id, token);
        }

        throw new SemanticError("Variable " + id + " not found", token);
    }

    public void DeclareVariable(string id, ValueWrapper value, Antlr4.Runtime.IToken token)
    {

        if (variables.ContainsKey(id))
        {
            if(token != null) throw new SemanticError("Variable " + id + " already declared", token);
        }
        else
        {
            variables[id] = value;
        }
    }

    public ValueWrapper AssignVariable(string id, ValueWrapper value , Antlr4.Runtime.IToken token)
    {
        if (variables.ContainsKey(id))
        {
            variables[id] = value;
            return value;
        }

        if (parent != null)
        {
            return parent.AssignVariable(id, value, token);
        }

        throw new SemanticError("Variable " + id + " no encontrada", token);
    }

}