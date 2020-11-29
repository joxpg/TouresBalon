using ApplicationCore.RouterProvider.Entity;
using DomainModel.Dto;
using System.Threading.Tasks;

namespace ApplicationCore.RouterProvider.Interfaces
{
    public interface IMapperService
    {
        public Task<GeneralDto> GetGeneralDtoAsync(GeneralRequest request);
    }
}
