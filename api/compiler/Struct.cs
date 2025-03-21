using analyzer;

public class LanguageStruct : Invocable
{
   public string Name { get; set; }
   public Dictionary<string, LanguageParser.VarDclContext> Props { get; set; }
  
   public LanguageStruct(string name , Dictionary<string, LanguageParser.VarDclContext> props)
   {
       Name = name;
       Props = props;
 
   }


   public int Arity()
   {

         return 0;
   }

    public ValueWrapper Invoke(List<ValueWrapper> arguments, CompilerVisitor visitor)
    {
      var newInstancia = new Instancia(this);


      foreach (var prop in Props)
      {
        
         var name = prop.Key;
         var value = prop.Value;

        if(value is LanguageParser.VarDclContext context)
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

            newInstancia.SeT(name, defaultValue);


        }
   
      }


      return new InstanciaValue(newInstancia);
    }

     
}
