using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Net.Http;
using System.Threading.Tasks;

namespace RouterProviderService.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class WeatherForecastController : ControllerBase
    {
        private static readonly string[] Summaries = new[]
        {
            "Freezing", "Bracing", "Chilly", "Cool", "Mild", "Warm", "Balmy", "Hot", "Sweltering", "Scorching"
        };

        private readonly ILogger<WeatherForecastController> _logger;


        public WeatherForecastController(ILogger<WeatherForecastController> logger)
        {
            _logger = logger;
        }

        private Type GetTypeFromDll(string assembly, string fullNameObject)
        {
            Assembly a = Assembly.Load(assembly);
            Type t = a.GetType(fullNameObject);
            return t;
        }

        [HttpGet]
        public IEnumerable<WeatherForecast> Get()
        {

            var rng = new Random();
            return Enumerable.Range(1, 5).Select(index => new WeatherForecast
            {
                Date = DateTime.Now.AddDays(index),
                TemperatureC = rng.Next(-20, 55),
                Summary = Summaries[rng.Next(Summaries.Length)]
            })
            .ToArray();
        }

        [Route("Rest")]
        [HttpGet]
        public async Task<IActionResult> TestRest()
        {
            try
            {
                var url = @"http://rest-adapter-service/api/restadapter/Provider";
               var result = await GetAsync(url);
                if (result.IsSuccessStatusCode)
                {
                   return Ok(result.Content.ReadAsStringAsync());
                }
                else
                {
                    return BadRequest("Error consumiendo el servicio REST");
                }
            }
            catch (Exception ex)
            {
                _logger.LogInformation(ex.Message);
                return BadRequest($"Error consumiendo el servicio REST. {ex.Message}");
            }

        }


        [Route("Soap")]
        [HttpGet]
        public IActionResult TestSoap()
        {
            return Ok("Servicio Rest OK");
        }

        private async Task<HttpResponseMessage> GetAsync(string endpoint)
        {
            var httpClient = new HttpClient();
            var messageResponse = await httpClient.GetAsync(endpoint).ConfigureAwait(false);
            return messageResponse;
        }
    }
}
