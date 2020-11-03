using DomainModel.Dto;
using System.Threading.Tasks;

namespace RouterProviderService.Interface
{
    public interface IRouterService
    {
        public Task<object> Search(GeneralDto generalDto);
        public Task<object> Book(GeneralDto generalDto);
    }
}
