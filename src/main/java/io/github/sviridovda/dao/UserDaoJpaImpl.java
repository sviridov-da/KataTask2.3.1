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

    public UserDaoJpaImpl(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public List<User> getAllUsers() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Query query = entityManager.createQuery("SELECT u FROM User u");
        List<User> res = query.getResultList();
        transaction.commit();
        entityManager.close();
        return res;
    }

    @Override
    public User getUserById(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        User res = entityManager.find(User.class, id);
        transaction.commit();
        entityManager.close();
        return res;
    }

    @Override
    public void addUser(User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();
        entityManager.close();
    }

    @Override
    public void update(long id, User user) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        User userToUpdate = entityManager.find(User.class, id);
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setSurname(user.getSurname());
        userToUpdate.setName(user.getName());
        userToUpdate.setAge(user.getAge());
        transaction.commit();
        entityManager.close();
    }

    @Override
    public void deleteUser(long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.remove(entityManager.find(User.class, id));
        transaction.commit();
        entityManager.close();
    }
}
