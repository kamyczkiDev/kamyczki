using KamyczkiMobile.Services;

namespace KamyczkiMobile.Views;

public partial class MainPage : ContentPage
{
	readonly IAuthService _authService;
	public MainPage(IAuthService authService)
	{
		InitializeComponent();
		_authService = authService;
	}
	private async void Button_Clicked(object sender, EventArgs e)
	{
		var response = await _authService.Logout();
		if (!string.IsNullOrEmpty(response))
		{
            await Shell.Current.GoToAsync("//LoginPage", true);
        }
    }
}