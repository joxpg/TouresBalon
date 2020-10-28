using System;

namespace DomainModel.Dto.Hotel
{
    public class SearchRoomDto
	{
		public string City { get; set; }
		public string Country { get; set; }
		public DateTime CheckIn { get; set; }
		public DateTime CheckOut { get; set; }
		public int Rooms { get; set; }
		public string TypeofRoom { get; set; }
    }
}
