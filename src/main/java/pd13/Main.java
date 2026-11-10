package pd13;

import pd13.model.Order;
import pd13.store.LaptopStore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Main {
    public static void main(String[] args) {
        LaptopStore store = LaptopStore.initialize();
        List<Order> orders = new ArrayList<>();
        orders.add(Order.initialize("John", 2));
        orders.add(Order.initialize("Jessica", 1));
        orders.add(Order.initialize("Kevin", 1));
        orders.add(Order.initialize("James", 3));
        orders.add(Order.initialize("Conrad", 4));
        orders.add(Order.initialize("Monica", 2));
        List<CompletableFuture<Boolean>> futures = orders.stream().
                map(order -> CompletableFuture.supplyAsync(
                        () -> store.buy(order.getOrderQuantity())
                )).toList();
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        int soldQuantity = 0;
        for (int i = 0; i < orders.size(); i++) {
            Order currentOrder = orders.get(i);
            boolean success = futures.get(i).join();
            if (success) {
                soldQuantity += currentOrder.getOrderQuantity();
                System.out.println(currentOrder.getName() + " - " + "Successful purchase: " + currentOrder.getOrderQuantity());
            } else {
                System.out.println(currentOrder.getName() + " - " + "Purchase unsuccessful: " + currentOrder.getOrderQuantity());
            }
        }
        System.out.println("\nNumber of laptops sold: " + soldQuantity);
        System.out.println("\nCurrent stock status for laptops: " + store.getAvailableQuantity());
    }
}
