
package pd13;

import pd13.model.Order;
import pd13.store.LaptopStore;
import pd13.store.InsufficientStockException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public class Main {
    public static void main(String[] args) {
        LaptopStore store = LaptopStore.initialize();
        List<Order> orders = new ArrayList<>();
        orders.add(Order.of("John", 2));
        orders.add(Order.of("Jessica", 1));
        orders.add(Order.of("Kevin", 1));
        orders.add(Order.of("James", 3));
        orders.add(Order.of("Conrad", 4));
        orders.add(Order.of("Monica", 2));
        List<CompletableFuture<Boolean>> futures = orders.stream()
                .map(order -> CompletableFuture
                        .runAsync(() -> store.buy(order.getOrderQuantity()))
                        .thenApply(result -> true)
                        .exceptionally(exception -> {
                            Throwable cause = exception instanceof CompletionException
                                    ? exception.getCause()
                                    : exception;
                            if (cause instanceof InsufficientStockException) {
                                System.out.println(
                                        order.getName() + " - Not enough laptops in stock"
                                );
                            } else if (cause instanceof IllegalArgumentException) {
                                System.out.println(
                                        order.getName() + " - Invalid quantity"
                                );
                            } else {
                                System.err.println(
                                        order.getName() + " - Unexpected error: "
                                                + cause.getMessage()
                                );
                            }
                            return false;
                        })
                )
                .toList();
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        for (int i = 0; i < orders.size(); i++) {
            Order currentOrder = orders.get(i);
            boolean success = futures.get(i).join();
            if (success) {
                System.out.println(currentOrder.getName() + " - Successful purchase: " + currentOrder.getOrderQuantity()
                );
            }
        }
        System.out.println("\nNumber of laptops sold: " + store.getSoldQuantity());
        System.out.println("\nCurrent stock status for laptops: " + store.getAvailableQuantity());
    }
}
