using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KamyczkiMobile.Services;

public interface IProtectedApiClient
{
    Task<T> GetProtectedResources<T>(string serviceUri, string endpointUri);
}
