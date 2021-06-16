package com.example.demo.service;

import com.example.demo.exception.UserBalanceViolation;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.dto.UserDto;

import javax.persistence.NoResultException;
import java.util.List;

public interface UserService {
    /**
     * login to system
     *
     * @param email    user email to login with
     * @param password user password to login with
     * @return user if exists on the system
     */
    UserDto login(String email, String password) throws UserNotFoundException;

    UserDto register(UserDto userDto, String password);

    UserDto update(UserDto userDto, String password);

    UserDto updateUserRole(String email, Role role);

    Double addUserBalance(UserDto user, Double amount) throws UserBalanceViolation;

    List<UserDto> findAllUsers();

    UserDto findUserById(Long userId) throws UserNotFoundException;

    void checkAdminExistence();

    UserDto findByEmail(String email) throws NoResultException;

    User findById(Long id) throws NoResultException;

}
