using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;

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

            //Type type = typeof(GeneralDto);

            //foreach (var item in type.GetProperties())
            //{
            //    if (item.Name == "GeneralFlightInfo")
            //    {
            //        Type type1 = GetTypeFromDll(type.Assembly.FullName, item.PropertyType.FullName);
            //        foreach (var item1 in type1.GetProperties())
            //        {
            //            var yy = item1.Name;
            //        }
            //    }
            //}


            var rng = new Random();
            return Enumerable.Range(1, 5).Select(index => new WeatherForecast
            {
                Date = DateTime.Now.AddDays(index),
                TemperatureC = rng.Next(-20, 55),
                Summary = Summaries[rng.Next(Summaries.Length)]
            })
            .ToArray();
        }
    }
}
