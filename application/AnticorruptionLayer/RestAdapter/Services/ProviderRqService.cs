using RestAdapter.Interfaces;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RestAdapter.Services
{
    public class ProviderRqService
    {
        private readonly IConsumer consumerService;

        public ProviderRqService(IConsumer consumerService)
        {
            this.consumerService = consumerService;
        }
        public async Task Request(RestAdapter.Models.MetadataConnection connection, object body)
        {

            switch (connection.Method.ToLower())
            {

                case "get":
                  var xx = await  consumerService.GetAsync(connection.Url);
                    break;
                case "post":
                  var yy = await  consumerService.PostAsync(connection.Url, connection.Body);
                    break;
                case "put":
                    break;
                case "delete":
                    break;
  
                default:
                    break;
            }


        }



    }
}
