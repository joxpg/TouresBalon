using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace RestAdapter.Models.Show
{
    public class ShowDto
    {
        public string Price { get; set; }

        public string? Row { get; set; }

        public string? SeatNumber { get; set; }

        public string? Location { get; set; }
    }
}
