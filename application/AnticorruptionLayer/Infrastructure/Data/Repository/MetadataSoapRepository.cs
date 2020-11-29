using DomainModel.Dto;
using Infrastructure.Data.Common;
using Infrastructure.Data.Interfaces;
using Infrastructure.Models;
using Microsoft.EntityFrameworkCore;
using System.Threading.Tasks;

namespace Infrastructure.Data.Repository
{
    public class MetadataSoapRepository : EfRepository<SoapAdapterConfiguration>, IMetadataSoapRepository
    {
        public MetadataSoapRepository(MetadataPGContext dbContext) : base(dbContext)
        {
        }

        public async Task<SoapAdapterConfiguration> GetMetadata(InformationProvider informationProvider)
        {
            var metadataCofig = await _dbContext.SoapAdapterConfigurations.FirstOrDefaultAsync(x =>
            (x.NombreProveedor == informationProvider.ProviderName || x.IdProveedor == informationProvider.IdProvider) &&
            x.TipoProveedor == informationProvider.ProviderType);

            return metadataCofig;
        }    
    }
}
