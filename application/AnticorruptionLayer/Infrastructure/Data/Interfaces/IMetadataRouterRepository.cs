using DomainModel.Dto;
using Infrastructure.Data.Common;
using Infrastructure.Models;
using System.Threading.Tasks;

namespace Infrastructure.Data.Interfaces
{

    public interface IMetadataRouterRepository : IAsyncRepository<RouterProvider>
    {
        public enum RequestType { search, book };
        public Task<RouterProvider> GetMetadata(InformationProvider informationProvider);
    }
}
