using ApplicationCore.Exceptions;
using ApplicationCore.RestAdapter.Interfaces;
using ApplicationCore.RestAdapter.Services.CommonServices;
using DomainModel.Dto;
using DomainModel.Dto.Hotel;
using Infrastructure.Data.Interfaces;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace ApplicationCore.RestAdapter.Services.DomainServices
{
    public class HotelService : IHotelServices
    {
        private readonly IConsumer _consumer;
        private readonly IFieldMapper _fieldMapper;
        private readonly IMetadataRestRepository _repository;

        public HotelService(
            IConsumer consumer,
            IFieldMapper fieldMapper,
            IMetadataRestRepository repository)
        {
            _consumer = consumer;
            _fieldMapper = fieldMapper;
            _repository = repository;
        }

        public async Task<int> Book(RoomReservationDto roomReservation, InformationProvider informationProvider)
        {
            //Obtiene la información de metadata y la lleva al modelo de metadata
            var metadataCofig = await _repository.GetMetadata(informationProvider, IMetadataRestRepository.RequestType.book);
            if (metadataCofig.Body != null || metadataCofig.Body != "")
            {
                var metadataFieldTransport = Newtonsoft.Json.JsonConvert.DeserializeObject<Entity.Hotel.RoomReservationDto>(metadataCofig.Body);
                //Convierte el objeto request en Json
                var body = Newtonsoft.Json.JsonConvert.SerializeObject(roomReservation, Newtonsoft.Json.Formatting.Indented);
                metadataCofig.Body = _fieldMapper.GetBodyMapped(metadataFieldTransport, body);
            }
            metadataCofig.Url = _fieldMapper.GetUrlMapped(roomReservation, metadataCofig.Url);
            var providerConsumer = new ProviderConsumerService(_consumer);
            var result = await providerConsumer.Request(metadataCofig);

            if (!result.IsSuccessStatusCode)
                throw new ProviderNotResponseException();

            var response = await result.Content.ReadAsStringAsync();
           if(int.TryParse(response,out int responseNumber))            
                return responseNumber;           

            return 0;
        }

        public async Task<List<RoomDto>> Search(SearchRoomDto searchRoom, InformationProvider informationProvider)
        {
            //Obtiene la información de metadata y la lleva al modelo de metadata
            var metadataCofig = await _repository.GetMetadata(informationProvider, IMetadataRestRepository.RequestType.search);
            if (metadataCofig.Body != null || metadataCofig.Body != "")
            {
                var metadataFieldTransport = Newtonsoft.Json.JsonConvert.DeserializeObject< Entity.Hotel.SearchRoomDto>(metadataCofig.Body);
                //Convierte el objeto request en Json
                var body = Newtonsoft.Json.JsonConvert.SerializeObject(searchRoom, Newtonsoft.Json.Formatting.Indented);
                metadataCofig.Body = _fieldMapper.GetBodyMapped(metadataFieldTransport, body);
            }
            metadataCofig.Url = _fieldMapper.GetUrlMapped(searchRoom, metadataCofig.Url);
            var providerConsumer = new ProviderConsumerService(_consumer);
            var result = await providerConsumer.Request(metadataCofig);
            if (!result.IsSuccessStatusCode)
                throw new ProviderNotResponseException();

            var response = await result.Content.ReadAsStringAsync();
            var rooms = _fieldMapper.GetObjetMapped<List<RoomDto>>(response, metadataCofig.Response);
            return rooms;
        }
    }
}
