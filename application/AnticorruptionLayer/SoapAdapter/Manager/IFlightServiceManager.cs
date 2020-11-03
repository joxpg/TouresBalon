using DomainModel.Dto.Transport;
using SoapAdapter.Interfaces;
using System.Threading.Tasks;

namespace SoapAdapter.Manager
{
    public interface IFlightServiceManager:IManagerMapper<GeneralFlightDto>
    {
    }
}
