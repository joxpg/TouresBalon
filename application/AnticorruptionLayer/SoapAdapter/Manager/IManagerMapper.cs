using DomainModel.Dto;
using System.Threading.Tasks;

namespace SoapAdapter.Manager
{
    public interface IManagerMapper<T>
    {
        public Task<T> GetResponseSearch(InformationProvider informationProv, T concreteDto);
        public Task<T> GetResponseBook(InformationProvider informationProv, T concreteDto);
    }
}
