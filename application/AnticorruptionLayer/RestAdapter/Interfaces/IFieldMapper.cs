using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RestAdapter.Interfaces
{
    public interface IFieldMapper
    {
        public string GetBodyMapped(object metadataInfo, string body);

        public string GetUrlMapped(object metadataInfo, string url);
    }
}
