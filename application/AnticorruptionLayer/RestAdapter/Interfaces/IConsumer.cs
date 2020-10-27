using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RestAdapter.Interfaces
{
    public interface IConsumer
    {
        public Task<object> Get(string endpoint, object body); 
        public Task<object> Post(string endpoint, object body); 
        public Task<object> Put(string endpoint, object body); 
        public Task<object> Delete(string endpoint); 
    }
}
