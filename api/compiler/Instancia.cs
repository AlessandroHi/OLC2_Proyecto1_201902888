public class Instancia {
    private LanguageStruct languageStruct;
    private Dictionary<string, ValueWrapper> Propiedades;

    public Instancia(LanguageStruct languageStruct)
    {
        this.languageStruct = languageStruct;
        Propiedades = new Dictionary<string, ValueWrapper>();
    }

    public void SeT(string name, ValueWrapper value)
    {
    
        Propiedades[name] = value;

        foreach(var pro in Propiedades)
        {
            Console.WriteLine(pro.Key);
            Console.WriteLine(pro.Value);
        }
        
   
    }

    public ValueWrapper Get(string name, Antlr4.Runtime.IToken token)
    {
        if (Propiedades.ContainsKey(name))
        {
            return Propiedades[name];
        }


        throw new SemanticError("Error Semantico: Propiedad " + name + " no encontrada", token);
    }


}