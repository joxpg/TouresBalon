
using ApplicationCore.SoapAdapter.Interfaces;
using DomainModel.Dto;
using DomainModel.Dto.Show;
using Infrastructure.Data.Interfaces;
using System.Threading.Tasks;

namespace ApplicationCore.SoapAdapter.Manager.Show
{
    public class ShowServiceManager : MetadataManager<IShowServices>, IShowServiceManager
    {
        public ShowServiceManager(IMetadataSoapRepository soapRepository) : base(soapRepository)
        {
        }

        public async Task<GeneralShowDto> GetResponseBook(InformationProvider informationProv, GeneralShowDto concreteDto)
        {
            var service = await GetService(informationProv);
            if (service != null)
            {
                var book = await service.BookShow(concreteDto.ShowReservation);
                concreteDto.ShowReservationResponse = book;
                return concreteDto;
            }

            return null;
        }

        public async Task<GeneralShowDto> GetResponseSearch(InformationProvider informationProv, GeneralShowDto concreteDto)
        {
            var service = await GetService(informationProv);
            if (service != null)
            {
                var shows = await service.SearchShow(concreteDto.SearchShow);
                concreteDto.Shows = shows;
                return concreteDto;
            }

            return null;
        }
    }
}
