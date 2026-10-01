package pd5;

import lombok.Getter;
import lombok.Setter;

public class Product implements Enity<String>{
    private String id;
    @Setter
    @Getter
    private String name;

    public Product(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getID() {
        return id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
