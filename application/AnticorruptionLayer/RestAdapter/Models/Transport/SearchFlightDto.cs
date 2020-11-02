using System;

namespace RestAdapter.Models.Transport
{
    public class SearchFlightDto
    {
        public MetaType? DepartingCity { get; set; }
        public MetaType? ArrivingCity { get; set; }
        public MetaType? DepartingDate { get; set; }
        public MetaType? Cabin { get; set; }
        public MetaType? PromotionCode { get; set; }
    }
}
