using AutoMapper;
using DomainModel.Dto;
using DomainModel.Dto.Transport;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using RestAdapter.Interfaces;
using RestAdapter.Models;
using RestAdapter.Repository.Common;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace RestAdapter.Services
{
    public class TransportService : ITransportServices
    {

        private readonly IConsumer _consumer;
        private readonly IFieldMapper _fieldMapper;
        private readonly IMetadataRepository _repository;

        public TransportService(
            IConsumer consumer, 
            IFieldMapper fieldMapper, 
            IMetadataRepository repository)
        {
            _consumer = consumer;
            _fieldMapper = fieldMapper;
            _repository = repository;
        }

        public async Task<bool> Book(BookFlightDto bookFlight, InformationProvider informationProvider)
        {
            //Obtiene la información de metadata y la lleva al modelo de metadata
            var metadataCofig = await _repository.GetMetadata(informationProvider, IMetadataRepository.RequestType.book);

            if(metadataCofig.Body!=null || metadataCofig.Body!="")
            {
                var metadataFieldTransport = Newtonsoft.Json.JsonConvert.DeserializeObject<RestAdapter.Models.Transport.BookFlightDto>(metadataCofig.Body);
                //Convierte el objeto request en Json
                var body = Newtonsoft.Json.JsonConvert.SerializeObject(bookFlight, Newtonsoft.Json.Formatting.Indented);
                metadataCofig.Body = _fieldMapper.GetBodyMapped(metadataFieldTransport, body);
            }

            metadataCofig.Url = _fieldMapper.GetUrlMapped(bookFlight, metadataCofig.Url);
            var providerConsumer = new ProviderConsumerService(_consumer);
            var result = await providerConsumer.Request(metadataCofig);

            if (!result.IsSuccessStatusCode)
            {
                return false;
            }

            return true;
        }

        public async Task<List<TripDto>> Search(SearchFlightDto searchFlight, DomainModel.Dto.InformationProvider informationProvider)
        {

            //Obtiene la información de metadata y la lleva al modelo de metadata
            var metadataCofig = await _repository.GetMetadata(informationProvider, IMetadataRepository.RequestType.search);

            if (metadataCofig.Body != null || metadataCofig.Body != "")
            {
                var metadataFieldTransport = Newtonsoft.Json.JsonConvert.DeserializeObject<RestAdapter.Models.Transport.SearchFlightDto>(metadataCofig.Body);
                //Convierte el objeto request en Json
                var body = Newtonsoft.Json.JsonConvert.SerializeObject(searchFlight, Newtonsoft.Json.Formatting.Indented);
                metadataCofig.Body = _fieldMapper.GetBodyMapped(metadataFieldTransport, body);
            }

            metadataCofig.Url = _fieldMapper.GetUrlMapped(searchFlight, metadataCofig.Url);
            var providerConsumer = new ProviderConsumerService(_consumer);
            var result = await providerConsumer.Request(metadataCofig);
            if (!result.IsSuccessStatusCode)//Respondió de manera negativa
            {
                return new List<TripDto>();
            }

            var trips = new List<TripDto>();
            var response = await  result.Content.ReadAsStringAsync();
            //var jt = JToken.Parse(response);

            var  fly = _fieldMapper.GetObjetMapped<List<FlightDto>>(response, metadataCofig.Response);
            if (fly != null)
                trips.Add(new TripDto { Flights = fly });

            return trips;
        }
    }
}
