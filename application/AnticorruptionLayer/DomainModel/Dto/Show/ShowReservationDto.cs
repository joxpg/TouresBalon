using System;

namespace DomainModel.Dto.Show
{
    public class ShowReservationDto
    {
        /// <summary>
        /// Fecha de evento
        /// </summary>
        public DateTime ShowDate { get; set; }

        /// <summary>
        /// Identificación del asistente al evento
        /// </summary>
        public string AttendeeIdentification { get; set; }
    }
}
