"use client";
import { Editor } from "@monaco-editor/react";
import { useState } from "react";
import Menu from "./components/navbar";
import Image from "next/image";
import logo from "../public/img/logo.png";
import { Console } from "console";

const API_URL = "http://localhost:5159";

export default function Home() {
  const [code, setCode] = useState<string>("");
  const [output, setOutput] = useState<string>("");
  const [listSymbols, setListSymbols] = useState<{
    id: string;
    tipo: string;
    tipoDato: string;
    ambito: string;
    linea: number;
    columna: number;
  }[] | null>(null);
  const [isError, setIsError] = useState<boolean>(false); // Estado para controlar el color del output
  const [cursorPosition, setCursorPosition] = useState<{
    line: number;
    column: number;
  }>({
    line: 1,
    column: 1,
  });


  const handleExecute = async () => {
    try {
      const response = await fetch(`${API_URL}/compile`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ code }),
      });

      const data = await response.json();

      if (!response.ok) {
        throw new Error(data.error || "Error desconocido");
      }

      setOutput(data.result);
      setListSymbols(data.symbols);
      setIsError(false); // Si no hay error
    } catch (err) {
      setOutput(err instanceof Error ? err.message : "Error desconocido");
      setIsError(true); // Si hay error
    }
  };


  const handleFileOpen = (event: React.ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0];
    if (!file) {
      console.error("No file selected");
      return;
    }

    if (!file.name.endsWith(".glt")) {
      console.error("Invalid file type. Only .gtl files are allowed.");
      alert("Solo se permiten archivos con extensión .gtl");
      return;
    }
  };

  const handleFileUpload = (content: string) => {
    setCode(content);
  };

  
  
  return (
    <div className="flex flex-col h-screen bg-gray-900 text-white">
      {/* Barra de herramientas */}
      <div className="w-full bg-gray-800 p-4 flex justify-between items-center shadow-md relative h-15">
        <div className="flex items-center space-x-4">
          <Image src={logo} alt="Logo" width={50} height={50} />
          <h1 className="text-xl font-bold tracking-wide text-white">
            GoLight
          </h1>
          <Menu code={code} onFileUpload={handleFileUpload} listSymbols={listSymbols} /> 

        </div>
        
        <div className="flex space-x-2">
          <button
            className="bg-green-700 hover:bg-green-600 transition-colors text-white font-semibold py-2 px-5 rounded-lg shadow"
            onClick={handleExecute}
          >
            Ejecutar
          </button>
          <button
            className="bg-red-600 hover:bg-red-700 transition-colors text-white font-semibold py-2 px-5 rounded-lg shadow"
            onClick={() => setCode("")}
          >
            Limpiar
          </button>
        </div>
      </div>

      {/* Contenedor principal (Editor + Consola) */}
      <div className="flex-1 flex flex-col p-6 space-y-6">
        {/* Editor de código */}
        <div className="min-h-[400px] flex-1 relative border border-gray-700 rounded-lg shadow-lg overflow-hidden">
          <Editor
            height="calc(100% - 38px)"
            defaultLanguage="go"
            theme="vs-dark"
            value={code}
            onChange={(value) => setCode(value || "")}
            onMount={(editor) => {
              editor.onDidChangeCursorPosition((event) => {
                setCursorPosition({
                  line: event.position.lineNumber,
                  column: event.position.column,
                });
              });
            }}
          />
          {/* Barra de posición del cursor */}
          <div className="absolute bottom-0 left-0 right-0 bg-gray-800 text-gray-400 p-2 text-sm border-t border-gray-700">
            Línea: {cursorPosition.line} | Columna: {cursorPosition.column}
          </div>
        </div>

        {/* Consola/Terminal */}
        <div
          className={`p-5 rounded-lg font-mono border border-gray-700 shadow-md min-h-[230px] max-h-[230px] overflow-auto ${
            isError ? "bg-black text-red-400" : "bg-black text-green-400"
          }`}
        >
          <pre className="whitespace-pre-wrap">{output || "Output"}</pre>
        </div>
      </div>

    </div>
  );
}
