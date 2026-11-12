package pd13.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class Order {
    private String name;
    private int orderQuantity;
}
