using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DomainModel.Dto.Hotel
{
    public class RoomDto
    {
        public Nullable<int> RoomNumber { get; set; }
        public HotelDto Hotel { get; set; }
        public double Price { get; set; }
        public string TypeofRoom { get; set; }
    }
}
