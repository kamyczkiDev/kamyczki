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

        if (string.IsNullOrWhiteSpace(username) || string.IsNullOrWhiteSpace(password))
        {
            await DisplayAlert("UWAGA", "Proszę uzupełnić oba pola", "OK");
            return;
        }
        string responseCode = await _authService.Login(username, password);
        string errorCode = ResponseCodes.IsErrorCode(responseCode);

        if (!string.IsNullOrEmpty(errorCode))
        {
            await DisplayAlert("BŁĄD",errorCode, "OK");
            return;
        }
        else
        {
            //await DisplayAlert("DZIALA KURWA", responseCode, "OK");
            await Shell.Current.GoToAsync("///MainPage", false);
        }
    }
    private async void TapRegister_Tapped(object sender, TappedEventArgs e)
    {
        //flaga zeby nie wyjebalo po spamowaniu
        if (_isChangingPages) return;
        try
        {
            _isChangingPages = true;
            await Shell.Current.GoToAsync(nameof(RegisterPage));
        }
        finally
        {
            _isChangingPages = false;
        }
    }
}