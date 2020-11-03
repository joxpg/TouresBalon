using DomainModel.Dto;
using RouterProviderService.Controllers.Dto;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RouterProviderService.Interface
{
    public interface IMapperService
    {
        public Task<GeneralDto> GetGeneralDtoAsync(GeneralRequest request);
    }
}
