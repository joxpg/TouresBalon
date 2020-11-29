using System.Collections.Generic;

namespace ApplicationCore.RestAdapter.Entity.Transport
{
    public class GeneralFligthDto
    {
        public SearchFlightDto? SearchFlight { get; set; }
        public BookFlightDto? BookFlight { get; set; }
        public FlightDto? Flight { get; set; }
        public List<TripDto>? Trip { get; set; }
        public bool? BookFlightResponse { get; set; }

    }
}
