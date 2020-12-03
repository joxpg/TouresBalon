namespace ApplicationCore.RestAdapter.Entity.Hotel
{
    public class SearchRoomDto
	{
		public MetaType City { get; set; }
		public MetaType Country { get; set; }
		public MetaType CheckIn { get; set; }
		public MetaType CheckOut { get; set; }
		public MetaType Rooms { get; set; }
		public MetaType TypeofRoom { get; set; }
    }
}
