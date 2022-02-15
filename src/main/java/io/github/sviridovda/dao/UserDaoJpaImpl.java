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
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("SELECT u FROM User u");
        List<User> res = query.getResultList();
        transaction.commit();
        return res;
    }

    @Override
    public User getUserById(long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        User res = entityManager.find(User.class, id);
        transaction.commit();
        return res;
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
