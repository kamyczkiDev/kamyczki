using System;

namespace KamyczkiMobile.Services;

public interface IAuthService
{
    Task<string> RegisterUser(string userEmail, string userPassword);
    Task<string> Login(string userEmail, string userPassword);
    Task<string> Logout();
    Task<string> RefreshToken();
}
