using KamyczkiMobile.Models;
using Newtonsoft.Json;

namespace KamyczkiMobile.Services;

public class ProtectedApiClient : IProtectedApiClient
{
    private readonly HttpClient _httpClient;

    public ProtectedApiClient(HttpClient httpClient)
    {
        _httpClient = httpClient ?? throw new ArgumentNullException(nameof(httpClient));
    }

    public async Task<T> GetProtectedResources<T>(string serviceUri, string endpointUri)
    {
        var response = await _httpClient.GetAsync(serviceUri + endpointUri);
        T result;

        if (response.IsSuccessStatusCode)
        {
            var responseContent = await response.Content.ReadAsStringAsync();
            result = JsonConvert.DeserializeObject<T>(responseContent);
        }
        else if (response.StatusCode == System.Net.HttpStatusCode.Forbidden)
        {
            var responseContent = await response.Content.ReadAsStringAsync();
            var responseDto = JsonConvert.DeserializeObject<ResponseDto>(responseContent);

            throw new Exception(responseDto.message ?? "");
        }
        else
        {
            throw new Exception(ResponseCodes.GetNauraCode());
        }

        return result;
    }

    //TODO: implement other methods post, put etc.
}
