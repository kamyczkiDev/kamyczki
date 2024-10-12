using System;
using System.Net;
using System.Net.Http.Json;
using KamyczkiMobile.Models;
using Newtonsoft.Json;

namespace KamyczkiMobile.Services;

public class AuthService : IAuthService
{
    HttpClient _client;

    public AuthService(string baseApiUrl, string authUri)
    {
        _client = new HttpClient();
        _client.DefaultRequestHeaders.Add("Accept", "application/json");
        _client.BaseAddress = new Uri(baseApiUrl + authUri);
    }

    public async Task<string> RegisterUser(string username, string userEmail, string userPassword)
    {
        var userRegisterRequest = new UserRegisterRequest
        {
            username = username,
            email = userEmail,
            password = userPassword
        };
        var response = await _client.PostAsJsonAsync("user/register", userRegisterRequest);

        if(response.IsSuccessStatusCode)
        {
            return "SUCESS_CODE";
        }
        var responseContent = await response.Content.ReadAsStringAsync();
        var responseDto = JsonConvert.DeserializeObject<ResponseDto>(responseContent);

        return responseDto.code;
    }

    public async Task<string> Login(string userEmail, string userPassword)
    {
        try
        {
            await SecureStorage.Default.SetAsync("access_token", "test");
        }
        catch(Exception ex)
        {
            Console.WriteLine(ex);
        }

        return "test";
    }

    public async Task<string> Logout()
    {
        SecureStorage.Default.RemoveAll();
        return "test";
    }

    public async Task<string> RefreshToken()
    {
         try
        {
            await SecureStorage.Default.SetAsync("access_token", "test");
        }
        catch(Exception ex)
        {
            Console.WriteLine(ex);
        }

        return "test";
    }
}
