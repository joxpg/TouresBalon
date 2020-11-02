using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using DomainModel.Dto.Transport;
using DomainModel.Dto;

namespace RestAdapter.Interfaces
{
    public interface ITransportServices
    {
        /// <summary>
        /// Busca dentro del servicio que ofrece un proveedor, ofertas de vuelos y las devuelve en un listado de viajes
        /// </summary>
        /// <param name="searchFlight"></param>
        /// <returns></returns>
        public Task<List<TripDto>> Search(SearchFlightDto searchFlight, InformationProvider informationProvider);

        /// <summary>
        /// Reserva el vuelo en el servicio del proveedor y devuelve si fue o no reservado.
        /// </summary>
        /// <param name="bookFligth"></param>
        /// <returns></returns>
        public Task<bool> Book(BookFlightDto bookFlight, InformationProvider informationProvider);
    }
}
