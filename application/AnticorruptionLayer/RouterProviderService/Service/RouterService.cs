using DomainModel.Dto;
using RouterProviderService.Interface;
using RouterProviderService.Models;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace RouterProviderService.Service
{
    public class RouterService : IRouterService
    {
        List<RouterProvider> routers;

        public RouterService()
        {
            routers = new List<RouterProvider>();
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
            var router = routers.FirstOrDefault(x => x.IdProveedor == generalDto.InformationProvider.IdProveedor && x.TipoProveedor == generalDto.InformationProvider.TipoProveedor);
            if (router != null)
            {
                var url = $"{router.Endpoint}/{router.TipoAdaptador}/{router.TipoProveedor}/{type}";

                var result = await Post(url, generalDto);

                if (result.IsSuccessStatusCode)
                {
                    return await result.Content.ReadAsStringAsync();
                }

                return null;
            }
            return null;
        }
          

        public async Task<HttpResponseMessage> Post(string endpoint, object body)
        {
            var httpClient = new HttpClient();
            var httpContent = GetHttpContent(body);
            var messageResponse = await httpClient.PostAsync(endpoint, httpContent).ConfigureAwait(false);
            return messageResponse;
        }


        private HttpContent GetHttpContent(object value)
        {
            var jsonDatosPDF = Newtonsoft.Json.JsonConvert.SerializeObject(value, Newtonsoft.Json.Formatting.Indented);
            HttpContent httpContent = new StringContent(jsonDatosPDF, Encoding.UTF8, "application/json");
            return httpContent;
        }
    }
}
