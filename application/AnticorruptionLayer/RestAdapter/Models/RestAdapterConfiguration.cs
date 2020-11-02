using System.ComponentModel.DataAnnotations.Schema;

namespace RestAdapter.Models
{
    [Table("RestAdapterConfiguration", Schema = "RestAdapterService")]
    public class RestAdapterConfiguration
    {
        public int Id { get; set; }

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
        public string Url { get; set; }
        public string Headers { get; set; }
        public string Method { get; set; }
        public string Body { get; set; }

        /// <summary>
        /// Tipo de petición (search | book)
        /// </summary>
        public string RequestType { get; set; }



    }
}
