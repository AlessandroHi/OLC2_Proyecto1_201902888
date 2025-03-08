'use client';

import { useState } from 'react';

export default function Menu() {
  const [menuOpen, setMenuOpen] = useState(false);
  const [fileSubmenuOpen, setFileSubmenuOpen] = useState(false);
  const [reportsSubmenuOpen, setReportsSubmenuOpen] = useState(false);

  const toggleMenu = () => {
    setMenuOpen(prev => !prev);
    if (menuOpen) {
      setFileSubmenuOpen(false);
      setReportsSubmenuOpen(false);
    }
  };

  const toggleSubmenu = (submenuSetter: (value: (prev: boolean) => boolean) => void) => (e: { stopPropagation: () => void; }) => {
    e.stopPropagation();
    submenuSetter(prev => !prev);
  };

  return (
    <div className="relative">
      <button
        className="text-white font-semibold py-2 px-4 bg-indigo-600 hover:bg-indigo-700 rounded"
        onClick={toggleMenu}
      >
        Menú
      </button>
      {menuOpen && (
        <div className="absolute top-full left-0 mt-2 w-48 bg-indigo-700 rounded-md shadow-lg z-10">
          <ul className="py-1">
            <li
              className="px-4 py-2 hover:bg-indigo-600 text-white cursor-pointer flex justify-between items-center relative"
              onClick={toggleSubmenu(setFileSubmenuOpen)}
            >
              File
              <svg className="w-4 h-4 fill-current text-white" viewBox="0 0 20 20">
                <path d="M7 7l5 5-5 5V7z" />
              </svg>
              {fileSubmenuOpen && (
                <ul className="absolute top-0 left-full ml-1 w-40 bg-indigo-800 rounded-md shadow-lg z-10">
                  <li className="px-4 py-2 hover:bg-indigo-600 text-white cursor-pointer">New File</li>
                  <li className="px-4 py-2 hover:bg-indigo-600 text-white cursor-pointer">Open File</li>
                  <li className="px-4 py-2 hover:bg-indigo-600 text-white cursor-pointer">Save File</li>
                </ul>
              )}
            </li>
            <li
              className="px-4 py-2 hover:bg-indigo-600 text-white cursor-pointer flex justify-between items-center relative"
              onClick={toggleSubmenu(setReportsSubmenuOpen)}
            >
              Reportes
              <svg className="w-4 h-4 fill-current text-white" viewBox="0 0 20 20">
                <path d="M7 7l5 5-5 5V7z" />
              </svg>
              {reportsSubmenuOpen && (
                <ul className="absolute top-0 left-full ml-1 w-40 bg-indigo-800 rounded-md shadow-lg z-10">
                  <li className="px-4 py-2 hover:bg-indigo-600 text-white cursor-pointer">Reporte Errores</li>
                  <li className="px-4 py-2 hover:bg-indigo-600 text-white cursor-pointer">Tabla de Simbolos</li>
                  <li className="px-4 py-2 hover:bg-indigo-600 text-white cursor-pointer">AST</li>
                </ul>
              )}
            </li>
          </ul>
        </div>
      )}
    </div>
  );
}
