using DomainModel.Dto;
using DomainModel.Dto.Hotel;
using SoapAdapter.Interfaces;
using SoapAdapter.Services.HotelServices;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SoapAdapter.Manager
{
    public class HotelServiceManager : IHotelServiceManager
    {
        private readonly Dictionary<string, IHotelServices> _servicesRegistry;

        public HotelServiceManager()
        {
            _servicesRegistry = new Dictionary<string, IHotelServices>();
            _servicesRegistry.Add("Avianca", new HiltonRoomServices());//Esto debería ser responsabilidad del servicio subscribirse  
        }

        public async Task<GeneralHotelDto> GetResponseBook(InformationProv informationProv, GeneralHotelDto concreteDto)
        {
            if (_servicesRegistry.TryGetValue(informationProv.NombreProveedor, out IHotelServices service))
            {
                var book = await service.RoomReservation(concreteDto.RoomReservation);
                concreteDto.RoomReservationResponse = book;
                return concreteDto;
            }

            return new GeneralHotelDto();
        } 

        public async Task<GeneralHotelDto> GetResponseSearch(InformationProv informationProv, GeneralHotelDto concreteDto)
        {
            if (_servicesRegistry.TryGetValue(informationProv.NombreProveedor, out IHotelServices service))
            {
                var room = await service.SearchRoom(concreteDto.SearchRoom);
                concreteDto.Rooms = room;
                return concreteDto;
            }

            return new GeneralHotelDto();
        }
    }
}
