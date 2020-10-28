

using DomainModel.Dto.Hotel;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace SoapAdapter.Interfaces
{
    public interface IHotelServices
    {
        public Task<List<RoomDto>> SearchRoom(SearchRoomDto searchRoom);
        public Task<int> RoomReservation(RoomReservationDto roomReservation);

    }
}
