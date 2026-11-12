package pd13.store;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(staticName = "initialize")
@Getter
public class LaptopStore {
    private int availableQuantity = 10;
    private int soldQuantity = 0;

    public synchronized void buy(int orderQuantity) {
        if (orderQuantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (orderQuantity > availableQuantity) {
            throw new InsufficientStockException("Not enough laptops in stock");
        }
        availableQuantity -= orderQuantity;
        soldQuantity += orderQuantity;
    }
}
