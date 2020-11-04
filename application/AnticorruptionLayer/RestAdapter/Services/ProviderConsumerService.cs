using RestAdapter.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RestAdapter.Services
{
    public class ProviderConsumerService
    {
        private readonly IConsumer consumerService;

        public ProviderConsumerService(IConsumer consumerService)
        {
            this.consumerService = consumerService;
        }

        public async Task<object> Request(RestAdapter.Models.RestAdapterConfiguration connection)
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
