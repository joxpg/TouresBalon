using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using DomainModel.Dto;
using DomainModel.Dto.Transport;
using Microsoft.EntityFrameworkCore;
using RestAdapter.Interfaces;
using RestAdapter.Models;

namespace RestAdapter.Services
{
    public class TransportService : ITransportServices
    {
        private readonly MetadataPGContext _context;

        public TransportService(MetadataPGContext context)
        {
            _context = context;
        }

        public async Task<bool> Book(BookFlightDto bookFlight, DomainModel.Dto.InformationProvider informationProvider)
        {
            throw new NotImplementedException();
        }



        public async Task<List<TripDto>> Search(SearchFlightDto searchFlight, DomainModel.Dto.InformationProvider informationProvider)
        {
            var metadataconnection =  await _context.RestAdapterConfiguration.FirstOrDefaultAsync(x =>
            (x.IdProveedor == informationProvider.IdProvider || x.NombreProveedor == informationProvider.ProviderName) &&
            x.RequestType=="search" && x.TipoProveedor == informationProvider.ProviderType
            );

            if (metadataconnection==null)            
                throw new Exception("Metadata not finded");           

            //Obtiene la información de metadata y la lleva al modelo de metadata
            var metadataFieldTransport = Newtonsoft.Json.JsonConvert.DeserializeObject<RestAdapter.Models.Transport.SearchFlightDto>(metadataconnection.Body);
            //Convierte el objeto request en Json
            var body = Newtonsoft.Json.JsonConvert.SerializeObject(searchFlight, Newtonsoft.Json.Formatting.Indented);
            body = GetBodyMapped(metadataFieldTransport, body);

            return new List<TripDto>();
        }


        private string GetBodyMapped(object metadataInfo, string body)
        {
            Type metadataType = metadataInfo.GetType();//Obtiene el tipo de objeto de metadata 
            //Recorre cada una de las propiedades de la metadata y reemplaza el valor del bodyRequest
            foreach (var item in metadataType.GetProperties())
            {
                try
                {
                    var fieldValue = (MetaType)metadataType.GetProperty(item.Name).GetValue(metadataInfo, null);
                    body = body.Replace(item.Name, fieldValue.Field);
                }
                catch (Exception ex)
                {
                    throw ex;
                }
            }

            return body;
        }
    }
}
