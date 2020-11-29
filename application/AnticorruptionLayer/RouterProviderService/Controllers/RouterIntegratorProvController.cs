using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using ApplicationCore.RouterProvider.Entity;
using ApplicationCore.RouterProvider.Interfaces;
using DomainModel.Dto;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;


namespace RouterProviderService.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class RouterIntegratorProvController : ControllerBase
    {
        private readonly IRouterService _routerService;
        private readonly IMapperService _mapperService;
        private readonly ILogger<RouterIntegratorProvController> _logger;

        public RouterIntegratorProvController(IRouterService routerService, IMapperService mapperService, ILogger<RouterIntegratorProvController> logger)
        {
            _routerService = routerService;
            _mapperService = mapperService;
            _logger = logger;
        }

        /// <summary>
        /// Servicio de búsqueda genérica para cualquier proveedor
        /// </summary>
        /// <param name="type"> transport | show | hotel </param>
        /// <param name="info"></param>
        /// <returns></returns>
        [Route("search")]
        [HttpPost]
        public async Task<IActionResult> Search([FromBody]GeneralRequest info)
        {
            if (!ModelState.IsValid || info.InformationProvider.ProviderType==null || info.Request.IdBusqueda==null)
                return BadRequest(ModelState);
            
            try
            {
                var dto = await _mapperService.GetGeneralDtoAsync(info);
                var result = await _routerService.Search(dto);
                return Ok(result);
            }
            catch (Exception ex)
            {
                return GetErrorServerCode(ex);
            }
        }


        [Route("book")]
        [HttpPost]
        public async Task<IActionResult> Book([FromBody]GeneralRequest info)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);
            try
            {
                var dto = await _mapperService.GetGeneralDtoAsync(info);
                var result = await _routerService.Book(dto);
                return Ok(result);
            }
            catch (Exception ex)
            {
                return GetErrorServerCode(ex);
            }
        }


        private IActionResult GetErrorServerCode(object value=null)
        {
            if (value == null)            
                return StatusCode(StatusCodes.Status500InternalServerError);
            
            return StatusCode(StatusCodes.Status500InternalServerError, value);
        }
    }
}