public abstract record ValueWrapper;


public record IntValue(int Value) : ValueWrapper;
public record FloatValue(decimal Value) : ValueWrapper;
public record StringValue(string Value) : ValueWrapper;
public record BoolValue(bool Value) : ValueWrapper;
public record RuneValue(char Value) : ValueWrapper;
public record NillValue(object? Value) : ValueWrapper;





public record ArrayValue(List<ValueWrapper> Value) : ValueWrapper;
public record MatrixValue(List<List<ValueWrapper>> Value) : ValueWrapper;
public record FunctionValue(Invocable invocable, string name) : ValueWrapper;
public record InstanciaValue(Instancia instancia) : ValueWrapper;
public record StructValue(LanguageStruct languageStruct) : ValueWrapper;

public record VoidValue : ValueWrapper;


