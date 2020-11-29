using DomainModel.Dto;
using Infrastructure.Data.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ApplicationCore.SoapAdapter.Manager
{
    public class MetadataManager<T>
    {
        private readonly IMetadataSoapRepository _soapRepository;

        public MetadataManager(IMetadataSoapRepository soapRepository)
        {
            _soapRepository = soapRepository;
        }

        protected async Task<T> GetService(InformationProvider informationProv)
        {
            var serviceFullName = await _soapRepository.GetMetadata(informationProv);
            if (serviceFullName != null)            
                return GetService(serviceFullName.Servicio);

            return default;
        }

        /// <summary>
        /// Recibe el nombre del servicio y genera una instancia concreta
        /// </summary>
        /// <param name="service">nombre del servicio p.e. SoapAdapter.Services.FlightServices.AviancaServices </param>
        /// <returns></returns>
        private T GetService(string serviceFullName)
        {
            Type serviceType = Type.GetType(serviceFullName);
            return (T)serviceType.Assembly.CreateInstance(serviceType.FullName);
        }
    }
}
