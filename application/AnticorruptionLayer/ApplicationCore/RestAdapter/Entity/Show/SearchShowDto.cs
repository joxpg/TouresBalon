using System;

namespace ApplicationCore.RestAdapter.Entity.Show
{
	public class SearchShowDto
    {
		public MetaType Country { get; set; }
		public MetaType City { get; set; }
		public MetaType ShowDate { get; set; }
		public MetaType Quantity { get; set; }
		public MetaType Location { get; set; }
	}
}
