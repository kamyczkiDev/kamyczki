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
        builder.Services.AddSingleton<IDeviceInstallationService, PushNotificationsDemo.Platforms.Android.DeviceInstallationService>();
#endif
        builder.Services.AddSingleton<IAuthService>(new AuthService(Config.BackendServiceEndpoint, Config.AuthControllerUri));

        return builder;
    }

	public static MauiAppBuilder RegisterViews(this MauiAppBuilder builder)
    {
		builder.Services.AddSingleton<LoginPage>();
		builder.Services.AddSingleton<RegisterPage>();
        //builder.Services.AddSingleton<MainPage>();
        return builder;
    }
}
