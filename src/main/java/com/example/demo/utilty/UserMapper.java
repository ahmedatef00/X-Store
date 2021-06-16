package com.example.demo.utilty;

import com.example.demo.model.Image;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.model.dto.OrderDto;
import com.example.demo.model.dto.UserDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static UserDto mapUser(User user) {
        List<OrderDto> orders = new ArrayList<>(0);
        for (Order order : user.getOrders()) {
            orders.add(OrderMapper.mapOrder(order));
        }
        String image = (user.getUserImage() != null
                ? "/iti-store/images?imageId=" + user.getUserImage().getImageId()
                : "images/user/avatar.jpg");
        UserDto userDto = new UserDto(user.getUserId(), user.getFirstName(), user.getLastName(), user.getPhone(), user.getEmail(), user.getRole(), user.getBalance(),
                "" + user.getBirthDate(),
                image, user.getAddress(), orders);

        return userDto;
    }

    public static User mapUser(UserDto userDto, String password) {
        User user = mapUser(userDto);
        user.setPassword(password);
        return user;
    }

    public static User mapUser(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setBalance(userDto.getBalance());
        user.setAddress(userDto.getAddress());
        user.setBirthDate(LocalDate.parse(userDto.getBirthDate()));
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());

        user.setPhone(userDto.getPhone());
        user.setRole(userDto.getRole());
        if (userDto.getUserImage() != null) {
            Long imageId = getImageId(userDto.getUserImage());
            if (imageId != null) {
                Image image = new Image();
                image.setImageId(imageId);
                user.setUserImage(image);
            }
        }
        if (userDto.getOrders() != null && userDto.getOrders().size() > 0) {
            List<OrderDto> orders = userDto.getOrders();
            orders.forEach(orderDto -> {
                Order order = OrderMapper.mapOrder(orderDto);
                user.addOrder(order);
            });
        }
        System.out.println(user);
        return user;
    }

    private static Long getImageId(String imagePath) {
        if (imagePath.contains("?imageId=")) {
            String[] split = imagePath.split("imageId=");
            Long id = Long.parseLong(split[1]);
            return id;
        }
        return null;
    }
}
