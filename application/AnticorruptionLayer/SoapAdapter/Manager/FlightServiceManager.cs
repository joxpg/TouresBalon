
using SoapAdapter.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using SoapAdapter.Services.FlightServices;
using DomainModel.Dto;
using DomainModel.Dto.Transport;

namespace SoapAdapter.Manager
{
    public class FlightServiceManager : IFlightServiceManager
    {
        /// <summary>
        /// TODO: Tratara de llevar esto para ser manejado por inyección de dependencias y que cada servicio se registre inmediatamente se cree
        /// Temporalmente se tendrá aquí
        /// </summary>
        private readonly Dictionary<string, IFlightServices> _servicesRegistry;

        public FlightServiceManager()
        {
            _servicesRegistry = new Dictionary<string, IFlightServices>();
            _servicesRegistry.Add("Avianca", new AviancaServices());//Esto debería ser responsabilidad del servicio subscribirse 
            _servicesRegistry.Add("AmericanAirlines", new AmericanAirlinesServices());//Esto debería ser responsabilidad del servicio subscribirse 
        }

        public async Task<GeneralFlightDto> GetResponseBook(InformationProv informationProv, GeneralFlightDto concreteDto)
        {
            if (_servicesRegistry.TryGetValue(informationProv.NombreProveedor, out IFlightServices service))
            {
                var book = await service.BookFlight(concreteDto.BookFlight);
                concreteDto.BookFlightResponse = book;
                return concreteDto;
            }

            return new GeneralFlightDto();
        }

        public async Task<GeneralFlightDto> GetResponseSearch(InformationProv informationProv, GeneralFlightDto concreteDto)
        {
            if (_servicesRegistry.TryGetValue(informationProv.NombreProveedor, out IFlightServices service))
            {
                var trip = await service.SearchFlight(concreteDto.SearchFlight);
                concreteDto.Trip = trip;
                return concreteDto;
            }

            return new GeneralFlightDto();
        }

        private IFlightServices GetServiceFromProvider(string provider)
        {
            IFlightServices flightServices;

            switch (provider)
            {
                case "Avianca":
                    flightServices = new AviancaServices();
                    break;
                case "AmericanAirlines":
                    flightServices = new AmericanAirlinesServices();
                    break;
                default:
                    flightServices = new AmericanAirlinesServices();
                    break;
            }

            return flightServices;
        }

    }


}
