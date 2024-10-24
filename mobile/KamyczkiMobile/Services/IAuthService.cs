using System;
using KamyczkiMobile.Models;

namespace KamyczkiMobile.Services;

public interface IAuthService
{
    Task<string> RegisterUser(string username, string userEmail, string userPassword);
    Task<string> Login(string username, string password);
    Task<string> Logout();
    Task<string> RefreshToken();
}
