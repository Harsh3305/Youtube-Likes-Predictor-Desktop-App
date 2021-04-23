package sample;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import org.json.simple.JSONValue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Data myData;
    HashMap<String, Integer>  map = new HashMap<>();
//    @FXML
//    private Label Result;
//
//    @FXML
//    private ChoiceBox<String> Category;
//

//    public void change()
//    {
//        Category = new ChoiceBox<String>(FXCollections.observableArrayList(list));
//    }
    @FXML
    private TextField TotalViews;

    @FXML
    private TextField SubscriberCount;

    @FXML
    private Button nextButton;

    @FXML
    private TextField VideoTitle;

    @FXML
    private TextArea Description;

    @FXML
    private TextField TotalVideos;

    @FXML
    private AnchorPane Scene2;
    @FXML
    private AnchorPane Scene1;

    @FXML
    private TextField ChannelName;

    @FXML
    private RadioButton cat1;

    @FXML
    private RadioButton cat2;

    @FXML
    private RadioButton cat3;

    @FXML
    private RadioButton cat4,cat5,cat6,cat7,cat8,cat9,cat10,cat11,cat12,cat13,cat14,cat15,cat16;

    @FXML
    private RadioButton cat17,cat18,cat19,cat20,cat21,cat22,cat23,cat24,cat25,cat26,cat27,cat28,cat29;

    @FXML
    private Button finalSubmit;
    @FXML
    private RadioButton selected;
    @FXML
    void changeScene(MouseEvent event) {
        Scene2.setOpacity(1.0);
        Scene2.setDisable(false);
        Scene1.setOpacity(0.0);
        Scene1.setDisable(true);
        myData = new Data(ChannelName.getText().toString(),
                Integer.parseInt(SubscriberCount.getText().toString()),
                Integer.parseInt(TotalViews.getText().toString()),
                Integer.parseInt(TotalVideos.getText().toString()),
                VideoTitle.getText().toString(),
                Description.getText().toString()
                );

        System.out.print(myData.getChannelName()+"\n"+myData.getSubscriber()+"\n"+myData.getTotalViews()
        +"\n"+myData.getTotalVideos()+"\n"+myData.getTitle()+"\n"+myData.getDescription());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        map.put("Film & Animation",1);
        map.put("Auto & Vehicles", 2);
        map.put("Music",10);
        map.put("Pets & Animals",15);
        map.put("Sports",17);
        map.put("Shorts & Movies",18);
        map.put("Travel & Events",19);
        map.put("Gaming",20);
        map.put("Videoblogging",21);
        map.put("People & Blogs",22);
        map.put("Comedy",23);
        map.put("Entertainment",24);
        map.put("News & Politics",25);
        map.put("Howto & Style",26);
        map.put("Education",27);
        map.put("Science & Technology",28);
        map.put("Nonprofits & Activism",29);
        map.put("Movies",30);
        map.put("Anime/Animation",31);
        map.put("Action/Adventure",32);
        map.put("Classics",33);
        map.put("Documentary",35);
        map.put("Drama",36);
        map.put("Family",37);
        map.put("Foreign",38);
        map.put("Horror",39);
        map.put("Sci-Fi/Fantasy",40);
        map.put("Thriller",41);
        map.put("Shorts",42);
        map.put("Shows",43);
    }
    @FXML
    void select(MouseEvent event){

        if(selected==null)
        {
            selected = (RadioButton)event.getSource();
            selected.setSelected(true);
        }
        else {
            selected.setSelected(false);
            selected = (RadioButton)event.getSource();
            selected.setSelected(true);
        }
    }
    @FXML
    void CollectData(MouseEvent event)throws Exception {
        myData.setCategory(map.get(selected.getText().toString()));
        System.out.print(myData.getChannelName()+"\n"+myData.getSubscriber()+"\n"+myData.getTotalViews()
                +"\n"+myData.getTotalVideos()+"\n"+myData.getTitle()+"\n"+myData.getDescription()+"\n"+
                myData.getCategory());

        ResultScene.setOpacity(1);
        ResultScene.setVisible(true);
        ResultScene.setDisable(false);
        Scene2.setDisable(true);
        Scene2.setVisible(false);
//        sendData();
        loadData(myData.getCategory()+"",myData.getTotalViews()+"",myData.getTitle(),myData.getTotalVideos()+"",myData.getDescription(),
                myData.getSubscriber()+"");
    }

    private void sendData() throws Exception
    {
        URL url = new URL("https://youtube-predictor.herokuapp.com/PredictLikes");
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        Map obj= new HashMap();
        obj.put("categoryId",new Integer(myData.getCategory()));
        obj.put("view_count",new Integer(myData.getTotalViews()));
        obj.put("video_count",new Integer(myData.getTotalVideos()));
        obj.put("subscriber_count", new Integer(myData.getSubscriber()));
        obj.put("video_title", myData.getTitle());
        obj.put("description",myData.getDescription());

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
            System.out.println(response.toString());
        }

    }
    @FXML
    private AnchorPane ResultScene;
    @FXML
    private Text likesResult;
    @FXML
    private ImageView loadingGif;
    private Response resp;
    private void loadData (String id_text, String view_count_text, String video_title_text, String video_count_text, String description_text, String subscriber_count_text) {
//        loadingGIF.setVisible(true);
        FetchData fetchData  = new FetchData();
        resp = fetchData.readData(id_text, view_count_text, video_title_text, video_count_text, description_text, subscriber_count_text);
        Runnable r = () -> {
            try {
                int d = 0;
                while (resp.getLikes() == -1) {
                    Thread.sleep(100);
//                    text.setText("Loading " + d++);
                    d%= 10;
                }
                loadingGif.setVisible(false);
                likesResult.setText("Predicted Likes : "+resp.getLikes());
//                likesResult.setVisible(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        new Thread(r).start();
    }

}
