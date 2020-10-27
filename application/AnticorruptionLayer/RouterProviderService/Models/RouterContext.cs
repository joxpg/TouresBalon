using Microsoft.EntityFrameworkCore;

namespace RouterProviderService.Models
{
    public class RouterContext : DbContext
    {
        public RouterContext()
        {

        }

        public RouterContext(DbContextOptions<RouterContext> options)
            : base(options)
        {

        }

        public virtual DbSet<RouterProvider> RouterProvider { get; set; }
    }
}
