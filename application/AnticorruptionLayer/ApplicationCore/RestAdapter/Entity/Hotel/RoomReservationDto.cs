using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ApplicationCore.RestAdapter.Entity.Hotel
{
    public class RoomReservationDto
    {
        public MetaType GuestName { get; set; }
        public MetaType RoomNumber { get; set; }
        public MetaType Checkout { get; set; }
        public MetaType Checkin { get; set; }
        public MetaType Hotel { get; set; }
    }
}
