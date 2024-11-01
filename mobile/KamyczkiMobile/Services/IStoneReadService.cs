namespace KamyczkiMobile.Services;

public interface IStoneReadService
{
    Task<String?> GetStone(String stoneId);
}
