
using KamyczkiMobile.Models.Stones;

namespace KamyczkiMobile.Views;

public partial class MainPage : ContentPage
{
	public MainPage()
	{
		InitializeComponent();
		GenerateStones();
	}
	private void GenerateStones()
	{
		List<Stone> stones = new List<Stone>();

		for (int i = 0; i < 5; i++)
		{
			Stone stone = new Stone()
			{
				UserName = "Beatka "+i,
				Date = DateTime.Now.ToShortDateString(),
				Description = "ale kamyk lasdasdasdasdfasdfionasdfipnewfonweofwefowenoiuweqfoiunewfqnuiofewdquniofwqeounifewquniofwequniofweuniofweqnuiofewnouifewqnouifewounifewuinofewuniowfenuiofwequnio "+i,
				ImageUrl = "kamyk"+i,
				TimeStamp = DateTime.Now.ToShortDateString(),
			};
			stones.Add(stone);
		}
		CvStones.ItemsSource = stones;
    }
	
}