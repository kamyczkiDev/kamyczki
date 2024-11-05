namespace KamyczkiMobile.Services;

public class ProtectedApiBearerTokenHandler : DelegatingHandler
{
    protected override async Task<HttpResponseMessage> SendAsync(
        HttpRequestMessage request,
        CancellationToken cancellationToken)
    {
        var accessToken = await SecureStorage.Default.GetAsync("access_token");

        if (!string.IsNullOrEmpty(accessToken))
        {
            //TODO handle token refetch
            request.Headers.Add("Authorization", "Bearer" + accessToken);
        }
        else
        {
            //TODO handle if no token
        }

        return await base.SendAsync(request, cancellationToken);
    }
}
