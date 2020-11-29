using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ApplicationCore.RestAdapter.Entity.Hotel
{
    public class RoomDto
    {
        public MetaType RoomNumber { get; set; }
        public HotelDto Hotel { get; set; }
        public MetaType Price { get; set; }
        public MetaType TypeofRoom { get; set; }
    }
}
