using KamyczkiMobile.Services;

namespace KamyczkiMobile.Views;

public partial class LoginPage : ContentPage
{
    readonly IAuthService _authService;
    private bool _isChangingPages = false;
	public LoginPage(IAuthService authService)
	{
		InitializeComponent();
        _authService = authService;
	}
	private async void BtnLogin_Clicked(object sender, EventArgs e)
    {
        string username = EntUsername.Text;
        string password = EntPassword.Text;
        // Simple validation check
        if (string.IsNullOrWhiteSpace(username) || string.IsNullOrWhiteSpace(password))
        {
            //ErrorMessage.Text = "Please enter both username and password.";
            //ErrorMessage.IsVisible = true;
            await DisplayAlert("UWAGA", "Proszę uzupełnić oba pola", "OK");
            return;
        }
        // Hardcoded login for demonstration purposes
        if (username == "admin" && password == "password")
        {
            await DisplayAlert("", "Zalogowano", "OK");
            //ErrorMessage.Text = "Zalogowano!";
            //ErrorMessage.TextColor = Colors.Green;
            //ErrorMessage.IsVisible = true;
        }
        else
        {
            await DisplayAlert("","Nieprawidłowa nazwa uytkownika lub hasło.", "OK");
            //ErrorMessage.Text = "Nieprawidłowa nazwa uytkownika lub hasło.";
            //ErrorMessage.IsVisible = true;
        }
    }
    private async void TapRegister_Tapped(object sender, TappedEventArgs e)
    {
        //flaga zeby nie wyjebalo po spamowaniu
        if (_isChangingPages) return;

        try
        {
            _isChangingPages = true;
            await Shell.Current.GoToAsync(nameof(RegisterPage),true);
        }
        finally
        {
            _isChangingPages = false;
        }
    }
}