package pd16.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class User {
    private Long id;
    private String name;
    private String username;
    private String email;
    private Address address;
}
