using AutoMapper;
using DomainModel.Dto;
using DomainModel.Dto.Hotel;
using DomainModel.Dto.Show;
using DomainModel.Dto.Transport;
using RouterProviderService.Controllers.Dto;
using RouterProviderService.Interface;
using System.Threading.Tasks;

namespace RouterProviderService.Service
{
    public class MapperService: IMapperService
    {
        public async Task<GeneralDto> GetGeneralDtoAsync(GeneralRequest request)
        {
            return await Task.Run(()=> 
            new GeneralDto()
            {
                InformationProvider = request.InformationProvider,
                GeneralFlightInfo = GetGeneralFlightDto(request.Request),
                GeneralHotelInfo = GeneralHotelDto(request.Request),
                GeneralShowInfo = GeneralShowDto(request.Request)
            });
        }
        

        private  GeneralFlightDto GetGeneralFlightDto(RequestToProvider request)
        {            
            var generalFlight = new GeneralFlightDto()
            {
                BookFlight = MapperT<RequestToProvider, BookFlightDto>.GetTMap(request),
                Flight = MapperT<RequestToProvider, FlightDto>.GetTMap(request),
                SearchFlight = MapperT<RequestToProvider, SearchFlightDto>.GetTMap(request)
            };

            return generalFlight;        
        }

        private GeneralHotelDto GeneralHotelDto(RequestToProvider request)
        {
            return  new GeneralHotelDto()
            {
                SearchRoom = MapperT<RequestToProvider, SearchRoomDto>.GetTMap(request),
                Hotel = MapperT<RequestToProvider, HotelDto>.GetTMap(request),
                RoomReservation = MapperT<RequestToProvider, RoomReservationDto>.GetTMap(request)
            };
        }

        public GeneralShowDto GeneralShowDto(RequestToProvider request)
        {
            return new GeneralShowDto()
            {
                SearchShow = MapperT<RequestToProvider, SearchShowDto>.GetTMap(request),
                ShowReservation = MapperT<RequestToProvider, ShowReservationDto>.GetTMap(request),
                Show = MapperT<RequestToProvider, ShowDto>.GetTMap(request)
            };
        }
    }


    public static class MapperT<T, U>
    {
        public static U GetTMap(object value)
        {
            var confiMapw = new MapperConfiguration(mc => mc.CreateMap<T, U>());
            var mapperw = new Mapper(confiMapw);
            var tMappped = mapperw.Map<T, U>((T)value);
            return tMappped;
        }
    }
}
