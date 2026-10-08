package pd5.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pd5.Subscription;

@AllArgsConstructor
@ToString
public class User implements Identifiable<Long> {
    private Long id;
    @Getter
    @Setter
    private String name;
    @Getter
    private Subscription subscription;

    @Override
    public Long getID() {
        return id;
    }

}
