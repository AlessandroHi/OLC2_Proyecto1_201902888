using api.Controllers;

public interface Invocable
{   
    int Arity();
    ValueWrapper Invoke(List<ValueWrapper> args, CompilerVisitor visitor);
}