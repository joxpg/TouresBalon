using System;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using DomainModel.Dto;
using ApplicationCore.RestAdapter.Interfaces;

namespace RestAdapter.Controllers
{
    [Route("api/restadapter/[controller]")]
    [ApiController]
    public class ProviderController : ControllerBase
    {
        private readonly ITransportServices transportServices;
        private readonly IHotelServices hotelServices;
        private readonly IShowServices showServices;

        public ProviderController(
            ITransportServices transportServices,
            IHotelServices hotelServices,
            IShowServices showServices
            )
        {
            this.transportServices = transportServices;
            this.hotelServices = hotelServices;
            this.showServices = showServices;
        }

        [Route("transport/search")]
        [HttpPost]
        public async Task<IActionResult> SearchFlight([FromBody]GeneralDto info)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);
            try
            {
                var result = await transportServices.Search(info.GeneralFlightInfo.SearchFlight, info.InformationProvider);
                if (result == null)
                    return NoContent();
                else
                {
                    return Ok(result);
                }
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
                var result = await transportServices.Book(info.GeneralFlightInfo.BookFlight, info.InformationProvider);
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
                var result = await hotelServices.Search(info.GeneralHotelInfo.SearchRoom, info.InformationProvider);
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
                var result = await hotelServices.Book(info.GeneralHotelInfo.RoomReservation, info.InformationProvider);
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
                var result = await showServices.Search(info.GeneralShowInfo.SearchShow, info.InformationProvider);
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
                var result = await showServices.Book(info.GeneralShowInfo.ShowReservation, info.InformationProvider);
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