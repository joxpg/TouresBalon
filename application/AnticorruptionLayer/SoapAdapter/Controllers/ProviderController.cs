using System;
using System.Threading.Tasks;
using ApplicationCore.SoapAdapter.Manager.Hotel;
using ApplicationCore.SoapAdapter.Manager.Show;
using ApplicationCore.SoapAdapter.Manager.Transport;
using DomainModel.Dto;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace SoapAdapter.Controllers
{
    [Route("api/soapadapter/[controller]")]
    [ApiController]
    public class ProviderController : ControllerBase
    {
        
        private readonly IFlightServiceManager _fligthManager;
        private readonly IHotelServiceManager _hotelService;
        private readonly IShowServiceManager _showService;

        public ProviderController(
            IFlightServiceManager fligthManager,
            IHotelServiceManager hotelService,
            IShowServiceManager showService
             )
        {
            _fligthManager = fligthManager;
            _hotelService = hotelService;
            _showService = showService;
        }


        [Route("transport/search")]
        [HttpPost]
        public async Task<IActionResult> SearchFlight([FromBody]GeneralDto info)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);
            try
            {
                var result = await _fligthManager.GetResponseSearch(info.InformationProvider, info.GeneralFlightInfo);
                return Ok(result);
            }
            catch (Exception)
            {
                return GetErrorServerCode();
            }
        }

        [Route("transport/book")]
        [HttpPost]
        public async Task<IActionResult> BookFlight([FromBody]GeneralDto info)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);
            try
            {
                var result = await _fligthManager.GetResponseBook(info.InformationProvider, info.GeneralFlightInfo);
                return Ok(result);
            }
            catch (Exception)
            {
                return GetErrorServerCode();
            }
        }

        [Route("hotel/search")]
        [HttpPost]
        public async Task<IActionResult> SearchRoom([FromBody]GeneralDto info)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);
            try
            {
                var result = await _hotelService.GetResponseSearch(info.InformationProvider, info.GeneralHotelInfo);
                return Ok(result);
            }
            catch (Exception)
            {
                return GetErrorServerCode();
            }
        }

        [Route("hotel/book")]
        [HttpPost]
        public async Task<IActionResult> BookRoom([FromBody]GeneralDto info)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);
            try
            {
                var result = await _hotelService.GetResponseBook(info.InformationProvider, info.GeneralHotelInfo);
                return Ok(result);
            }
            catch (Exception)
            {
                return GetErrorServerCode();
            }
        }


        [Route("show/search")]
        [HttpPost]
        public async Task<IActionResult> SearchShow([FromBody]GeneralDto info)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);
            try
            {
                var result = await _showService.GetResponseSearch(info.InformationProvider, info.GeneralShowInfo);
                if (result == null)
                    return NotFound();
                else
                    return Ok(result);                
            }
            catch (Exception)
            {
                return GetErrorServerCode();
            }
        }

        [Route("show/book")]
        [HttpPost]
        public async Task<IActionResult> BookShow([FromBody]GeneralDto info)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);
            try
            {
                var result = await _showService.GetResponseBook(info.InformationProvider, info.GeneralShowInfo);
                if (result == null)
                    return NotFound();
                else
                    return Ok(result);
            }
            catch (Exception)
            {
                return GetErrorServerCode();
            }
        }

        private IActionResult GetErrorServerCode()
        {
            return StatusCode(StatusCodes.Status500InternalServerError);
        }

    }
}