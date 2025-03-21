using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.ComponentModel.DataAnnotations;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;
using analyzer;
using Antlr4.Runtime;
using Antlr4.Runtime.Misc;
using Antlr4.Runtime.Tree;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;

namespace api.Controllers
{
    [Route("[controller]")]
    public class Compile : Controller
    {
        private readonly ILogger<Compile> _logger;

        public Compile(ILogger<Compile> logger)
        {
            _logger = logger;
        }

        public class CompileRequest
        {
            [Required]
            public required string code { get; set; }
        }

        // POST /compile
        [HttpPost]
        public IActionResult Post([FromBody] CompileRequest request)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(new { error = "Invalid request" });
            }

            var inputStream = new AntlrInputStream(request.code);
            var lexer = new LanguageLexer(inputStream);

            // Add custom error listener
            lexer.RemoveErrorListeners();
            lexer.AddErrorListener(new LexicalErrorListener());

            var tokens = new CommonTokenStream(lexer);
            var parser = new LanguageParser(tokens);

            parser.RemoveErrorListeners();
            parser.AddErrorListener(new SyntaxErrorListener());

            try
            {
                var tree = parser.program();

                var visitor = new CompilerVisitor();
                visitor.Visit(tree);
                visitor.ExecuteMain();
                

                return Ok(new { result = visitor.output });

            }
            catch (ParseCanceledException ex)
            {
                return BadRequest(new { error = ex.Message });
            }
            catch (SemanticError ex)
            {
                return BadRequest(new { error = ex.Message });
            }
            catch (ContinueException)
            {
                return BadRequest(new { error = "Statment no loop" });
            }
        }


        [HttpPost("ast")]

        public async Task<IActionResult> GetAst([FromBody] CompileRequest request)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(new { error = "Invalid request" });
            }

            string grammarPath = Path.Combine(Directory.GetCurrentDirectory(), "grammars/Language.g4");
            var grammar = "";

            try
            {
                if (System.IO.File.Exists(grammarPath))
                {
                    grammar = await System.IO.File.ReadAllTextAsync(grammarPath);
                }
                else
                {
                    return BadRequest(new { error = "Grammar file not found" });
                }
            }
            catch (System.Exception)
            {
                return BadRequest(new { error = "Error leer la gramatica" });
            }

  

            var payload = new
            {
                grammar,
                input = request.code,
                lexgrammar = "",
                start = "program"
            };

           
            var JsonPayload = JsonSerializer.Serialize(payload);
            var context = new StringContent(JsonPayload, Encoding.UTF8, "application/json");    

            using (var client = new HttpClient())
            {
                try{

                    HttpResponseMessage response = await client.PostAsync("http://lab.antlr.org/parse/", context);
                    response.EnsureSuccessStatusCode();

                    string result = await response.Content.ReadAsStringAsync();
                    using var doc = JsonDocument.Parse(result);
                    var root = doc.RootElement;

                    if(root.TryGetProperty("result", out JsonElement resultElement)&& resultElement.TryGetProperty("svgtree", out JsonElement svgElement))
                    {
                        string svgtree = svgElement.GetString() ?? string.Empty;
                        return Content(svgtree, "image/svg+xml");
                    }

                    return BadRequest(new { error = "Error al obtener el arbol" });


                }
                catch(System.Exception)
                {
                    return BadRequest(new { error = "Error al conectar con el servidor" });
                }
              

            }



        }

    }
}