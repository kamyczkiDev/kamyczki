using System;

namespace KamyczkiMobile;

public static class Config
{
    //local dla androida: "http://10.0.2.2:8080"
    //local wszedzie indziej :D http://localhost:8080
    public static string BackendServiceEndpoint = "http://10.0.2.2:8080";
    public static string AuthControllerUri = "/auth/api/";
}
