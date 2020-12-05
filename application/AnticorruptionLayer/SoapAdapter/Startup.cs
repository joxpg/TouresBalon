using ApplicationCore.SoapAdapter.Manager.Hotel;
using ApplicationCore.SoapAdapter.Manager.Show;
using ApplicationCore.SoapAdapter.Manager.Transport;
using Infrastructure.Data.Interfaces;
using Infrastructure.Data.Repository;
using Infrastructure.Filters.ExceptionHandling;
using Infrastructure.Models;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Steeltoe.Discovery.Client;

namespace SoapAdapter
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
            services.AddDiscoveryClient(Configuration);
            services.AddControllers();

            services.AddMvc(options =>
            {
                options.Filters.Add(typeof(ExceptionFilter));
            });

            services.AddDbContext<MetadataPGContext>(options =>
            {
                options.UseNpgsql(Configuration.GetConnectionString("DbPGConnection"));
            });

            services.AddScoped<IFlightServiceManager, FlightServiceManager>();
            services.AddScoped<IHotelServiceManager, HotelServiceManager>();
            services.AddScoped<IShowServiceManager, ShowServiceManager>();


            services.AddScoped<IMetadataSoapRepository, MetadataSoapRepository>();
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
