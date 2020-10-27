using DomainModel.Dto;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RouterProviderService.Interface
{
    public interface IRouterService
    {
        public Task<object> Search(GeneralDto generalDto);
        public Task<object> Book(GeneralDto generalDto);
    }
}
