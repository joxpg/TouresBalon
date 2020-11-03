using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SoapAdapter.Models
{
    public class MetadataContext:DbContext
    {
        public MetadataContext()
        {

        }

        public MetadataContext(DbContextOptions<MetadataContext> options)
            : base(options)
        {

        }

        public virtual DbSet<SoapAdapterConfiguration> SoapAdapterConfiguration { get; set; }
    }
}
