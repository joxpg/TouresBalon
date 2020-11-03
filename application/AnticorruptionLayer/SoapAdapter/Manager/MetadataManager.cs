using DomainModel.Dto;
using SoapAdapter.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SoapAdapter.Manager
{
    public class MetadataManager<T>
    {
        private readonly MetadataContext _context;
        public MetadataManager(MetadataContext context)
        {
            _context = context;
        }

        protected T GetService(InformationProvider informationProv)
        {
            var serviceFullName = _context.SoapAdapterConfiguration.FirstOrDefault(x =>
            (x.NombreProveedor == informationProv.ProviderName || x.IdProveedor == informationProv.IdProvider) &&
            x.TipoProveedor == informationProv.ProviderType).Servicio;

            if (serviceFullName != null)
            {
                return GetService(serviceFullName);
            }

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
