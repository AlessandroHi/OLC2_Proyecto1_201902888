"use client";

import { useState } from "react";

interface MenuProps {
  code: string;
  onFileUpload: (content: string) => void; // Nueva prop para manejar el contenido del archivo
}

export default function Menu({ code, onFileUpload }: MenuProps) {
  const [menuOpen, setMenuOpen] = useState(false);
  const [fileSubmenuOpen, setFileSubmenuOpen] = useState(false);
  const [reportsSubmenuOpen, setReportsSubmenuOpen] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [svgContent, setSvgContent] = useState("");

  const toggleMenu = () => {
    setMenuOpen((prev) => !prev);
    if (menuOpen) {
      setFileSubmenuOpen(false);
      setReportsSubmenuOpen(false);
    }
  };

  const toggleSubmenu =
    (submenuSetter: (value: (prev: boolean) => boolean) => void) =>
    (e: { stopPropagation: () => void }) => {
      e.stopPropagation();
      submenuSetter((prev) => !prev);
    };

  const closeModal = () => {
    setIsModalOpen(false);
  };

  // Función para manejar la selección de archivos
  const handleFileSelect = (event: React.ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = (e) => {
        const content = e.target?.result as string;
        onFileUpload(content); // Pasar el contenido al componente padre
      };
      reader.readAsText(file);
    }
  };

  // Función para obtener el AST y abrir el modal
  const getAST = async () => {
    try {
      const response = await fetch("http://localhost:5159/compile/ast", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ code: code }),
      });

      const AST = await response.text();

      if (!response.ok) {
        throw new Error(AST || "Error desconocido");
      }

      setSvgContent(AST);
      setIsModalOpen(true);
    } catch (err) {
      console.error("Error en getAST:", err);
      setSvgContent("<p>Error al obtener el AST</p>");
      setIsModalOpen(true);
    }
  };

  return (
    <div className="relative">
      <button
        className="text-white font-semibold py-2 px-4 bg-indigo-600 hover:bg-indigo-700 rounded"
        onClick={toggleMenu}
      >
        Menú
      </button>

      {/* Menú desplegable */}
      {menuOpen && (
        <div className="absolute top-full left-0 mt-2 w-48 bg-indigo-700 rounded-md shadow-lg z-10">
          <ul className="py-1">
            {/* Submenú "File" */}
            <li
              className="px-4 py-2 hover:bg-indigo-600 text-white cursor-pointer flex justify-between items-center relative group"
              onMouseEnter={() => setFileSubmenuOpen(true)}
              onMouseLeave={() => setFileSubmenuOpen(false)}
            >
              File
              <svg
                className="w-4 h-4 fill-current text-white"
                viewBox="0 0 20 20"
              >
                <path d="M7 7l5 5-5 5V7z" />
              </svg>
              {fileSubmenuOpen && (
                <ul className="absolute top-0 left-full ml-1 w-40 bg-indigo-800 rounded-md shadow-lg z-10">
                  <li className="px-4 py-2 hover:bg-indigo-600 text-white cursor-pointer">
                    New File
                  </li>
                  <li
                    className="px-4 py-2 hover:bg-indigo-600 text-white cursor-pointer"
                    onClick={() => document.getElementById("fileInput")?.click()} // Simular clic en el input de archivo
                  >
                    Open File
                  </li>
                  <li className="px-4 py-2 hover:bg-indigo-600 text-white cursor-pointer">
                    Save File
                  </li>
                </ul>
              )}
            </li>

            {/* Submenú "Reportes" */}
            <li
              className="px-4 py-2 hover:bg-indigo-600 text-white cursor-pointer flex justify-between items-center relative group"
              onMouseEnter={() => setReportsSubmenuOpen(true)}
              onMouseLeave={() => setReportsSubmenuOpen(false)}
            >
              Reportes
              <svg
                className="w-4 h-4 fill-current text-white"
                viewBox="0 0 20 20"
              >
                <path d="M7 7l5 5-5 5V7z" />
              </svg>
              {reportsSubmenuOpen && (
                <ul className="absolute top-0 left-full ml-1 w-40 bg-indigo-800 rounded-md shadow-lg z-10">
                  <li className="px-4 py-2 hover:bg-indigo-600 text-white cursor-pointer">
                    Reporte Errores
                  </li>
                  <li className="px-4 py-2 hover:bg-indigo-600 text-white cursor-pointer">
                    Tabla de Simbolos
                  </li>
                  <li
                    className="px-4 py-2 hover:bg-indigo-600 text-white cursor-pointer"
                    onClick={getAST}
                  >
                    AST
                  </li>
                </ul>
              )}
            </li>
          </ul>
        </div>
      )}

      {/* Input oculto para seleccionar archivos */}
      <input
        id="fileInput"
        type="file"
        accept=".glt" // Aceptar solo archivos .go o .txt
        style={{ display: "none" }}
        onChange={handleFileSelect}
      />

      {isModalOpen && (
        <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50 z-20">
          <div className="bg-white bg-opacity-95 p-6 rounded-lg shadow-xl w-[95%] max-w-5xl h-[80%] max-h-[80vh] flex flex-col">
            <h2 className="text-2xl font-bold mb-4 text-gray-800">AST</h2>
            <div className="overflow-auto h-full border border-gray-200 rounded-lg p-3 bg-gray-50">
              <div dangerouslySetInnerHTML={{ __html: svgContent }} />
            </div>
            <div className="mt-4 flex justify-end">
              <button
                className="px-6 py-2 bg-red-500 text-white rounded-lg hover:bg-red-600 transition duration-200 focus:outline-none focus:ring-2 focus:ring-red-500 focus:ring-offset-2"
                onClick={closeModal}
              >
                Cerrar
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}