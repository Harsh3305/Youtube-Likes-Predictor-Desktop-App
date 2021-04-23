package sample;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonReader {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static int readJsonFromUrl(String link, Map obj) throws IOException, JSONException  {
        URL url = new URL(link);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);


        String jsonInputString = JSONValue.toJSONString(obj);

        try(OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
//            JSONParser parser = new JSONParser();
//            JSONObject result = (JSONObject)parser.parse(response.toString());
              System.out.println(response.toString());
            String res = response.toString().substring(9,response.toString().length()-1);
            return Integer.parseInt(res);
        }
//        return null;
    }

    public static int readData(String id_text, String view_count_text, String video_title_text, String video_count_text, String description_text, String subscriber_count_text) throws IOException, JSONException {
//        String id_text = "1";
//        String view_count_text = "100000";
//        String video_title_text = "PubG";
//        String video_count_text = "100";
//        String description_text = "Tutorial";
//        String subscriber_count_text = "1000000";

        Map obj= new HashMap();
        obj.put("categoryId",Integer.parseInt(id_text));
        obj.put("view_count",Integer.parseInt(view_count_text));
        obj.put("video_count",Integer.parseInt(video_count_text));
        obj.put("subscriber_count", Integer.parseInt(subscriber_count_text));
        obj.put("video_title", video_title_text);
        obj.put("description",description_text);

        String url = "https://youtube-predictor.herokuapp.com/PredictLikes";

        int json = readJsonFromUrl(url, obj);
        System.out.println(json);
        return (int)(json);
    }
}
