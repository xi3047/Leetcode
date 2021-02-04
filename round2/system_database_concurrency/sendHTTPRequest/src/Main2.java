package round2.system_database_concurrency.sendHTTPRequest.src;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.SQLOutput;

/**
 * @author Xi Zhang
 * @date 2/2/21 9:45 下午
 * @topic round2.system_database_concurrency.sendHTTPRequest.src
 * @link https://www.youtube.com/watch?v=qzRKa8I36Ww&ab_channel=CodingMaster-ProgrammingTutorials
 * @description
 * Method 2, use java.net.http.HttpClient to handle asynchronous operations for you
 */
public class Main2 {

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("https://jsonplaceholder.typicode.com/albums")).build();
         client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(Main::parse)
                .join();

//        String json = new StringBuilder()
//                .append("{")
//                .append("\"name\":\"mkyong\",")
//                .append("\"notes\":\"hello\"")
//                .append("}").toString();
//
//        HttpRequest postRequest = HttpRequest.newBuilder()
//                .POST(HttpRequest.BodyPublishers.ofString(json))
//                .uri(URI.create("https://httpbin.org/post"))
//                .build();
//        HttpResponse<String> response = client.send(postRequest, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.statusCode());

    }

    public static String parse(String responseBody) {
        JSONArray albums = new JSONArray(responseBody);
        for (int i = 0; i < albums.length(); i++) {
            JSONObject album = albums.getJSONObject(i);
            int id = album.getInt("id");
            int userId = album.getInt("userId");
            String title = album.getString("title");
            System.out.println(id + " " + title + " " + userId);
        }
        return null;
    }
}
