using KamyczkiMobile.Services;
using KamyczkiMobile.Views;

namespace KamyczkiMobile;

public partial class App : Application
{
	private readonly IAuthService _authService;
	public App(IAuthService authService)
	{
		InitializeComponent();
		_authService = authService;
		SetMainPageBasedOnToken();
    }
	private async void SetMainPageBasedOnToken()
	{
		MainPage = new AppShell();
		var token = await _authService.CheckAndGetToken();
		if(!string.IsNullOrEmpty(token))
		{
			await Shell.Current.GoToAsync("///MainPage", false);
        }
		else
		{
			await Shell.Current.GoToAsync("//"+nameof(LoginPage), false);
		}
    }
}
