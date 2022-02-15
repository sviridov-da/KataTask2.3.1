package io.github.sviridovda.services;

import io.github.sviridovda.dao.UserDao;
import io.github.sviridovda.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component
public class UserServiceImpl implements UserService{

    @Autowired
    @Qualifier("userDaoListImpl")
    UserDao dao;

    @Override
    public List<User> getAllUsers() {
        return dao.getAllUsers();
    }

    @Override
    public User getUserById(long id) {
        return dao.getUserById(id);
    }

    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Override
    public void updateUser(long id, User user) {
        dao.update(id, user);
    }

    @Override
    public void deleteUser(long id) {
        dao.deleteUser(id);
    }
}
