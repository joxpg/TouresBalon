
using System.Threading.Tasks;

namespace RestAdapter.Interfaces
{
    public interface IConsumer
    {
        public Task<object> GetAsync(string endpoint, object body=null); 
        public Task<object> PostAsync(string endpoint, object body); 
        public Task<object> PutAsync(string endpoint, object body); 
        public Task<object> DeleteAsync(string endpoint); 
    }
}
