package pd16.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import pd16.model.Post;
import pd16.model.Todo;
import pd16.model.User;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.List;

public class JsonPlaceholderGateway extends AbstractApiGateway {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public JsonPlaceholderGateway(HttpClient httpClient, ObjectMapper objectMapper) {
        super(httpClient, objectMapper);
    }

    public User getUser(Long userId) throws IOException, InterruptedException {
        String url = BASE_URL + "/users/" + userId;
        return sendGet(url, "JSONPlaceholder",
                body -> objectMapper.readValue(body, User.class));
    }

    public List<Todo> getTodos(Long userId) throws IOException, InterruptedException {
        String url = BASE_URL + "/users/" + userId + "/todos";
        List<Todo> todos = sendGet(url, "JSONPlaceholder TODO",
                body -> objectMapper.readValue(
                        body,
                        new TypeReference<List<Todo>>() {}
                ));
        return todos.stream()
                .filter(todo -> !todo.isCompleted())
                .toList();
    }

    public List<Post> getPosts(Long userId) throws IOException, InterruptedException {
        String url = BASE_URL + "/users/" + userId + "/posts";
        List<Post> posts = sendGet(url, "JSONPlaceholder POSTS",
                body -> objectMapper.readValue(
                        body,
                        new TypeReference<List<Post>>() {}
                ));
        return posts.stream()
                .sorted((p1, p2) -> p2.getId().compareTo(p1.getId()))
                .limit(3)
                .toList();
    }
}
