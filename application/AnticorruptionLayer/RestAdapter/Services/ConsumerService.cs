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
        private HttpContent GetHttpContent(object value, string? headers)
        {
            if (string.IsNullOrEmpty(headers))            
                headers = "application/json";

            //var jsonDatosPDF = Newtonsoft.Json.JsonConvert.SerializeObject(value, Newtonsoft.Json.Formatting.Indented);
            HttpContent httpContent = new StringContent(value.ToString(), Encoding.UTF8, headers);
            return httpContent;
        }

        public async Task<HttpResponseMessage> GetAsync(string endpoint, string? headers, object body=null)
        {            
            var httpClient = new HttpClient();
            var messageResponse = await httpClient.GetAsync(endpoint).ConfigureAwait(false);
            return messageResponse;
        }

        public async Task<HttpResponseMessage> PostAsync(string endpoint, string? headers, object body)
        {
            var httpClient = new HttpClient();
            var httpContent = GetHttpContent(body,headers);
            var messageResponse = await httpClient.PostAsync(endpoint, httpContent).ConfigureAwait(false);
            return messageResponse;
        }

        public async Task<HttpResponseMessage> PutAsync(string endpoint, string? headers, object? body)
        {
            var httpClient = new HttpClient();
            var httpContent = GetHttpContent(body, headers);
            var messageResponse = await httpClient.PutAsync(endpoint, httpContent).ConfigureAwait(false);
            return messageResponse;
        }

        public async Task<HttpResponseMessage> DeleteAsync(string endpoint, string? headers)
        {
            var httpClient = new HttpClient();
            var messageResponse = await httpClient.DeleteAsync(endpoint).ConfigureAwait(false);
            return messageResponse;
        }
    }
}
