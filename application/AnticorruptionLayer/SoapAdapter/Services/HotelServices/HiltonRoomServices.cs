using AutoMapper;
using DomainModel.Dto.Hotel;
using ServiceReferenceHiltomRoom;
using ServiceReferenceHiltonBooking;
using SoapAdapter.Interfaces;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace SoapAdapter.Services.HotelServices
{
    public class HiltonRoomServices : IHotelServices
    {
        private readonly HiltonRoomService _roomService;
        private readonly HiltonBookingService _bookService;
        public HiltonRoomServices()
        {
            _bookService = new HiltonBookingServiceClient();
            _roomService = new HiltonRoomServiceClient();
        }


        public async Task<List<RoomDto>> SearchRoom(SearchRoomDto searchRoom)
        {
            var roomRequest = GetRequestRoom(searchRoom);
            var roomsFinded = await _roomService.initiateAsync(roomRequest);
            return GetRoomResponse(roomsFinded);
        }

        public async Task<int> RoomReservation(RoomReservationDto roomReservation)
        {
            var bookRoomRequest = GetBookRoomResponse(roomReservation);
            var booking = await _bookService.bookRoomAsync(bookRoomRequest);
            return booking.result;
        }

        private List<RoomDto> GetRoomResponse(initiateResponse responseRoom)
        {
            var result = responseRoom.HiltonRoomServiceProcessResponse.result;
            var confiMap = new MapperConfiguration(mc => mc.CreateMap<Room, RoomDto>());
            var mapper = new Mapper(confiMap);
            var roomsMappped = mapper.Map<Room[], List<RoomDto>>(result);
            return roomsMappped;
        }


        /// <summary>
        /// Devuelve un Room en modelo de domini desde el objeto room del proveedor
        /// </summary>
        /// <returns></returns>
        private RoomDto GetRoomDto(Room room)
        {
            return new RoomDto()
            {
                Hotel = GetHotelDto(room.Hotel),
                Price = room.Price,
                RoomNumber = int.TryParse(room.Number, out int roomNumber)? roomNumber : 0 ,
                TypeofRoom = room.Type
            };
        }

        private HotelDto GetHotelDto(Hotel hotel)
        {
            var confiMap = new MapperConfiguration(mc => mc.CreateMap<Hotel, HotelDto>());
            var mapper = new Mapper(confiMap);
            return mapper.Map<Hotel, HotelDto>(hotel);
        }

        /// <summary>
        /// Traduce el dto al objeto initiateRequest para consultar las habitaciones en el hotel en formato solicitado por proveedor
        /// </summary>
        /// <param name="searchRoom"></param>
        /// <returns></returns>
        private initiateRequest GetRequestRoom(SearchRoomDto searchRoom)
        {
            var roomService = new HiltonRoomServiceProcessRequest()
            {
                CheckIn = searchRoom.CheckIn,
                CheckOut = searchRoom.CheckOut,
                City = searchRoom.City,
                Country = searchRoom.Country,
                Rooms = searchRoom.Rooms.ToString(),
                Type = searchRoom.TypeofRoom
            };
       
            return new initiateRequest(roomService);
        }


        /// <summary>
        /// Traduce el dto al objeto de bookRoomRequest para solicitar la reserva
        /// </summary>
        /// <remarks>
        /// En esta transformación se utiliza la librería mappper porque el dto es igual al objeto del proveedor
        /// </remarks>
        /// <param name="roomReservation"></param>
        /// <returns></returns>
        private bookRoomRequest GetBookRoomResponse(RoomReservationDto roomReservation)
        {
            var confiMap = new MapperConfiguration(mc => mc.CreateMap<RoomReservationDto, RoomReservation>());
            var mapper = new Mapper(confiMap);
            var roomsMappped = mapper.Map<RoomReservationDto, RoomReservation>(roomReservation);
            return new bookRoomRequest(roomsMappped);
        }

    }
}
