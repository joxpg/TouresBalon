using System;

namespace ApplicationCore.RouterProvider.Entity
{
    public class RequestToProvider
    {
        public string DepartingCity {get;set;}
        public string ArrivingCity {get;set;}
        public DateTime DepartingDate {get;set;}
        public string Cabin {get;set;}
        public string PromotionCode {get;set;}
        public string City {get;set;}
        public string Country {get;set;}
        public DateTime CheckIn {get;set;}
        public DateTime CheckOut {get;set;}
        public string Rooms {get;set;}
        public string TypeofRoom {get;set;}
        public DateTime ShowDate {get;set;}
        public int Quantity {get;set;}
        public string Location {get;set;}
        public string IdBusqueda {get;set;}
    }
}
