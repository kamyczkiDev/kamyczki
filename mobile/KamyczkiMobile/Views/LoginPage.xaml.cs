using KamyczkiMobile.Services;

namespace KamyczkiMobile.Views;

public partial class LoginPage : ContentPage
{
    readonly IAuthService _authService;
	public LoginPage(IAuthService authService)
	{
		InitializeComponent();

        _authService = authService;
	}

	private void OnLoginClicked(object sender, EventArgs e)
    {
        string username = UsernameEntry.Text;
        string password = PasswordEntry.Text;
        // Simple validation check
        if (string.IsNullOrWhiteSpace(username) || string.IsNullOrWhiteSpace(password))
        {
            ErrorMessage.Text = "Please enter both username and password.";
            ErrorMessage.IsVisible = true;
            return;
        }
        // Hardcoded login for demonstration purposes
        if (username == "admin" && password == "password")
        {
            ErrorMessage.Text = "Zalogowano!";
            ErrorMessage.TextColor = Colors.Green;
            ErrorMessage.IsVisible = true;
        }
        else
        {
            ErrorMessage.Text = "Nieprawidłowa nazwa uytkownika lub hasło.";
            ErrorMessage.IsVisible = true;
        }
    }

    private async void OnRegisterClicked(object sender, EventArgs e)
    {
        await _authService.RegisterUser("test@test.pl", "test");
        //await Shell.Current.GoToAsync("RegisterPage");
    }
}