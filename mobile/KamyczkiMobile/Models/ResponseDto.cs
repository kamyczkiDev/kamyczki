using System;

namespace KamyczkiMobile.Models;

public class ResponseDto
{
    public string code { get; set; } = String.Empty;
    public string message { get; set; } = String.Empty;
    public string field { get; set; } = String.Empty;
}
