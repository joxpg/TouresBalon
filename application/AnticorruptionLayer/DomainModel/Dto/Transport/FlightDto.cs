using System;


namespace DomainModel.Dto.Transport
{
    /// <summary>
    /// Objeto para manejar la información del vuelo
    /// </summary>
    public class FlightDto
    {
        /// <summary>
        /// Clase o cabina del vuelo
        /// </summary>
        public string Cabin { get; set; }

        /// <summary>
        /// Fecha de destino vuello
        /// </summary>
        public DateTime? ArrivingDate { get; set; }

        /// <summary>
        /// precio del vuelo
        /// </summary>
        public double? Price { get; set; }

        /// <summary>
        /// ciudaad de llegada
        /// </summary>
        public string ArrivingCity { get; set; }

        /// <summary>
        /// Comida
        /// </summary>
        public string Meals { get; set; }

        /// <summary>
        /// Fecha de salida
        /// </summary>
        public DateTime? DepartingDate { get; set; }

        /// <summary>
        /// Ciudad de origen
        /// </summary>
        public string DepartingCity { get; set; }

        /// <summary>
        /// Número de viaje (AA)
        /// </summary>
        public int TripNumber { get; set; }

        /// <summary>
        /// Podría ser código de vuelo (AV)
        /// </summary>
        public string FlightNumber { get; set; }

    }
}
