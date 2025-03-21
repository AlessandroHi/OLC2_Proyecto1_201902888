using System.Reflection.Metadata;
using Antlr4.Runtime;
using Antlr4.Runtime.Misc;

public class SemanticError : Exception
{
    private string message;
    private Antlr4.Runtime.IToken? token;

    public SemanticError(string message, Antlr4.Runtime.IToken? token = null)
    {
        this.message = message;
        this.token = token;
    }

    public override string Message
    {
        get
        {
            if (token != null)
                return $"{message} en linea {token.Line}, Columna {token.Column}";
            else
                return message; // Evita acceder a un token nulo
        }
    }
}

public class LexicalErrorListener : BaseErrorListener, IAntlrErrorListener<int>
{
    public void SyntaxError(TextWriter output, IRecognizer recognizer, int offendingSymbol, int line, int charPositionInLine, string msg, RecognitionException e)
    {
        throw new ParseCanceledException($"Error lexico en linea {line}:{charPositionInLine} - {msg}");
    }
}

public class SyntaxErrorListener : BaseErrorListener
{
    public override void SyntaxError(TextWriter output, IRecognizer recognizer, IToken offendingSymbol, int line, int charPositionInLine, string msg, RecognitionException e)
    {
        throw new ParseCanceledException($"Error sintactico en linea {line}:{charPositionInLine} - {msg}");
    }
}

