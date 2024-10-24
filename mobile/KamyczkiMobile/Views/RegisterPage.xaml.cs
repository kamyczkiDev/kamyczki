using KamyczkiMobile.Services;
using KamyczkiMobile.Models;

namespace KamyczkiMobile.Views;

public partial class RegisterPage : ContentPage
{
    readonly IAuthService _authService;
	private bool _isChangingPages = false;

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

		if(ResponseCodes.IsErrorCode(responseCode))
		{
			await DisplayAlert("UWAGA", "Coś poszło nie tak", "OK");
			//ErrorMessage.Text = "Coś poszło nie tak";
            //ErrorMessage.IsVisible = true;
		}
		else
		{
            await DisplayAlert("UWAGA", "Utworzono nowe konto", "OK");
            //SuccessMessage.Text = "Utworzono nowe konto";
			//SuccessMessage.IsVisible = true;
		}
    }
    private async void TapLogin_Tapped(object sender, TappedEventArgs e)
    {
        //flaga zeby nie wyjebalo po spamowaniu
        if (_isChangingPages) return;
        try
        {
            _isChangingPages = true;
            await Shell.Current.GoToAsync("..", true);
        }
        finally
        {
            _isChangingPages = false;
        }
    }
}