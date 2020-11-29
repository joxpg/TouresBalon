using DomainModel.Dto;
using System.ComponentModel.DataAnnotations;

namespace ApplicationCore.RouterProvider.Entity
{
    public class GeneralRequest
    {
       [Required]
       public RequestToProvider Request { get; set; }

        [Required]
        public InformationProvider InformationProvider { get; set; }
    }
}
