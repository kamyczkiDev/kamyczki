using System;
using System.Net;
using System.Net.Http.Headers;
using System.Net.Http.Json;
using KamyczkiMobile.Models;
using Newtonsoft.Json;

namespace KamyczkiMobile.Services;

public class AuthService : IAuthService
{
    private readonly HttpClient _client;
    private string _token; //token w pamieci zeby za kazdym razem nie brac ze storage i nie zajechac ajfonika? refresh token(ulu)
    public AuthService(HttpClient client)
    {
        _client = client;
        _client.DefaultRequestHeaders.Add("Accept", "application/json");
    }
    public async Task<string> RegisterUser(string username, string userEmail, string userPassword)
    {
        var responseCode = String.Empty;
        var userRegisterRequest = new UserRegisterRequest
        {
            username = username,
            email = userEmail,
            password = userPassword
        };
        var response = await _client.PostAsJsonAsync("user/register", userRegisterRequest);

        if(response.IsSuccessStatusCode)
        {
            responseCode = ResponseCodes.GetSuccessCode();
        }
        else
        {
            var responseContent = await response.Content.ReadAsStringAsync();
            var responseDto = JsonConvert.DeserializeObject<ResponseDto>(responseContent);

            responseCode = responseDto.code;
        }
        
        return responseCode;
    }
    public async Task<string> Login(string username, string password)
    {
        var responseCode = String.Empty;

        var loginRequest = new LoginRequest
        {
            username = username,
            password = password
        };
        var response = await _client.PostAsJsonAsync("auth/sign-in", loginRequest);

        if(response.IsSuccessStatusCode)
        {
            var responseContent = await response.Content.ReadAsStringAsync();
            var loginResponse = JsonConvert.DeserializeObject<LoginResponse>(responseContent);
            try
            {
                _token = loginResponse.token;
                await SecureStorage.Default.SetAsync("access_token", loginResponse.token);
                SetAuthorizationHeaderAfterLogin();
            }
            catch(Exception ex)
            {
                Console.WriteLine(ex);
            }

            responseCode = ResponseCodes.GetSuccessCode();
        }
        else
        {
            var responseContent = await response.Content.ReadAsStringAsync();
            var responseDto = JsonConvert.DeserializeObject<ResponseDto>(responseContent);

            responseCode = responseDto.code;
        }

        return responseCode;
    }
    public async Task<string> Logout()
    {
        _token = string.Empty;
        SecureStorage.Default.RemoveAll();
        _client.DefaultRequestHeaders.Authorization = null;
        return "no za brame juz";
    }
    private void SetAuthorizationHeaderAfterLogin()
    {
        _client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", _token);
    }
    public HttpClient GetHttpClient() => _client;
    public async Task<string> CheckAndGetToken()
    {
        if (string.IsNullOrEmpty(_token))
        {
            //sprawdzic czy token ze stored¿a valid, tera taki placeholder
            _token = await SecureStorage.Default.GetAsync("access_token");
        }
        else
        {
            _token = string.Empty;
        }
        return _token;
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
