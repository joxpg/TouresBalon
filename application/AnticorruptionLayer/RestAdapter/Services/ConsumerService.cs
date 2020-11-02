using Microsoft.AspNetCore.Http;
using RestAdapter.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace RestAdapter.Services
{
    public class ConsumerService : IConsumer
    {
        private HttpContent GetHttpContent(object value)
        {
            var jsonDatosPDF = Newtonsoft.Json.JsonConvert.SerializeObject(value, Newtonsoft.Json.Formatting.Indented);
            HttpContent httpContent = new StringContent(jsonDatosPDF, Encoding.UTF8, "application/json");
            return httpContent;
        }

        public async Task<object> GetAsync(string endpoint, object body=null)
        {            
            var httpClient = new HttpClient();
            var messageResponse = await httpClient.GetAsync(endpoint).ConfigureAwait(false);
            return messageResponse;
        }

        public async Task<object> PostAsync(string endpoint, object body)
        {
            var httpClient = new HttpClient();
            var httpContent = GetHttpContent(body);
            var messageResponse = await httpClient.PostAsync(endpoint, httpContent).ConfigureAwait(false);
            return messageResponse;
        }

        public async Task<object> PutAsync(string endpoint, object? body)
        {
            var httpClient = new HttpClient();
            var httpContent = GetHttpContent(body);
            var messageResponse = await httpClient.PutAsync(endpoint, httpContent).ConfigureAwait(false);
            return messageResponse;
        }

        public async Task<object> DeleteAsync(string endpoint)
        {
            var httpClient = new HttpClient();
            var messageResponse = await httpClient.DeleteAsync(endpoint).ConfigureAwait(false);
            return messageResponse;
        }
    }
}
