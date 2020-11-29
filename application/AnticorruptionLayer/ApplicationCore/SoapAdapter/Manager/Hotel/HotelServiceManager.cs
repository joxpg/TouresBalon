using ApplicationCore.SoapAdapter.Interfaces;
using DomainModel.Dto;
using DomainModel.Dto.Hotel;
using Infrastructure.Data.Interfaces;
using System.Threading.Tasks;

namespace ApplicationCore.SoapAdapter.Manager.Hotel
{
    public class HotelServiceManager : MetadataManager<IHotelServices>, IHotelServiceManager
    {
        public HotelServiceManager(IMetadataSoapRepository soapRepository) :
            base(soapRepository)
        {
        }

        public async Task<GeneralHotelDto> GetResponseBook(InformationProvider informationProv, GeneralHotelDto concreteDto)
        {
            var service = await GetService(informationProv);
            if (service != null)
            {
                var book = await service.RoomReservation(concreteDto.RoomReservation);
                concreteDto.RoomReservationResponse = book;
                return concreteDto;
            }

            return new GeneralHotelDto();
        }

        public async Task<GeneralHotelDto> GetResponseSearch(InformationProvider informationProv, GeneralHotelDto concreteDto)
        {
            var service = await GetService(informationProv);
            if (service != null)
            {
                var room = await service.SearchRoom(concreteDto.SearchRoom);
                concreteDto.Rooms = room;
                return concreteDto;
            }

            return new GeneralHotelDto();
        }
    }
}
