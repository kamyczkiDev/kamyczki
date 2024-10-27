using KamyczkiMobile.Services;
using KamyczkiMobile.Models;

namespace KamyczkiMobile.Views;

public partial class RegisterPage : ContentPage
{
    readonly IAuthService _authService;

	public RegisterPage(IAuthService authService)
	{
		InitializeComponent();

		 _authService = authService;
	}
	private void InitializePage()
    {
		//ErrorMessage.IsVisible = false;
		//SuccessMessage.IsVisible = false;
    }
	private async void OnRegisterClicked(object sender, EventArgs e)
    {
		string username = EntUsername.Text;
		string email = EntEmail.Text;
        string password = EntPassword.Text;

        string responseCode = await _authService.RegisterUser(username, email, password);

        string errorCode = ResponseCodes.IsErrorCode(responseCode);

        if (!string.IsNullOrEmpty(errorCode))
        {
            await DisplayAlert("", errorCode, "OK");
            return;
        }
        else
        {
            await DisplayAlert("UTWORZONO KONTO", responseCode, "OK");
            await Shell.Current.GoToAsync("..", true);
        }
    }
    private async void TapLogin_Tapped(object sender, TappedEventArgs e)
    {
        //flaga zeby nie wyjebalo po spamowaniu
        await Shell.Current.GoToAsync("//LoginPage", true);
    }
}