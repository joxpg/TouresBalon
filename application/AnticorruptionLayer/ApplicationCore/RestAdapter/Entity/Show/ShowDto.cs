using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ApplicationCore.RestAdapter.Entity.Show
{
    public class ShowDto
    {
        public MetaType Price { get; set; }

        public MetaType? Row { get; set; }

        public MetaType? SeatNumber { get; set; }

        public MetaType? Location { get; set; }
    }
}
