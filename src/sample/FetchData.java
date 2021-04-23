package sample;

import java.io.IOException;
import java.net.ResponseCache;

public class FetchData {

//    private Data myData;

    public Response readData (String id_text, String view_count_text, String video_title_text, String video_count_text, String description_text, String subscriber_count_text) {
        Response response = new Response();
        Runnable r = () -> {
            try {
                int likes = JsonReader.readData(id_text, view_count_text, video_title_text, video_count_text, description_text, subscriber_count_text);
                response.setLikes(likes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        Thread backgroundThread = new Thread(r);
        backgroundThread.start();
        return response;
    }
}
