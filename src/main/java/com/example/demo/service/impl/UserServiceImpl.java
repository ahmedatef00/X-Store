package com.example.demo.service.impl;

import com.example.demo.exception.UserBalanceViolation;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.Address;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.dto.UserDto;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.impl.UserRepositoryImpl;
import com.example.demo.service.UserService;
import com.example.demo.utilty.UserMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    private static UserServiceImpl instance;
    private UserRepository userRepository;

    protected UserServiceImpl() {
        userRepository = UserRepositoryImpl.getInstance();
    }

    public static synchronized UserServiceImpl getInstance() {
        return instance = Objects.requireNonNullElseGet(instance, UserServiceImpl::new);
    }

    @Override
    public UserDto register(UserDto userDto, String password) {
        String hashpw = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = UserMapper.mapUser(userDto, hashpw);
        user = userRepository.update(user);
        return UserMapper.mapUser(user);
    }

    @Override
    public UserDto update(UserDto userDto, String password) {
        //hashing the password before store it in db
        String hashpw = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = UserMapper.mapUser(userDto, hashpw);
        user = userRepository.update(user);
        return UserMapper.mapUser(user);
    }

    @Override
    public UserDto updateUserRole(String email, Role role) {
        UserDto userDto = null;
        User user = userRepository.updateUserRole(email, role);
        if (user != null) {
            userDto = UserMapper.mapUser(user);
        }
        return userDto;
    }

    @Override
    public UserDto login(String email, String password) throws UserNotFoundException {
        UserDto userDto = null;
        try {
            User user = userRepository.findByEmail(email);
            boolean validPass = BCrypt.checkpw(password, user.getPassword());
            if (validPass) {
                userDto = UserMapper.mapUser(user);
            }

        } catch (NoResultException e) {
            e.printStackTrace();
            throw new UserNotFoundException(String.format("%s not found on the database or " +
                    "incorrect password", email));
        }
        return userDto;
    }

    @Override
    public Double addUserBalance(UserDto userDto, Double amount) throws UserBalanceViolation {
        Double newBalance = userDto.getBalance() + amount;
        if (newBalance < 0) {
            throw new UserBalanceViolation();
        }
        User user = userRepository.findById(userDto.getUserId());
        return userRepository.addUserBalance(user, newBalance);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<UserDto> collect = userRepository.findAll().stream().map(UserMapper::mapUser).collect(Collectors.toList());
        return collect;
    }

    @Override
    public UserDto findUserById(Long userId) throws UserNotFoundException {

        UserDto userDto = null;
        try {
            User user = userRepository.findById(userId);
            System.out.println("from repo" + user);
            if (user != null) {
                userDto = UserMapper.mapUser(user);
            }
        } catch (NoResultException e) {
            e.printStackTrace();
            throw new UserNotFoundException(String.format("%s not found on the database or " +
                    "incorrect id ", userId));
        }
        return userDto;
    }

    @Override
    public void checkAdminExistence() {
        List<User> allAdmins = userRepository.findALlAdminUsers();
        if (allAdmins.size() < 1) {
            String password = "admin";
            User user = new User();
            user.setFirstName("Default");
            user.setLastName("Admin");
            user.setEmail("admin@store.com");
            user.setPassword(password);
            user.setRole(Role.ADMIN_ROLE);
            user.setBirthDate(LocalDate.now());
            user.setBalance(0.0);
            user.setPhone("+ 02 353 556 56");
            user.setUserImage(null);

            Address address = new Address();
            address.setCountry("Egypt");
            address.setState("Cairo");
            address.setCity("6 October");
            address.setStreet("1st Street");
            address.setZipCode("12345");
            user.setAddress(address);
            UserDto userDto = UserMapper.mapUser(user);
            update(userDto, password);
        }
    }

    @Override
    public UserDto findByEmail(String email) throws NoResultException {
        UserDto userDto = null;
        try {
            User user = userRepository.findByEmail(email);
            System.out.println(user);
            if (user != null) {
                userDto = UserMapper.mapUser(user);
            }
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return userDto;
    }

    @Override
    public User findById(Long id) throws NoResultException {
        return userRepository.findById(id);
    }


}
