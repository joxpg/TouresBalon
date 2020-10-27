

using System.Collections.Generic;

namespace DomainModel.Dto.Transport
{
    /// <summary>
    /// Objeto para Manejar el Viaje
    /// </summary>
    public class TripDto
    {
        public  List<FlightDto>? Flights { get; set; }
    }
}
