using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Dynamic;

namespace RestAdapter.Services
{
    public class FieldAdapter
    {

		/// <summary>
		/// Maperar Campos
		/// </summary>
		public void MapperField(RestAdapter.Models.MetadataProvider metadataProvider)
		{
			
		}
			   
		public void Exp()
		{
			dynamic dynamicobj = new ExpandoObject();
			dynamicobj.nombre = "";
		}


  //      private string AssignParameterValue(string paramName, string paramType, Object valor)
  //      {
		//	var value
		//	switch (paramType)
		//	{
		//		case "bigint":
		//			param.Value = Convert.ToInt64(valor);
		//			break;
		//		case "int":
		//			param.Value = Convert.ToInt32(valor);
		//			break;
		//		case "smallint":
		//			param.Value = Convert.ToInt16(valor);
		//			break;
		//		case "byte":
		//			param.Value = Convert.ToByte(valor);
		//			break;
		//		case "Bit":
		//			param.Value = Convert.ToBoolean(valor);
		//			break;
		//		case "Char":
		//		case "string":	
		//			param.Value = valor.ToString();
		//			break;
		//		case "Decimal":
		//		case "Money":
		//		case "SmallMoney":
		//			param.Value = Convert.ToDecimal(valor);
		//			break;
		//		case "float":
		//			param.Value = Convert.ToDouble(valor);
		//			break;
		//		case "Date":
		//		case "DateTime":
		//		case "SmallDateTime":
		//			// param.Value = Convert.ToDateTime( valor, dfi );
		//			param.Value = valor.ToString();
		//			break;

		//	}
		//}
    }




}
