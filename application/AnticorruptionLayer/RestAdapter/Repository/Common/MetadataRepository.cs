using DomainModel.Dto;
using Microsoft.EntityFrameworkCore;
using RestAdapter.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RestAdapter.Repository.Common
{
    public class MetadataRepository : EfRepository<RestAdapterConfiguration>, IMetadataRepository
    {


        public MetadataRepository(MetadataPGContext dbContext):base(dbContext)
        {
 
        }


        public async Task<RestAdapterConfiguration> GetMetadata(InformationProvider informationProvider, IMetadataRepository.RequestType requestType)
        {
            var metadataCofig = await _dbContext.RestAdapterConfiguration.FirstOrDefaultAsync(x =>
                            (x.IdProveedor == informationProvider.IdProvider || x.NombreProveedor == informationProvider.ProviderName) &&
                            x.RequestType == requestType.ToString() && x.TipoProveedor == informationProvider.ProviderType);
            return metadataCofig;
        }
    }
}
