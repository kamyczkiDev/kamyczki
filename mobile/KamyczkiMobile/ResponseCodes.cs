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
        {"RESOURCE_ALREADY_EXISTS", "Podana wartość juz istnieje"}
    };

    public static bool IsErrorCode(string code)
    {
        return _errorCodes.ContainsKey(code);
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
