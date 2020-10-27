using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DomainModel.Dto.Show
{
    public class GeneralShowDto
    {
        public SearchShowDto? SearchShow { get; set; }
        public List<ShowDto>? Shows{ get; set; }
        public ShowReservationDto? ShowReservation { get; set; }
        public bool? ShowReservationResponse { get; set; }

    }
}
