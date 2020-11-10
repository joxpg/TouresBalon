using DomainModel.Dto;
using DomainModel.Dto.Show;
using RestAdapter.Interfaces;
using RestAdapter.Repository.Common;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RestAdapter.Services
{
    public class ShowService : IShowServices
    {
        private readonly IConsumer _consumer;
        private readonly IFieldMapper _fieldMapper;
        private readonly IMetadataRepository _repository;

        public ShowService(IConsumer consumer,
            IFieldMapper fieldMapper,
            IMetadataRepository repository)
        {
            _consumer = consumer;
            _fieldMapper = fieldMapper;
            _repository = repository;
        }

        public async Task<bool> Book(ShowReservationDto showReservation, InformationProvider informationProvider)
        {
            var metadataCofig = await _repository.GetMetadata(informationProvider, IMetadataRepository.RequestType.book);
            //Obtiene la información de metadata y la lleva al modelo de metadata

            if (metadataCofig.Body != null || metadataCofig.Body != "")
            {
                var metadataFieldTransport = Newtonsoft.Json.JsonConvert.DeserializeObject<RestAdapter.Models.Show.ShowReservationDto>(metadataCofig.Body);
                //Convierte el objeto request en Json
                var body = Newtonsoft.Json.JsonConvert.SerializeObject(showReservation, Newtonsoft.Json.Formatting.Indented);
                metadataCofig.Body = _fieldMapper.GetBodyMapped(metadataFieldTransport, body);
            }
            metadataCofig.Url = _fieldMapper.GetUrlMapped(showReservation, metadataCofig.Url);
            var providerConsumer = new ProviderConsumerService(_consumer);
            var result = await providerConsumer.Request(metadataCofig);
            if (!result.IsSuccessStatusCode)
            {
                return false;
            }

            return true;


        }

        public async Task<List<ShowDto>> Search(SearchShowDto searchShow, InformationProvider informationProvider)
        {
            //Obtiene la información de metadata y la lleva al modelo de metadata
            var metadataCofig = await _repository.GetMetadata(informationProvider, IMetadataRepository.RequestType.search);
            if (metadataCofig.Body != null || metadataCofig.Body != "")
            {
                var metadataFieldTransport = Newtonsoft.Json.JsonConvert.DeserializeObject<RestAdapter.Models.Show.SearchShowDto>(metadataCofig.Body);
                //Convierte el objeto request en Json
                var body = Newtonsoft.Json.JsonConvert.SerializeObject(searchShow, Newtonsoft.Json.Formatting.Indented);
                metadataCofig.Body = _fieldMapper.GetBodyMapped(metadataFieldTransport, body);
            }

            metadataCofig.Url = _fieldMapper.GetUrlMapped(searchShow, metadataCofig.Url);
            var providerConsumer = new ProviderConsumerService(_consumer);
            var result = await providerConsumer.Request(metadataCofig);

            if (!result.IsSuccessStatusCode)
            {
                return new List<ShowDto>();
            }

            var response = await result.Content.ReadAsStringAsync();
            var show = _fieldMapper.GetObjetMapped<List<ShowDto>>(response, metadataCofig.Response);
            return show;
        }
    }
}
