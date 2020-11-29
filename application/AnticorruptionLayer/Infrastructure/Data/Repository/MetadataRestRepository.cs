using DomainModel.Dto;
using Infrastructure.Data.Common;
using Infrastructure.Data.Interfaces;
using Infrastructure.Models;
using Microsoft.EntityFrameworkCore;
using System.Threading.Tasks;

namespace Infrastructure.Data.Repository
{
    public class MetadataRestRepository : EfRepository<RestAdapterConfiguration>, IMetadataRestRepository
    {
        public MetadataRestRepository(MetadataPGContext dbContext) : base(dbContext)
        {
        }

        public async Task<RestAdapterConfiguration> GetMetadata(InformationProvider informationProvider, IMetadataRestRepository.RequestType requestType)
        {
            var metadataCofig = await _dbContext.RestAdapterConfigurations.FirstOrDefaultAsync(x =>
                            (x.IdProveedor == informationProvider.IdProvider || x.NombreProveedor == informationProvider.ProviderName) &&
                            x.RequestType == requestType.ToString() && x.TipoProveedor == informationProvider.ProviderType);
            return metadataCofig;
        }
    }
}
