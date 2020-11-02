using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using RestAdapter.Models.Transport;
using RestAdapter.Models.Show;
using RestAdapter.Models.Hotel;

namespace RestAdapter.Models
{
    public class MetadataInformation
    {
        public GeneralFligthDto? Transport { get; set; }
        public GeneralShowDto? Show { get; set; }
        public GeneralHotelDto? Hotel { get; set; }
    }
}
