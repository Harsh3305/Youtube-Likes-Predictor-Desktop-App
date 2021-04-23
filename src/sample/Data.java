package sample;

public class Data {
    private String ChannelName;
    private int Subscriber;
    private int TotalViews;
    private int TotalVideos;
    private String Title;
    private String Description;
    private int Category;

    public Data(String ChannelName, int Subscriber, int TotalViews, int TotalVideos, String Title, String Description)
    {
        this.ChannelName = ChannelName;
        this.Subscriber = Subscriber;
        this.TotalVideos = TotalVideos;
        this.TotalViews = TotalViews;
        this.Title = Title;
        this.Description = Description;
    }

    public int getSubscriber() {
        return Subscriber;
    }

    public int getTotalVideos() {
        return TotalVideos;
    }

    public int getTotalViews() {
        return TotalViews;
    }

    public String getChannelName() {
        return ChannelName;
    }

    public String getDescription() {
        return Description;
    }

    public String getTitle() {
        return Title;
    }

    public void setCategory(int category) {
        Category = category;
    }

    public int getCategory() {
        return Category;
    }
}
