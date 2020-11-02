using DomainModel.Dto;
using DomainModel.Dto.Hotel;
using RestAdapter.Interfaces;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace RestAdapter.Services
{
    public class HotelService : IHotelServices
    {
        public Task<int> Book(RoomReservationDto roomReservation, InformationProvider informationProvider)
        {
            throw new NotImplementedException();
        }

        public Task<List<RoomDto>> Search(SearchRoomDto searchRoom, InformationProvider informationProvider)
        {
            throw new NotImplementedException();
        }
    }
}
