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
                return GetService(serviceFullName.Servicio, serviceFullName.TipoProveedor);

            return default;
        }

        /// <summary>
        /// Recibe el nombre del servicio y genera una instancia concreta
        /// </summary>
        /// <param name="service">nombre del servicio p.e. SoapAdapter.Services.FlightServices.AviancaServices </param>
        /// <returns></returns>
        private T GetService(string serviceFullName, string tipoProveedor)
        {
            switch (tipoProveedor.ToLower())
            {
                case "hotel":
                    tipoProveedor = "HotelServices";
                    break;
                case "transport":
                    tipoProveedor = "FlightServices";
                    break;
                case "show":
                    tipoProveedor = "ShowServices";
                    break;
                default:
                    break;
            }

            serviceFullName = $"ApplicationCore.SoapAdapter.Services.{tipoProveedor}.{serviceFullName}";
            Type serviceType = Type.GetType(serviceFullName);
            return (T)serviceType.Assembly.CreateInstance(serviceType.FullName);
        }
    }
}
