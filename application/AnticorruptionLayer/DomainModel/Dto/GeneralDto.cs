using DomainModel.Dto.Hotel;
using DomainModel.Dto.Show;
using DomainModel.Dto.Transport;

namespace DomainModel.Dto
{
    public class GeneralDto
    {
        public InformationProvider InformationProvider { get; set; }
        public GeneralFlightDto? GeneralFlightInfo { get; set; }
        public GeneralHotelDto? GeneralHotelInfo { get; set; }
        public GeneralShowDto? GeneralShowInfo { get; set; }
    }
}
