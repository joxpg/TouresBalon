
using ApplicationCore.SoapAdapter.Interfaces;
using DomainModel.Dto;
using DomainModel.Dto.Transport;
using Infrastructure.Data.Interfaces;
using System.Threading.Tasks;

namespace ApplicationCore.SoapAdapter.Manager.Transport
{
    public class FlightServiceManager : MetadataManager<IFlightServices> ,IFlightServiceManager
    {
        public FlightServiceManager(IMetadataSoapRepository soapRepository) :base(soapRepository)
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
            var service = await GetService(informationProv);
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
            var service = await GetService(informationProv);
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
