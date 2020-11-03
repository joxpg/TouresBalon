using RestAdapter.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RestAdapter.Repository.Common
{
    public interface IMetadataRepository : IAsyncRepository<RestAdapterConfiguration>
    {
        public enum RequestType { search, book };
        public Task<RestAdapterConfiguration> GetMetadata(DomainModel.Dto.InformationProvider informationProvider, RequestType requestType);
    }
}
