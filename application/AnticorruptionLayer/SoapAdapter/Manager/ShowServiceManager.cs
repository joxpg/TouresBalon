
using DomainModel.Dto;
using DomainModel.Dto.Show;
using SoapAdapter.Interfaces;
using SoapAdapter.Models;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace SoapAdapter.Manager
{
    public class ShowServiceManager : MetadataManager<IShowServices>, IShowServiceManager
    {


        public ShowServiceManager(MetadataContext context) : base(context)
        {

        }


        public async Task<GeneralShowDto> GetResponseBook(InformationProvider informationProv, GeneralShowDto concreteDto)
        {
            var service = GetService(informationProv);
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
            var service = GetService(informationProv);
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
