package pd13.store;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(staticName = "initialize")
@Getter
public class LaptopStore {
    private int availableQuantity = 10;

    public synchronized boolean buy(int orderQuantity) {
        if (orderQuantity > availableQuantity || orderQuantity <= 0) {
            return false;
        }
        availableQuantity -= orderQuantity;
        return true;
    }
}
