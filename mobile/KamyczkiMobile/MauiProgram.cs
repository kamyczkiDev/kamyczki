using KamyczkiMobile.Services;
using KamyczkiMobile.Views;
using Microsoft.Extensions.Logging;

namespace KamyczkiMobile;

public static class MauiProgram
{
	public static MauiApp CreateMauiApp()
	{
		var builder = MauiApp.CreateBuilder();
		builder
			.UseMauiApp<App>()
			.ConfigureFonts(fonts =>
			{
				fonts.AddFont("OpenSans-Regular.ttf", "OpenSansRegular");
				fonts.AddFont("OpenSans-Semibold.ttf", "OpenSansSemibold");
			})
			.RegisterServices()
			.RegisterViews();

#if DEBUG
		builder.Logging.AddDebug();
#endif

		return builder.Build();
	}

	public static MauiAppBuilder RegisterServices(this MauiAppBuilder builder)
    {
#if IOS
		// builder.Services.AddSingleton<IDeviceInstallationService, PushNotificationsDemo.Platforms.iOS.DeviceInstallationService>();
#elif ANDROID
        //builder.Services.AddSingleton<IDeviceInstallationService, PushNotificationsDemo.Platforms.Android.DeviceInstallationService>();
#endif
        builder.Services.AddTransient<ProtectedApiBearerTokenHandler>();

        builder.Services.AddHttpClient<AuthService>((httpClient) =>
		{
			httpClient.BaseAddress = new Uri(Config.BackendServiceEndpoint + Config.AuthControllerUri);

        });

        builder.Services.AddHttpClient<IProtectedApiClient, ProtectedApiClient>((httpClient) =>
        {
            httpClient.BaseAddress = new Uri(Config.BackendServiceEndpoint);
			httpClient.DefaultRequestHeaders.Add("Accept", "application/json");
        }).AddHttpMessageHandler<ProtectedApiBearerTokenHandler>();

        builder.Services.AddTransient<IProtectedApiClient, ProtectedApiClient>();

        builder.Services.AddSingleton<IAuthService, AuthService>();
        builder.Services.AddTransient<IStoneReadService, StoneReadService>();

        return builder;
    }

	public static MauiAppBuilder RegisterViews(this MauiAppBuilder builder)
    {
        builder.Services.AddSingleton<Views.MainPage>();
		builder.Services.AddSingleton<RegisterPage>();
		builder.Services.AddSingleton<LoginPage>();

        return builder;
    }
}
