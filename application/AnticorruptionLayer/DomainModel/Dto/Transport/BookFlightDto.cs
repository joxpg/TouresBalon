
namespace DomainModel.Dto.Transport
{
    /// <summary>
    /// Objeto para manejar la reserva del vuelo
    /// </summary>
    public class BookFlightDto
    {
        public int? PassengerIdentification { get; set; }
        public string? PassengerName { get; set; }
        public FlightDto? Flight { get; set; }
    }
}
