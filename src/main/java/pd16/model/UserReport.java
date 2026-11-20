package pd16.model;

import java.util.List;

public record UserReport(
        User user,
        List<Todo> todos,
        List<Post> posts,
        Weather weather,
        Country country
) {
    public UserReport {
        todos = List.copyOf(todos);
        posts = List.copyOf(posts);
    }
}