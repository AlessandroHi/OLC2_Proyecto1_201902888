using System.Reflection.Metadata;
using Antlr4.Runtime;
using Antlr4.Runtime.Misc;

public class SemanticError : Exception
{
   
   private string message;
   private Antlr4.Runtime.IToken token;
   
    public SemanticError(string message, Antlr4.Runtime.IToken token)
    {
        this.message = message;
        this.token = token;
    }

    public override string Message
    {
        get
        {
            return message + " en linea " + token.Line + ", Columnna " + token.Column;
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