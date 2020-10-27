using ServiceReferenceAAAFligth;
using DomainModel.Dto.Transport;
using SoapAdapter.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SoapAdapter.Services.FlightServices
{
    public class AmericanAirlinesServices : IFlightServices
    {
        private readonly AAFlightsService _flightService;
        public AmericanAirlinesServices()
        {
            _flightService = new AAFlightsServiceClient();
        }

        public async Task<List<TripDto>> SearchFlight(SearchFlightDto searchFlight)
        {
            var search = new searchFlightRequest(searchFlight.DepartingCity,searchFlight.ArrivingCity,searchFlight.DepartingDate,searchFlight.Cabin,searchFlight.PromotionCode);
            var trip = await _flightService.searchFlightAsync(search);
            return GetTrips(trip.result);
        }

        public async Task<bool> BookFlight(BookFlightDto bookFligth)
        {
            var reservarVuelo = new bookFligthRequest(GetVuelo(bookFligth.Flight), bookFligth.PassengerName);
            var respuesta = await _flightService.bookFligthAsync(reservarVuelo);
            return respuesta.result;
        }

        private List<TripDto> GetTrips(Trip[] trips)
        {
            var tripdtos = new List<TripDto>();
            foreach (var trip in trips)
            {
                var flightList = new List<FlightDto>();
                foreach (var flight in trip.flights)
                {
                    flightList.Add(GetFlightDto(flight));
                }

                var tripDto = new TripDto() { Flights = flightList };
                tripdtos.Add(tripDto);
            }

            return tripdtos;
        }


        /// <summary>
        /// Realiza la conversión de Vuelo a FlightDto
        /// </summary>
        /// <param name="vuelo"></param>
        /// <returns></returns>
        private FlightDto GetFlightDto(Flight flight)
        {
            return new FlightDto()
            {
                DepartingCity = flight.departinCity,
                ArrivingCity = flight.arrivingCity,
                DepartingDate = flight.departinDate,
                ArrivingDate = flight.arrivingDate,
                Cabin = flight.cabin,
                Meals = flight.meals,
                Price = flight.price,
                TripNumber = flight.number,
                FlightNumber = flight.number==null? "" : flight.number.ToString()
            };
        }


        /// <summary>
        /// Devuelve el vuelo en formato util para el proveedor
        /// </summary>
        /// <param name="fl"></param>
        /// <returns></returns>
        private Flight GetVuelo(FlightDto fl)
        {
            return new Flight()
            {
                price = Convert.ToInt64(fl.Price),
                meals = fl.Meals,
                departinCity = fl.DepartingCity,
                arrivingCity = fl.ArrivingCity,
                cabin = fl.Cabin,
                departinDate = fl.DepartingDate,
                arrivingDate = fl.ArrivingDate
            };
        }
    }
}
