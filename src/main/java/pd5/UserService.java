package pd5;

import java.util.Optional;

public class UserService {
    private final EnityStorage<User, Long> userStorage;

    public UserService(EnityStorage<User, Long> userStorage) {
        this.userStorage = userStorage;
    }

    public void renameUser(Long userId, String newName){
        Optional<User> user = userStorage.findById(userId);
        user.ifPresent(user1 -> user1.setName(newName));
    }
}
