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
		ErrorMessage.IsVisible = false;
		SuccessMessage.IsVisible = false;
    }

	private async void OnRegisterClicked(object sender, EventArgs e)
    {
		string username = UsernameEntry.Text;
		string email = EmailEntry.Text;
        string password = PasswordEntry.Text;

        string responseCode = await _authService.RegisterUser(username, email, password);

		if(ResponseCodes.IsErrorCode(responseCode))
		{
			ErrorMessage.Text = "Coś poszło nie tak";
            ErrorMessage.IsVisible = true;
		}
		else
		{
			SuccessMessage.Text = "Utworzono nowe konto";
			SuccessMessage.IsVisible = true;
		}
    }
}