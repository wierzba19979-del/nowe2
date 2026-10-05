package pd5.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Product implements Identifiable<String> {
    private String id;
    @Setter
    @Getter
    private String name;

    @Override
    public String getID() {
        return id;
    }

}
