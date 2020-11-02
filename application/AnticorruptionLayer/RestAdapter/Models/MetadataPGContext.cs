using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RestAdapter.Models
{
    public class MetadataPGContext : DbContext
    {
        public MetadataPGContext(DbContextOptions<MetadataPGContext> options)
             : base(options)
        {
        }

        public virtual DbSet<RestAdapterConfiguration> RestAdapterConfiguration { get; set; }

    }
}
