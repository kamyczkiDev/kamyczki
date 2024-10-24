using System;

namespace KamyczkiMobile.Models;

public class UserRegisterRequest
{
    public string username { get; set; } = String.Empty;
    public string password { get; set; } = String.Empty;
    public string email { get; set; } = String.Empty;
}
