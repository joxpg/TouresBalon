using System;

namespace DomainModel.Dto.Show
{
	public class SearchShowDto
    {

		/// <summary>
		/// País donde se disfruta el evento
		/// </summary>
		public string Country { get; set; }

		/// <summary>
		/// Ciudad donde se disfruta el evento
		/// </summary>
		public string City { get; set; }

		/// <summary>
		/// Fecha del evento o espectáculo
		/// </summary>
		public DateTime ShowDate { get; set; }

		/// <summary>
		/// Cantidad de boletos de ingreso que se cotizan
		/// </summary>
		public int Quantity{ get; set; }

		/// <summary>
		/// Dto para manejar la categoria, la silla, la ubicación, la locación que es otorgada cuando se compra un boleto a evento
		/// </summary>
		public string Location { get; set; }
	}
}
