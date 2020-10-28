using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DomainModel.Dto.Transport
{
    public class GeneralFlightDto
    {
        public BookFlightDto? BookFlight { get; set; }
        public FlightDto? Flight { get; set; }
        public SearchFlightDto? SearchFlight { get; set; }
        public List<TripDto>? Trip { get; set; }
        public bool? BookFlightResponse { get; set; }

    }
}
