package primitivelist;

import java.util.ArrayList;
import java.util.List;

class SimpleList {
    private List<User> users = new ArrayList<>();

    List<User> getUsers() {
        return users;
    }

    void addUser(User user) {
        users.add(user);
    }
}
