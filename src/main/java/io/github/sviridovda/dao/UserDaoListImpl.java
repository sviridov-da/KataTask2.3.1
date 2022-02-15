package io.github.sviridovda.dao;

import io.github.sviridovda.models.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoListImpl implements UserDao{
    private static int COUNT;
    List<User> users;
    {
        COUNT = 0;
        users = new ArrayList<>();
        users.add(new User((long) ++COUNT, "User1", "Surname1", (byte)11, "user1@mail.ru"));
        users.add(new User((long) ++COUNT, "User2", "Surname2", (byte)22, "user2@mail.ru"));
        users.add(new User((long) ++COUNT, "User3", "Surname3", (byte)33, "user3@mail.ru"));
        users.add(new User((long) ++COUNT, "User4", "Surname4", (byte)44, "user4@mail.ru"));
        users.add(new User((long) ++COUNT, "User5", "Surname5", (byte)55, "user5@mail.ru"));
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public User getUserById(long id) {
        return users.stream().filter(u->u.getId() == id).findAny().orElse(null);
    }

    @Override
    public void addUser(User user) {
        user.setId(++COUNT);
        users.add(user);
    }

    @Override
    public void update(long id, User user) {
        User userToUpdate = getUserById(id);
        userToUpdate.setName(user.getName());
        userToUpdate.setSurname(user.getSurname());
        userToUpdate.setAge(user.getAge());
        userToUpdate.setEmail(user.getEmail());
    }

    @Override
    public void deleteUser(long id) {
        users.removeIf(u->u.getId()==id);
    }
}
