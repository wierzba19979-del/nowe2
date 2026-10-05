package pd5.service;

import lombok.RequiredArgsConstructor;
import pd5.model.User;
import pd5.repository.Repository;

import java.util.Optional;
@RequiredArgsConstructor
public class UserService {
    private final Repository<User, Long> userStorage;

    public void renameUser(Long userId, String newName){
        Optional<User> user = userStorage.findById(userId);
        user.ifPresent(user1 -> user1.setName(newName));
    }
}
