using Microsoft.Extensions.Configuration;
using RestAdapter.Models;
using RestAdapter.Repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RestAdapter.Services
{
    /// <summary>
    /// Gestiona el ingrso y obtención de información de metadata para el consumo de los servicios rest
    /// </summary>
    public class MetadataManager
    {
        private readonly IConfiguration _configuration;
        public MetadataManager(IConfiguration configuration)
        {
            _configuration = configuration;
        }

        public MetadataManager()
        {

        }
    }
}
