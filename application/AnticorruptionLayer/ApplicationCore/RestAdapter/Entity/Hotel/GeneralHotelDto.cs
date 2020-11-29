using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ApplicationCore.RestAdapter.Entity.Hotel
{
    public class GeneralHotelDto
    {
        public HotelDto? Hotel { get; set; }

        public List<RoomDto>? Rooms { get; set; }

        public RoomReservationDto? RoomReservation { get; set; }

        public SearchRoomDto? SearchRoom { get; set; }

        public int? RoomReservationResponse { get; set; }
    }
}
