
using DomainModel.Dto;
using Infrastructure.Data.Common;
using Infrastructure.Models;
using System.Threading.Tasks;

namespace Infrastructure.Data.Interfaces
{
    public interface IMetadataRestRepository : IAsyncRepository<RestAdapterConfiguration>
    {
        public enum RequestType { search, book };
        public Task<RestAdapterConfiguration> GetMetadata(InformationProvider informationProvider, RequestType requestType);
    }
}
