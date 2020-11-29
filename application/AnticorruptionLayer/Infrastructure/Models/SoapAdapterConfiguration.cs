using Infrastructure.Data.Common;
using System.ComponentModel.DataAnnotations.Schema;

namespace Infrastructure.Models
{
    [Table("SoapAdapterConfiguration", Schema = "SoapAdapterService")]
    public class SoapAdapterConfiguration : BaseEntity, IAggregateRoot
    {

        /// <summary>
        /// Identificador de proveedor
        /// </summary>
        public string IdProveedor { get; set; }


        /// <summary>
        /// Nombre del proveedor que lo identifíca dentro del dominio
        /// </summary>
        public string NombreProveedor { get; set; }

        /// <summary>
        /// Tipo de proveedor (transport | hotel | show)
        /// </summary>
        public string TipoProveedor { get; set; }

        /// <summary>
        /// Nombre de la interfaz que debe implementar
        /// </summary>
        public string Interfaz { get; set; }

        /// <summary>
        /// Nombre completo del servicio
        /// </summary>
        public string Servicio { get; set; }


    }
}
