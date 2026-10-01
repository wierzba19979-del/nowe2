package pd5;

import lombok.Getter;
import lombok.Setter;

public class User implements Enity<Long> {
    private Long id;
    @Getter
    @Setter
    private String name;

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public Long getID() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
