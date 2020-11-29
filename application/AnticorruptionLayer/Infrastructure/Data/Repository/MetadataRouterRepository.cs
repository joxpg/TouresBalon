using DomainModel.Dto;
using Infrastructure.Data.Common;
using Infrastructure.Data.Interfaces;
using Infrastructure.Models;
using Microsoft.EntityFrameworkCore;
using System.Threading.Tasks;

namespace Infrastructure.Data.Repository
{
    public class MetadataRouterRepository : EfRepository<RouterProvider>, IMetadataRouterRepository
    {
        public MetadataRouterRepository(MetadataPGContext dbContext) : base(dbContext)
        {
        }
        public async Task<RouterProvider> GetMetadata(InformationProvider informationProvider)
        {
            var metadataCofig = await _dbContext.RouterProviders.FirstOrDefaultAsync(x => 
            (x.IdProveedor == informationProvider.IdProvider || x.NombreProveedor == informationProvider.ProviderName) && x.TipoProveedor == informationProvider.ProviderType);

            return metadataCofig;
        }
    }
}
