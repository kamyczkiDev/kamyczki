using System;

namespace KamyczkiMobile;

public static class ResponseCodes
{
    private static readonly Dictionary<string, string> _codes = new Dictionary<string, string>()
    {
        {"SUCESS_CODE", "SUCESS_CODE"}
    };
    private static readonly Dictionary<string, string> _errorCodes = new Dictionary<string, string>()
    {
        {"",""},
        {"RESOURCE_ALREADY_EXISTS", "Podana wartość juz istnieje"},
        {"RESOURCE_NOT_FOUND","Nie znaleziono użytkownika"}
    };
    public static string IsErrorCode(string code)
    {
        if(_errorCodes.ContainsKey(code))
        {
            return _errorCodes[code];
        }
        return string.Empty;
    }
    public static string GetSuccessCode()
    {
        var code = "SUCESS_CODE";
        // if(_codes.TryGetValue("SUCESS_CODE", out value))
        // {
        //     code = value;
        // }

        return code;
    }
}
