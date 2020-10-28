using System;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using DomainModel.Dto;

namespace RestAdapter.Controllers
{
    [Route("api/restadapter/[controller]")]
    [ApiController]
    public class ProviderController : ControllerBase
    {

        public ProviderController()
        {

        }

        [Route("transport")]
        [HttpGet]
        public async Task<IActionResult> SearchFl([FromBody]GeneralDto info)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);
            try
            {
                return NotFound();
            }
            catch (Exception)
            {
                return GetErrorServerCode();
            }
        }

        [Route("transport/book")]
        [HttpPost]
        public async Task<IActionResult> BookFl([FromBody]GeneralDto info)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);
            try
            {
                return NotFound();
            }
            catch (Exception)
            {
                return GetErrorServerCode();
            }
        }

        [Route("hotel")]
        [HttpGet]
        public async Task<IActionResult> SearchRoom([FromBody]GeneralDto info)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);
            try
            {
                return NotFound();
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
                return NotFound();
            }
            catch (Exception)
            {
                return GetErrorServerCode();
            }
        }


        [Route("show")]
        [HttpGet]
        public async Task<IActionResult> SearchShow([FromBody]GeneralDto info)
        {
            if (!ModelState.IsValid)
                return BadRequest(ModelState);
            try
            {
                return NotFound();
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
                return NotFound();
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