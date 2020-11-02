

namespace RestAdapter.Models.Transport
{
    /// <summary>
    /// Objeto para manejar la información del vuelo
    /// </summary>
    public class FlightDto
    {
        public MetaType? Cabin { get; set; }
        public MetaType? DepartingDate { get; set; }
        public MetaType? ArrivingDate { get; set; }
        public MetaType? Price { get; set; }
        public MetaType? DepartingCity { get; set; }
        public MetaType? ArrivingCity { get; set; }
        public MetaType? Meals { get; set; }
        public MetaType? TripNumber { get; set; }
        public MetaType? FlightNumber { get; set; }

    }
}
