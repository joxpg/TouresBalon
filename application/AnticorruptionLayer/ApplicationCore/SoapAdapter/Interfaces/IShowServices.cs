
using DomainModel.Dto.Show;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ApplicationCore.SoapAdapter.Interfaces
{
    public interface IShowServices
    {
        public  Task<List<ShowDto>>  SearchShow(SearchShowDto searchShow);

        public Task<bool> BookShow(ShowReservationDto showReservation);

    }
}
