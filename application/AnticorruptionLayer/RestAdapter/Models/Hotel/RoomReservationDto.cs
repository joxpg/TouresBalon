using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RestAdapter.Models.Hotel
{
    public class RoomReservationDto
    {
        public string GuestName { get; set; }
        public string RoomNumber { get; set; }
        public string Checkout { get; set; }
        public string Checkin { get; set; }
        public string Hotel { get; set; }
    }
}
