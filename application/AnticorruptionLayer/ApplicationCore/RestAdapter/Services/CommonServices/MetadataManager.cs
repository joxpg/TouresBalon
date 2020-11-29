using Microsoft.Extensions.Configuration;

namespace ApplicationCore.RestAdapter.Services.CommonServices
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
