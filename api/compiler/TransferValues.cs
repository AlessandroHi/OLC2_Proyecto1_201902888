public class BreakException : Exception
{
    public BreakException() : base("Break statement")
    {
    
    }
}

public class ContinueException : Exception
{
    public ContinueException() : base("Continue statement")
    {
    }
}

public class ReturnException : Exception
{
    public ValueWrapper Value { get; set; }

    public ReturnException(ValueWrapper value) : base("Return statement")
    {
        Value = value;
    }
}