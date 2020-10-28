using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using DomainModel.Dto;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using RouterProviderService.Interface;

namespace RouterProviderService.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class RouterIntegratorProvController : ControllerBase
    {
        private readonly IRouterService _routerService;

        public RouterIntegratorProvController(IRouterService routerService)
        {
            _routerService = routerService;
        }
  
        /// <summary>
        /// 
        /// </summary>
        /// <param name="type"> transport | show | hotel </param>
        /// <param name="info"></param>
        /// <returns></returns>
        [Route("search")]
        [HttpPost]
        public async Task<IActionResult> Search([FromBody]GeneralDto info)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);
            try
            {
                return Ok();
            }
            catch (Exception)
            {
                return GetErrorServerCode();
            }
        }


        [Route("book")]
        [HttpPost]
        public async Task<IActionResult> Book([FromBody]GeneralDto info)
        {
            return GetErrorServerCode();
        }


        private IActionResult GetErrorServerCode()
        {
            return StatusCode(StatusCodes.Status500InternalServerError);
        }
    }
}