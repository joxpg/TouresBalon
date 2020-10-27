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

        public async Task<object> Get(string endpoint, object body)
        {            
            var httpClient = new HttpClient();
            var messageResponse = await httpClient.GetAsync(endpoint).ConfigureAwait(false);
            return messageResponse;
        }

        public async Task<object> Post(string endpoint, object body)
        {
            var httpClient = new HttpClient();
            var httpContent = GetHttpContent(body);
            var messageResponse = await httpClient.PostAsync(endpoint, httpContent).ConfigureAwait(false);
            return messageResponse;
        }

        public async Task<object> Put(string endpoint, object body)
        {
            var httpClient = new HttpClient();
            var httpContent = GetHttpContent(body);
            var messageResponse = await httpClient.PutAsync(endpoint, httpContent).ConfigureAwait(false);
            return messageResponse;
        }

        public async Task<object> Delete(string endpoint)
        {
            var httpClient = new HttpClient();
            var messageResponse = await httpClient.DeleteAsync(endpoint).ConfigureAwait(false);
            return messageResponse;
        }
    }
}
