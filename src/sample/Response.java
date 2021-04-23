package sample;

public class Response {
    private int likes;
    public Response(int likes) {
        this.likes = likes;
    }
    public Response() {
        this.likes = -1;
    }
    public int getLikes () {
        return likes;
    }
    public void setLikes (int likes) {
        this.likes = likes;
    }
}
