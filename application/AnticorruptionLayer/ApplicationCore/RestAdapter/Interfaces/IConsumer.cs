
using System.Net.Http;
using System.Threading.Tasks;

namespace ApplicationCore.RestAdapter.Interfaces
{
    public interface IConsumer
    {
        public Task<HttpResponseMessage> GetAsync(string endpoint, string? headers, object body=null); 
        public Task<HttpResponseMessage> PostAsync(string endpoint, string? headers, object body); 
        public Task<HttpResponseMessage> PutAsync(string endpoint, string? headers, object body); 
        public Task<HttpResponseMessage> DeleteAsync(string endpoint, string? headers); 
    }
}
