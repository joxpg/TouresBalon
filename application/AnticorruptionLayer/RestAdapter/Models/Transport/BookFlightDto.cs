
namespace RestAdapter.Models.Transport
{
    /// <summary>
    /// Objeto para manejar la reserva del vuelo
    /// </summary>
    public class BookFlightDto:FlightDto
    {
        public MetaType? PassengerIdentification { get; set; }
        public MetaType? PassengerName { get; set; }
        public MetaType? Flight { get; set; }
    }
}
