

using System.Collections.Generic;

namespace ApplicationCore.RestAdapter.Entity.Transport
{
    /// <summary>
    /// Objeto para Manejar el Viaje
    /// </summary>
    public class TripDto
    {
        public  List<FlightDto>? Flights { get; set; }
    }
}
