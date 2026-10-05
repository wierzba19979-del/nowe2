package pd5;

import pd5.model.Product;
import pd5.model.User;
import pd5.repository.Repository;
import pd5.service.UserService;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Repository<User, Long> userStorage = new Repository<>();

        userStorage.save(new User(1L, "Adam"));
        userStorage.save(new User(2L, "Ewa"));
        userStorage.save(new User(3L, "Kasia"));

        Optional<User> user = userStorage.findById(2L);
        System.out.println(user);

        userStorage.deleteById(1L);
        System.out.println(userStorage.findAll());

        UserService userService = new UserService(userStorage);
        userService.renameUser(2L, "Basia");
        System.out.println(userStorage.findById(2L));

        Repository<Product, String> productStorage = new Repository<>();

        productStorage.save(new Product("AAA", "Koszula"));
        productStorage.save(new Product("AAB", "Sweter"));
        productStorage.save(new Product("AAC", "Spodnie"));

        Optional<Product> product =  productStorage.findById("AAB");
        System.out.println(product);

        productStorage.deleteById("AAA");
        System.out.println(productStorage.findAll());

        System.out.println(productStorage.findById("AAD"));
    }
}
