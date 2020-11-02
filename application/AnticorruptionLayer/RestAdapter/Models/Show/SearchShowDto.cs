using System;

namespace RestAdapter.Models.Show
{
	public class SearchShowDto
    {
		public string Country { get; set; }
		public string City { get; set; }
		public string ShowDate { get; set; }
		public int Quantity{ get; set; }
		public string Location { get; set; }
	}
}
