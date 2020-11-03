using DomainModel.Dto;
using DomainModel.Dto.Show;
using RestAdapter.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RestAdapter.Services
{
    public class ShowService : IShowServices
    {
        public Task<bool> Book(ShowReservationDto showReservation, InformationProvider informationProvider)
        {
            throw new NotImplementedException();
        }

        public Task<List<ShowDto>> Search(SearchShowDto searchShow, InformationProvider informationProvider)
        {
            throw new NotImplementedException();
        }
    }
}
