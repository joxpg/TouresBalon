using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RestAdapter.Models.Transport
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
