package pd5;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class User implements Identifiable<Long> {
    private Long id;
    @Getter
    @Setter
    private String name;

    @Override
    public Long getID() {
        return id;
    }

}
