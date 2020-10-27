using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DomainModel.Dto.Show
{
    public class ShowDto
    {
        public double Price { get; set; }

        /// <summary>
        /// Fila Si tiene
        /// </summary>
        public string? Row { get; set; }

        /// <summary>
        /// Número de silla si tiene
        /// </summary>
        public string? SeatNumber { get; set; }

        /// <summary>
        /// Locación, categoria si tiene
        /// </summary>
        public string? Location { get; set; }
    }
}
