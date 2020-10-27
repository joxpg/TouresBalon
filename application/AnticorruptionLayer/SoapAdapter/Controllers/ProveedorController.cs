using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Net.Http;
using System.Threading.Tasks;

namespace SoapAdapter.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ProveedorController : ControllerBase
    {
        
        public async Task<IActionResult> GetHotels()
        {
            var _endPoint = @"http://localhost:59426/api/Hotels";
            //Creamos el cliente http para consumir el api
            var httpClient = new HttpClient(); 
            var messageResponse = await httpClient.GetAsync(_endPoint).ConfigureAwait(false);
            Console.WriteLine(messageResponse);
            return Ok(messageResponse);
        }

        [HttpGet]
        public IEnumerable<int> GetAll()
        {
            return new List<int> { 1, 2, 3, 4, 5 };
        }

    }
}