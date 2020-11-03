

using System.ComponentModel.DataAnnotations.Schema;

namespace RouterProviderService.Models
{
    [Table("RouterProvider", Schema = "RouterService")]
    public partial class RouterProvider
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
        public string TipoProveedor{ get; set; }

        /// <summary>
        /// Tipo de adaptador que usa (restadapter | soapadapter)
        /// </summary>
        public string TipoAdaptador { get; set; }

        /// <summary>
        /// 
        /// </summary>
        public string Endpoint { get; set; }
    }
}
