using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DomainModel.Dto.Hotel
{
    public class RoomReservationDto
    {
        public string? GuestName { get; set; }
        public int RoomNumber { get; set; }
        public DateTime? Checkout { get; set; }
        public DateTime? Checkin { get; set; }
        public string Hotel { get; set; }
    }
}
