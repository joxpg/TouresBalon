
using SoapAdapter.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using SoapAdapter.Services.FlightServices;
using DomainModel.Dto;
using DomainModel.Dto.Transport;
using SoapAdapter.Models;

namespace SoapAdapter.Manager
{
    public class FlightServiceManager : MetadataManager<IFlightServices> ,IFlightServiceManager
    {
        public FlightServiceManager(MetadataContext context):base(context)
        {  
        }

        /// <summary>
        /// Reserva un transporte
        /// </summary>
        /// <param name="informationProv"></param>
        /// <param name="concreteDto"></param>
        /// <returns></returns>
        public async Task<GeneralFlightDto> GetResponseBook(InformationProvider informationProv, GeneralFlightDto concreteDto)
        {
            var service = GetService(informationProv);
            if (service!=null)
            {
                var book = await service.BookFlight(concreteDto.BookFlight);
                concreteDto.BookFlightResponse = book;
                return concreteDto;
            }

            return new GeneralFlightDto();
        }

        /// <summary>
        /// Realiza una busqueda de transporte
        /// </summary>
        /// <param name="informationProv"></param>
        /// <param name="concreteDto"></param>
        /// <returns></returns>
        public async Task<GeneralFlightDto> GetResponseSearch(InformationProvider informationProv, GeneralFlightDto concreteDto)
        {
            var service = GetService(informationProv);
            if (service != null)
            {
                var trip = await service.SearchFlight(concreteDto.SearchFlight);
                concreteDto.Trip = trip;
                return concreteDto;
            }

            return new GeneralFlightDto();
        }

    }


}
