package pd5;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Subscription {
    private boolean active;
    private String discountCode;

}
