using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Dynamic;
using RestAdapter.Models;
using RestAdapter.Interfaces;
using System.Reflection;

namespace RestAdapter.Services
{
    public class FieldAdapter : IFieldMapper
    {

        public string GetBodyMapped(object metadataInfo, string body)
        {
            Type metadataType = metadataInfo.GetType();//Obtiene el tipo de objeto de metadata 
                                                       //Recorre cada una de las propiedades de la metadata y reemplaza el valor del bodyRequest
            foreach (var item in metadataType.GetProperties())
            {
                var fieldValue = (MetaType)metadataType.GetProperty(item.Name).GetValue(metadataInfo, null);
                if (fieldValue != null && fieldValue.Field != "")
                    body = body.Replace(item.Name, fieldValue.Field);
            }

            return body;//retorna el cuerpo listo para ser
        }

        public string GetUrlMapped(object metadataInfo, string url)
        {
            var pattern = @"{[a-zA-Z]*}";
            var regex = new System.Text.RegularExpressions.Regex(pattern);
            var matches = regex.Matches(url);
            if (matches.Count == 0)//Retorna la url igual porque no necesita reemplazar ningun parámetro
                return url;

            Type metadataType = metadataInfo.GetType();//Obtiene el tipo de objeto de metadata 
            //Recorre cada una de las propiedades de la metadata y reemplaza el valor del bodyRequest
            for (int i = 0; i < matches.Count; i++)
            {
                var item = matches[i].Value;
                var value = getUrlParam(item);
                var property = metadataType.GetProperty(value);
                if (property!=null)
                {
                    var fieldValue = property.GetValue(metadataInfo, null);
                    if (fieldValue != null)
                    {
                        url = url.Replace(item, fieldValue.ToString());
                    }
                }
            }

            /*
            foreach (var item in metadataType.GetProperties())
            {
                var asdfdsf = metadataType.GetProperty(item.Name);
                var fieldValue = metadataType.GetProperty(item.Name).GetValue(metadataInfo, null);
                if (fieldValue != null)
                {
                    url = url.Replace(item.Name, fieldValue.ToString());
                }
            }*/

            return url;

        }

        private string getUrlParam(string item)
        {
            return item.Replace("{", "").Replace("}", "");
        }


        private Type GetTypeFromDll(string assembly, string fullNameObject)
        {
            Assembly a = Assembly.Load(assembly);
            Type t = a.GetType(fullNameObject);
            return t;
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
