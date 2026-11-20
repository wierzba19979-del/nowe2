package pd16.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Todo {
    private Long userId;
    private Long id;
    private String title;
    private boolean completed;
}
