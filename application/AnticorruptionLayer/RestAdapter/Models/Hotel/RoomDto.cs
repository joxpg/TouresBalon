using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RestAdapter.Models.Hotel
{
    public class RoomDto
    {
        public string RoomNumber { get; set; }
        public HotelDto Hotel { get; set; }
        public string Price { get; set; }
        public string TypeofRoom { get; set; }
    }
}
