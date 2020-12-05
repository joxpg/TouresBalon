using ApplicationCore.RestAdapter.Interfaces;
using ApplicationCore.RestAdapter.Services.CommonServices;
using ApplicationCore.RestAdapter.Services.DomainServices;
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

namespace RestAdapter
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

            services.AddScoped<ITransportServices, TransportService>();
            services.AddScoped<IHotelServices, HotelService>();
            services.AddScoped<IShowServices, ShowService>();

            services.AddScoped<IFieldMapper, FieldAdapter>();
            services.AddScoped<IConsumer, ConsumerService>();

            services.AddScoped<IMetadataRestRepository, MetadataRestRepository>();

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
