package io.github.sviridovda.dao;

import io.github.sviridovda.models.User;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
@Component
public class UserDaoJpaImpl implements UserDao{
    private final EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public UserDaoJpaImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Override
    public List<User> getAllUsers() {
        Query query = entityManager.createQuery("SELECT u FROM User u");
        return query.getResultList();
    }

    @Override
    public User getUserById(long id) {
        return (User) entityManager.find(User.class, id);
    }

    @Override
    public void addUser(User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
    }

    @Override
    public void update(long id, User user) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        User userToUpdate = entityManager.find(User.class, id);
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setSurname(user.getSurname());
        userToUpdate.setName(user.getName());
        userToUpdate.setAge(user.getAge());
        transaction.commit();
    }

    @Override
    public void deleteUser(long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(entityManager.find(User.class, id));
        transaction.commit();
    }
}
