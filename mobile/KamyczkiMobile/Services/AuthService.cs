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
            string responseContent = await response.Content.ReadAsStringAsync();
            var loginResponse = JsonConvert.DeserializeObject<LoginResponse>(responseContent);
            try
            {
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
            if (responseContent == null)
            {
                responseCode = ResponseCodes.GetNauraCode();
                return responseCode;
            }
            var responseDto = JsonConvert.DeserializeObject<ResponseDto>(responseContent);

            responseCode = responseDto.code;
        }

        return responseCode;
    }
    public async Task<string> Logout()
    {
        SecureStorage.Default.RemoveAll();
        _client.DefaultRequestHeaders.Authorization = null;
        return "no za brame juz";
    }
    private async void SetAuthorizationHeaderAfterLogin()
    {
        var token = await CheckAndGetToken();
        _client.DefaultRequestHeaders.Authorization = new AuthenticationHeaderValue("Bearer", token);
    }
    public HttpClient GetHttpClient() => _client;
    public async Task<string> CheckAndGetToken()
    {
        string _token = await SecureStorage.Default.GetAsync("access_token");
        if (!string.IsNullOrEmpty(_token))
        {
            return _token;
        }
        return "";
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
