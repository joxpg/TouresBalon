using System;

namespace DomainModel.Dto.Transport
{
    public class SearchFlightDto
    {
        /// <summary>
        /// Ciudad de Origen
        /// </summary>
        public string DepartingCity { get; set; }

        /// <summary>
        /// Ciudad de Destino
        /// </summary>
        public string ArrivingCity { get; set; }

        /// <summary>
        /// Fecha de salida
        /// </summary>
        public Nullable<DateTime> DepartingDate { get; set; }


        /// <summary>
        /// Clase
        /// </summary>
        public string Cabin { get; set; }


        /// <summary>
        /// Código de promoción
        /// </summary>
        public string? PromotionCode { get; set; }

    }
}
