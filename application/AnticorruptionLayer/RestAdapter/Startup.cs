using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Amazon.DynamoDBv2;
using Amazon.Runtime;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.HttpsPolicy;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.Extensions.Logging;
using RestAdapter.Interfaces;
using RestAdapter.Models;
using RestAdapter.Repository.Common;
using RestAdapter.Services;
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


            services.AddDbContext<MetadataPGContext>(options =>
            {
                options.UseNpgsql(Configuration.GetConnectionString("DbPGConnection"));
            });

            services.AddScoped<ITransportServices, TransportService>();
            services.AddScoped<IHotelServices, HotelService>();
            services.AddScoped<IShowServices, ShowService>();


            services.AddScoped<IFieldMapper, FieldAdapter>();
            services.AddScoped<IConsumer, ConsumerService>();
            services.AddScoped<IMetadataRepository, MetadataRepository>();

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
