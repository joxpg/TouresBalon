using ApplicationCore.Exceptions;
using ApplicationCore.RouterProvider.Interfaces;
using DomainModel.Dto;
using Infrastructure.Data.Interfaces;
using Microsoft.Extensions.Logging;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace ApplicationCore.RouterProvider.Service
{
    public class RouterService : IRouterService
    {

        private readonly IMetadataRouterRepository _routerRepository;
        private readonly ILogger<RouterService> _logger;

        public RouterService(IMetadataRouterRepository routerRepository, ILogger<RouterService> logger)
        {
            _routerRepository = routerRepository;
            _logger = logger;
        }

        public async Task<object> Book(GeneralDto generalDto)
        {
            return await GetResponseProviderService(generalDto, "book");
        }

        public async Task<object> Search(GeneralDto generalDto)
        {
            return await GetResponseProviderService(generalDto, "search");
        }

        private async Task<object> GetResponseProviderService(GeneralDto generalDto, string type)
        {
            var router = await _routerRepository.GetMetadata(generalDto.InformationProvider);
            if (router != null)
            {
                var url = $"{router.Endpoint}/{router.TipoAdaptador}/Provider/{router.TipoProveedor}/{type}";
                _logger.LogInformation($"URL_SENDED {url}");

                var result = await Post(url, generalDto);
                if (result.IsSuccessStatusCode)
                    return await result.Content.ReadAsStringAsync();
                else if (result.StatusCode == System.Net.HttpStatusCode.ServiceUnavailable)
                    throw new ProviderNotResponseException(await result.Content.ReadAsStringAsync());
                else
                {
                    _logger.LogInformation(await result.Content.ReadAsStringAsync(), "ERROR_CONSUMING_SERVICE ", new object[] { url });
                    throw new System.Exception($"StatusCode {result.StatusCode.ToString()}, error {result.Content.ReadAsStringAsync()}");
                }
            }
            return null;
        }


        public async Task<HttpResponseMessage> Post(string endpoint, object body)
        {
            var httpClient = new HttpClient();
            var httpContent = GetHttpContent(body);
            var messageResponse = await httpClient.PostAsync(endpoint, httpContent);
            return messageResponse;
        }


        private HttpContent GetHttpContent(object value)
        {
            var jsonDatosPDF = Newtonsoft.Json.JsonConvert.SerializeObject(value, Newtonsoft.Json.Formatting.Indented);
            _logger.LogInformation($"BODY_SENDED {jsonDatosPDF}");
            HttpContent httpContent = new StringContent(jsonDatosPDF, Encoding.UTF8, "application/json");
            return httpContent;
        }
    }
}
