package zl5;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ProductTest {
    public static void main(String[] args) {
        ArrayList<Product> product = new ArrayList<>();
        product.add(new Electronics("Pralka",2000,"AGD",12));
        product.add(new Electronics("Telewizor",5000,"AGD",24));
        product.add(new Electronics("Telefon",2000,"AGD",12));
        product.add(new FoodProduct("Pomidor",10,"Warzywo","12.12.2026"));
        product.add(new FoodProduct("Pomarańcza",5,"Owoc","11.12.2026"));

        for (Product product1 : product) {
            System.out.println(product1.getDescription());
        }
    }
}
