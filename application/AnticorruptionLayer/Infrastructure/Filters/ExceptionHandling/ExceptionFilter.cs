using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Filters;
using Microsoft.Extensions.Logging;
using System.Net;
using System.Threading.Tasks;


namespace Infrastructure.Filters.ExceptionHandling
{
    /// <summary>
    /// Maneje de exepciones generales del sistema
    /// </summary>
    public class ExceptionFilter : IAsyncExceptionFilter
    {
        private readonly ILogger<ExceptionFilter> _logger;

        public ExceptionFilter(ILogger<ExceptionFilter> logger)
        {
            _logger = logger;
        }

        /// <summary>
        /// Recibe un lanzamiento de execpión, lo registra en el archivo de log y enmascara un mensaje (json) para devolverlo como respuesta al cliente
        /// </summary>
        /// <param name="context">Contexto que contiene las excepciones lanzadas</param>
        /// <returns></returns>
        public Task OnExceptionAsync(ExceptionContext context)
        {
            _logger.LogInformation("OnExceptionAsync: " + context.Exception.Message);

            var statusCode = HttpStatusCode.InternalServerError;//devuelve un error 500
            context.HttpContext.Response.ContentType = "application/json";
            context.HttpContext.Response.StatusCode = (int)statusCode;
            context.Result = new JsonResult(new
            {
                error = new[] { $"{StatusCodes.Status500InternalServerError}" }
            });


            return Task.CompletedTask;
        }
    }
}
