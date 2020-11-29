using ApplicationCore.RestAdapter.Interfaces;
using Infrastructure.Models;
using System;
using System.Net.Http;
using System.Threading.Tasks;

namespace ApplicationCore.RestAdapter.Services.CommonServices
{
    public class ProviderConsumerService
    {
        private readonly IConsumer consumerService;

        public ProviderConsumerService(IConsumer consumerService)
        {
            this.consumerService = consumerService;
        }

        public async Task<HttpResponseMessage> Request(RestAdapterConfiguration connection)
        {

            switch (connection.Method.ToLower())
            {

                case "get":
                  return await  consumerService.GetAsync(connection.Url, connection.Headers);
                case "post":
                    return await consumerService.PostAsync(connection.Url, connection.Headers, connection.Body);
                case "put":
                    return await consumerService.PutAsync(connection.Url, connection.Headers, connection.Body);
                case "delete":
                    return await consumerService.DeleteAsync(connection.Url, connection.Headers);  
                default:
                    return null;
            }


        }



    }
}
