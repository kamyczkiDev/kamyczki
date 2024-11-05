namespace KamyczkiMobile.Services;

public class StoneReadService : IStoneReadService
{
    private readonly IProtectedApiClient _protectedApiClient; 

    public StoneReadService(IProtectedApiClient protectedApiClient)
    {
        _protectedApiClient = protectedApiClient;
    }

    public async Task<String?> GetStone(String stoneId)
    {
        var result = await _protectedApiClient.GetProtectedResources<String>("/stone-read", "/api/stone/" + stoneId);

        return result;
    }
}
