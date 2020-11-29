using Microsoft.EntityFrameworkCore;

namespace Infrastructure.Models
{
    public class MetadataPGContext : DbContext
    {
        public MetadataPGContext(DbContextOptions<MetadataPGContext> options)
             : base(options)
        {
        }

        public virtual DbSet<RestAdapterConfiguration> RestAdapterConfigurations { get; set; }
        public virtual DbSet<SoapAdapterConfiguration> SoapAdapterConfigurations { get; set; }
        public virtual DbSet<RouterProvider> RouterProviders { get; set; }

    }
}
