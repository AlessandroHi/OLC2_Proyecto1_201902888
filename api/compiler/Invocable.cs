using api.Controllers;

public interface Invocable
{   
    int Arity();
    ValueWrapper Invoke(List<ValueWrapper> args, CompilerVisitor visitor); //recibe una lista de parametros y el visitor para poder acceder a las variables globales
}