using DomainModel.Dto.Transport;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace ApplicationCore.SoapAdapter.Interfaces
{
    public interface IFlightServices
    {
        /// <summary>
        /// Busca dentro del servicio que ofrece un proveedor, ofertas de vuelos y las devuelve en un listado de viajes
        /// </summary>
        /// <param name="searchFlight"></param>
        /// <returns></returns>
        public Task<List<TripDto>> SearchFlight(SearchFlightDto searchFlight);

        /// <summary>
        /// Reserva el vuelo en el servicio del proveedor y devuelve si fue o no reservado.
        /// </summary>
        /// <param name="bookFligth"></param>
        /// <returns></returns>
        public Task<bool> BookFlight(BookFlightDto bookFligth);
    }
}
