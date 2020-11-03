using DomainModel.Dto;
using DomainModel.Dto.Hotel;
using RestAdapter.Interfaces;
using RestAdapter.Repository.Common;
using System;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace RestAdapter.Services
{
    public class HotelService : IHotelServices
    {
        private readonly IConsumer _consumer;
        private readonly IFieldMapper _fieldMapper;
        private readonly IMetadataRepository _repository;

        public HotelService(
            IConsumer consumer,
            IFieldMapper fieldMapper,
            IMetadataRepository repository)
        {
            _consumer = consumer;
            _fieldMapper = fieldMapper;
            _repository = repository;
        }

        public async Task<int> Book(RoomReservationDto roomReservation, InformationProvider informationProvider)
        {
            //Obtiene la información de metadata y la lleva al modelo de metadata
            var metadataCofig = await _repository.GetMetadata(informationProvider, IMetadataRepository.RequestType.search);
            if (metadataCofig.Body != null || metadataCofig.Body != "")
            {
                var metadataFieldTransport = Newtonsoft.Json.JsonConvert.DeserializeObject<RestAdapter.Models.Hotel.RoomReservationDto>(metadataCofig.Body);
                //Convierte el objeto request en Json
                var body = Newtonsoft.Json.JsonConvert.SerializeObject(roomReservation, Newtonsoft.Json.Formatting.Indented);
                metadataCofig.Body = _fieldMapper.GetBodyMapped(metadataFieldTransport, body);
            }
            metadataCofig.Url = _fieldMapper.GetUrlMapped(roomReservation, metadataCofig.Url);
            var providerConsumer = new ProviderConsumerService(_consumer);
            var result = providerConsumer.Request(metadataCofig);

            return 0;
        }

        public async Task<List<RoomDto>> Search(SearchRoomDto searchRoom, InformationProvider informationProvider)
        {
            //Obtiene la información de metadata y la lleva al modelo de metadata
            var metadataCofig = await _repository.GetMetadata(informationProvider, IMetadataRepository.RequestType.search);
            if (metadataCofig.Body != null || metadataCofig.Body != "")
            {
                var metadataFieldTransport = Newtonsoft.Json.JsonConvert.DeserializeObject<RestAdapter.Models.Hotel.SearchRoomDto>(metadataCofig.Body);
                //Convierte el objeto request en Json
                var body = Newtonsoft.Json.JsonConvert.SerializeObject(searchRoom, Newtonsoft.Json.Formatting.Indented);
                metadataCofig.Body = _fieldMapper.GetBodyMapped(metadataFieldTransport, body);
            }
            metadataCofig.Url = _fieldMapper.GetUrlMapped(searchRoom, metadataCofig.Url);
            var providerConsumer = new ProviderConsumerService(_consumer);
            var result = await providerConsumer.Request(metadataCofig);

            return new List<RoomDto>();
        }
    }
}
