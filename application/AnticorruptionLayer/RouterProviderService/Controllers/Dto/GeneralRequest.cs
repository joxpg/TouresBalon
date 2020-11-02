using DomainModel.Dto;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace RouterProviderService.Controllers.Dto
{
    public class GeneralRequest
    {
       [Required]
       public RequestToProvider Request { get; set; }

        [Required]
        public InformationProvider InformationProvider { get; set; }
    }
}
