package com.example.demo.repository.impl;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Objects;

public class UserRepositoryImpl extends CrudImpl<User, Long> implements UserRepository {
    private static UserRepositoryImpl instance;

    private UserRepositoryImpl() {
        super();
    }

    public static synchronized UserRepositoryImpl getInstance() {
        return Objects.requireNonNullElseGet(instance,
                () -> {
                    instance = new UserRepositoryImpl();
                    return instance;
                });
    }

    @Override
    public User findByMailAndPassword(String email, String password) throws NoResultException {
        return (User) getEntityManager().createNamedQuery("User.findByEmailAndPassword")
                .setParameter("email", email)
                .setParameter("password", password).getSingleResult();
    }

    @Override
    public User findByEmail(String email) throws NoResultException {
        return (User) getEntityManager().createNamedQuery("User.findByEmail")
                .setParameter("email", email).getSingleResult();
    }

    @Override
    public Boolean checkBalanceGreaterThan(User user, Double balance) {
        Double currentUserBalance = (Double) getEntityManager().createNamedQuery("User.getUserBalance")
                .setParameter("id", user.getUserId()).getSingleResult();
        return currentUserBalance >= balance;
    }

    @Override
    public Double addUserBalance(User user, Double balance) {
        getEntityManager().getTransaction().begin();
        int executeUpdate = getEntityManager().createNamedQuery("User.updateUserBalance")
                .setParameter("balance", balance)
                .setParameter("id", user.getUserId()).executeUpdate();
        getEntityManager().getTransaction().commit();
        if (executeUpdate > 0) {
            user.setBalance(balance);
            return balance;
        }
        return -1d;
    }

    @Override
    public List<User> findALlAdminUsers() {
        return (List<User>) getEntityManager().createNamedQuery("User.getAllAdminUsers").getResultList();
    }

    @Override
    public List<User> findALlCustomerUsers() {
        return (List<User>) getEntityManager().createNamedQuery("User.getAllCustomerUsers").getResultList();
    }

    @Override
    public User updateUserRole(String email, Role role) {
        User user = findByEmail(email);
        user.setRole(role);
        return update(user);
//        getEntityManager().getTransaction().begin();
//        int executeUpdate = getEntityManager().createNamedQuery("User.updateUserRole")
//                .setParameter("email", email)
//                .setParameter("role", role)
//                .executeUpdate();
//        getEntityManager().getTransaction().commit();
//        if (executeUpdate > 0) {
//            user = findByEmail(email);
//        }
//
//        return user;
    }

//    @Override
//    public User findById(Long id) {
//        return (User) getEntityManager().createNamedQuery("User.findById")
//                .setParameter("userId", id).getSingleResult();
//    }
}
