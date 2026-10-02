package pd5;

import lombok.RequiredArgsConstructor;

import java.util.Optional;
@RequiredArgsConstructor
public class UserService {
    private final Repository<User, Long> userStorage;

    public void renameUser(Long userId, String newName){
        Optional<User> user = userStorage.findById(userId);
        user.ifPresent(user1 -> user1.setName(newName));
    }
}
