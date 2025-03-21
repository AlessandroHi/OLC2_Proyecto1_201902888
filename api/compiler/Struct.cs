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
        Console.WriteLine(name);
        Console.WriteLine(value.GetText());
   
    
        
        if(value.expr() != null)
        {
            
            var val = visitor.Visit(value.expr());
           
            newInstancia.SeT(name, val);
        }
        else
        {
            newInstancia.SeT(name, new VoidValue());
        }
      }


      return new InstanciaValue(newInstancia);
    }

     
}
