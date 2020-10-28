using DomainModel.Dto;
using System.Threading.Tasks;

namespace SoapAdapter.Manager
{
    public interface IManagerMapper<T>
    {
        public Task<T> GetResponseSearch(InformationProv informationProv, T concreteDto);
        public Task<T> GetResponseBook(InformationProv informationProv, T concreteDto);
    }
}
