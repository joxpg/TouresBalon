using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using DomainModel.Dto.Show;
using DomainModel.Dto;

namespace RestAdapter.Interfaces
{
    public interface IShowServices
    {
        public Task<List<ShowDto>> Search(SearchShowDto searchShow, InformationProvider informationProvider);

        public Task<bool> Book(ShowReservationDto showReservation, InformationProvider informationProvider);

    }
}
