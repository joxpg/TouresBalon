using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using DomainModel.Dto;
using DomainModel.Dto.Hotel;

namespace ApplicationCore.RestAdapter.Interfaces
{
    public interface IHotelServices
    {
        public Task<List<RoomDto>> Search(SearchRoomDto searchRoom, InformationProvider informationProvider);
        public Task<int> Book(RoomReservationDto roomReservation, InformationProvider informationProvider);

    }
}
