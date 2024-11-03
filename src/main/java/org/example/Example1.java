package org.example;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Example1 {

    public static final String URL = "https://gorest.co.in/";
    private static final String TOKEN = "eae5679842119c19e51bccfbfeccb538e9499ea49ea487cbabed0233e9ec8be5";

    public static void main(String[] args) throws IOException, InterruptedException {
        URI uri = URI.create(URL + "public/v2/users");
        Gson gson = new Gson();
        final URI uri1 = MyURIBuilder.builder()
                .withURL(URL)
                .withResource("public/v2/users")
                .withQueryParameters(new MyPage(1, 1))
                .withQueryParameters(new MyLanguage("en"))
                .build();
        System.out.println(uri1.toString());
        final URI uri2 = MyURIBuilder.builder()
                .withURL(URL)
                .withResource("public/v2/users")
                .withQueryParameters(new MyAuthorization(TOKEN))
                .build();
        final HttpRequest usersRequest = HttpRequest.newBuilder()
                .GET()
                .uri(uri)
                .build();
        User userRequest = new User();
        userRequest.setEmail("dmytro@gmail.com");
        userRequest.setName("Dmytro 4455");
        userRequest.setGender("male");
        userRequest.setStatus("active");

        final HttpRequest userCreationRequest = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(userRequest)))
                .uri(uri2)
                . build();


        final HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .connectTimeout(Duration.ofMinutes(1))
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        final HttpResponse<String> usersResponse =
                client.send(usersRequest, HttpResponse.BodyHandlers.ofString());
        final int statusCode = usersResponse.statusCode();
        final String body = usersResponse.body();

        System.out.println(statusCode);
        System.out.println(body);

        final User[] users = gson.fromJson(body, User[].class);
        System.out.println("====================");
        for (User user : users) {
            System.out.println(user);
            System.out.println();
        }
        System.out.println("----------------------");

        System.out.println("========2=============");
        final HttpResponse<String> userCreationResponse =
                client.send(userCreationRequest, HttpResponse.BodyHandlers.ofString());
        final int statusCode2 = userCreationResponse.statusCode();
        String body2 = userCreationResponse.body();

     //   final User createdUser = gson.fromJson(body2, User.class);

    //    System.out.println(createdUser);
        System.out.println(body2);
        System.out.println(statusCode2);

    }
}

class User{
    private Long id;
    private String name;
    private String email;
    private String gender;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

class MyURIBuilder{
    private String url;
    private String resource;
    private List <QueryParameters> queries = new ArrayList<>();


    public MyURIBuilder() {
    }
    public static MyURIBuilder builder(){
        return new MyURIBuilder();
    }

    public MyURIBuilder withURL (String url){
        this.url = url;
        return this;
    }

    public MyURIBuilder withResource (String resource){
        this.resource = resource;
        return this;
    }

    public MyURIBuilder withQueryParameters(QueryParameters queryParameters){
        this.queries.add(queryParameters);
        return this;
    }

    public URI build() {
        String link = new StringBuilder()
                .append(url)
                .append(resource)
                .append(queries())
                .toString();
        System.out.println("link: " + link);
        return URI.create(link);
    }

    private String queries() {
        if (queries.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder("?");
        queries.forEach(x -> builder.append(x.query() + "&"));
        return builder.toString();
    }
}
 interface QueryParameters {
    String query();
 }

 class MyPage implements QueryParameters{
    private final  int page;
    private final  int perPage;

     public MyPage(int page, int perPage) {
         this.page = page;
         this.perPage = perPage;
     }

     @Override
     public String query(){
         return String.format("page=%d&per_page=%d",page,perPage);

     }
 }

 class MyLanguage implements QueryParameters{
    private final String language;

     public MyLanguage(String language) {
         this.language = language;
     }

     @Override
     public String query() {
         return String.format("language=%s" , language);
     }
 }

 class MyAuthorization implements QueryParameters{
    private final String token;

    MyAuthorization (String token){
        this.token = token;
    }

     @Override
     public String query() {
         return String.format("access-token=%s", token);

     }
 }