
using DomainModel.Dto;
using DomainModel.Dto.Show;
using SoapAdapter.Interfaces;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace SoapAdapter.Manager
{
    public class ShowServiceManager : IShowServiceManager
    {
        private readonly Dictionary<string, IShowServices> _servicesRegistry;
        public ShowServiceManager()
        {
            _servicesRegistry = new Dictionary<string, IShowServices>();
        }

        public async Task<GeneralShowDto> GetResponseBook(InformationProv informationProv, GeneralShowDto concreteDto)
        {
            if (_servicesRegistry.TryGetValue(informationProv.NombreProveedor, out IShowServices service))
            {
                var book = await service.BookShow(concreteDto.ShowReservation);
                concreteDto.ShowReservationResponse = book;
                return concreteDto;
            }

            return null;
        }

        public async Task<GeneralShowDto> GetResponseSearch(InformationProv informationProv, GeneralShowDto concreteDto)
        {
            if (_servicesRegistry.TryGetValue(informationProv.NombreProveedor, out IShowServices service))
            {
                var shows = await service.SearchShow(concreteDto.SearchShow);
                concreteDto.Shows = shows;
                return concreteDto;
            }

            return null;
        }
    }
}
