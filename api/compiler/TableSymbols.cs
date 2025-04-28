using System;
using System.Collections.Generic;

namespace Proyecto1_OLC2
{
    public class Symbol
    {
        public string Id { get; set; }
        public string Tipo { get; set; }
        public string TipoDato { get; set; }
        public string Ambito { get; set; }
        public int Linea { get; set; }
        public int Columna { get; set; }

        public Symbol(string id, string tipo, string tipoDato, string ambito, int linea, int columna)
        {
            Id = id;
            Tipo = tipo;
            TipoDato = tipoDato;
            Ambito = ambito;
            Linea = linea;
            Columna = columna;
        }

    }

    public class SymbolTable
    {
        public List<Symbol> symbols = new List<Symbol>();

        public SymbolTable()
        {
            symbols = new List<Symbol>();
        }

        public void AddSymbol(Symbol symbol)
        {
            symbols.Add(symbol);
        }

        public List<Symbol> getList()
        {
            return symbols;
        }

    }
}