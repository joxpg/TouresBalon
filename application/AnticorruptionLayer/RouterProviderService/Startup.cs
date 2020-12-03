using ApplicationCore.RouterProvider.Interfaces;
using ApplicationCore.RouterProvider.Service;
using Infrastructure.Data.Interfaces;
using Infrastructure.Data.Repository;
using Infrastructure.Models;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using RouterProviderService.Filters.ExceptionHandling;
using Steeltoe.Discovery.Client;


namespace RouterProviderService
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        // This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            services.AddControllers();
            services.AddDiscoveryClient(Configuration);

            services.AddMvc(options =>
            {
                options.Filters.Add(typeof(ExceptionFilter));
            });

            services.AddDbContext<MetadataPGContext>(options =>
            {
                options.UseNpgsql(Configuration.GetConnectionString("DbPGConnection"));
            });

            services.AddScoped<IRouterService, RouterService>();
            services.AddScoped<IMapperService, MapperService>();

            services.AddScoped<IMetadataRouterRepository, MetadataRouterRepository>();
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseHttpsRedirection();

            app.UseRouting();

            app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });


            app.UseDiscoveryClient();
        }
    }
}
