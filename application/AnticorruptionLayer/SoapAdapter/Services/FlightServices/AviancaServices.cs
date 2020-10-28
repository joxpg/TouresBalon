using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using DomainModel.Dto.Transport;
using SoapAdapter.Interfaces;
using ServiceReferenceAvianca;

namespace SoapAdapter.Services.FlightServices
{
    public class AviancaServices : IFlightServices
    {
        private readonly  ServicioAviancaVuelos _flightService;
        public AviancaServices()
        {
            _flightService = new ServicioAviancaVuelosClient();
        }

        public async Task<List<TripDto>> SearchFlight(SearchFlightDto searchFlight)
        {
            var search = new consultarVueloRequest(searchFlight.DepartingCity,searchFlight.ArrivingCity, searchFlight.DepartingDate, searchFlight.Cabin);
            var trip = await _flightService.consultarVueloAsync(search);    
            return GetTrips(trip.result);
        }

        public async Task<bool> BookFlight(BookFlightDto bookFligth)
        { 
            var reservarVuelo = new reservarVueloRequest(GetFlight(bookFligth.Flight), bookFligth.PassengerName, bookFligth.PassengerIdentification);
            var respuesta = await _flightService.reservarVueloAsync(reservarVuelo);
            return respuesta.result;
        }

        private List<TripDto> GetTrips(Vuelo[] vuelos)
        {
            var flightList = new List<FlightDto>();
            foreach (var flight in vuelos)
            {
                flightList.Add(GetFlightDto(flight));
            }

            var trips = new List<TripDto>();
            var trip = new TripDto() { Flights = flightList };
            trips.Add(trip);
            return trips;
        }

        /// <summary>
        /// Realiza la conversión de Vuelo a FlightDto
        /// </summary>
        /// <param name="flight"></param>
        /// <returns></returns>
        private FlightDto GetFlightDto(Vuelo flight)
        {
            return new FlightDto()
            {
                DepartingCity = flight.ciudadOrigen,
                ArrivingCity = flight.ciudadDestino,
                DepartingDate = flight.fechaSalida,
                ArrivingDate = flight.fechaLlegada,
                Cabin = flight.clase,
                FlightNumber = flight.vuelo,
                Price = flight.precio
            };
        }

        /// <summary>
        /// Realiza la conversión de FlightDto a Vuelo
        /// </summary>
        /// <param name="fl"></param>
        /// <returns></returns>
        private Vuelo GetFlight(FlightDto fl)
        {
            return new Vuelo()
            {
                precio = Convert.ToInt64(fl.Price),
                vuelo = fl.FlightNumber,
                ciudadOrigen = fl.DepartingCity,
                ciudadDestino = fl.ArrivingCity,
                clase = fl.Cabin,
                fechaSalida = fl.DepartingDate,
                fechaLlegada = fl.ArrivingDate
            };              
        }         

    }
}
