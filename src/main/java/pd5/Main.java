package pd5;

import pd5.model.Product;
import pd5.model.User;
import pd5.repository.Repository;
import pd5.service.UserService;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Repository<User, Long> userStorage = new Repository<>();

        userStorage.save(new User(1L, "Adam", new Subscription(true, "abc5")));
        userStorage.save(new User(2L, "Ewa", new Subscription(true, "def10")));
        userStorage.save(new User(3L, "Kasia", new Subscription(false, "ghi0")));

        Optional<User> user = userStorage.findById(2L);
        System.out.println(user);

        System.out.println(getDiscountCode(userStorage.findById(1L).orElse(null)));
        System.out.println(getDiscountCode(userStorage.findById(2L).orElse(null)));
        System.out.println(getDiscountCode(userStorage.findById(3L).orElse(null)));

        userStorage.deleteById(1L);
        System.out.println(userStorage.findAll());

        UserService userService = new UserService(userStorage);
        userService.renameUser(2L, "Basia");
        System.out.println(userStorage.findById(2L));

        Repository<Product, String> productStorage = new Repository<>();

        productStorage.save(new Product("AAA", "Koszula"));
        productStorage.save(new Product("AAB", "Sweter"));
        productStorage.save(new Product("AAC", "Spodnie"));

        Optional<Product> product = productStorage.findById("AAB");
        System.out.println(product);

        productStorage.deleteById("AAA");
        System.out.println(productStorage.findAll());

        System.out.println(productStorage.findById("AAD"));
    }

    public static String getDiscountCode(User user) {
        return Optional.ofNullable(user)
                .map(User::getSubscription)
                .filter(Subscription::isActive)
                .map(Subscription::getDiscountCode)
                .map(code -> code.isBlank() ? "DEFAULT10" : code.toUpperCase())
                .orElse(null);
    }
}
