using DomainModel.Dto;
using Infrastructure.Data.Common;
using Infrastructure.Models;
using System.Threading.Tasks;

namespace Infrastructure.Data.Interfaces
{
    public interface IMetadataSoapRepository : IAsyncRepository<SoapAdapterConfiguration>
    {
        public Task<SoapAdapterConfiguration> GetMetadata(InformationProvider informationProvider);
    }
}
