namespace KamyczkiMobile.Views;

public partial class LoginPage : ContentPage
{
	public LoginPage()
	{
		InitializeComponent();
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
}