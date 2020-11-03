using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RestAdapter.Models
{
    public class MetadataConnection
    {
        public string Url { get; set; }
        public string Headers { get; set; }
        public string Method { get; set; }
        public string Body { get; set; }

    }
}
