using DomainModel.Dto;
using DomainModel.Dto.Hotel;
using SoapAdapter.Interfaces;
using SoapAdapter.Models;
using SoapAdapter.Services.HotelServices;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SoapAdapter.Manager
{
    public class HotelServiceManager : MetadataManager<IHotelServices>,IHotelServiceManager
    {
        public HotelServiceManager(MetadataContext context) :
            base(context)
        {
        }

        public async Task<GeneralHotelDto> GetResponseBook(InformationProvider informationProv, GeneralHotelDto concreteDto)
        {
            var service = GetService(informationProv);
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
            var service = GetService(informationProv);
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
