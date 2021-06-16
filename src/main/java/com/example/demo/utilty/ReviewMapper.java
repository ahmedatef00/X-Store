package com.example.demo.utilty;

import com.example.demo.model.Review;
import com.example.demo.model.User;
import com.example.demo.model.dto.ReviewDto;

import java.time.format.DateTimeFormatter;

public class ReviewMapper {
    public static ReviewDto mapToReviewDto(Review review) {
        User user = review.getUser();
        String image = (user.getUserImage() != null
                ? "/iti-store/images?imageId=" + user.getUserImage().getImageId()
                : "images/user/avatar.jpg");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss");
        String formattedString = review.getReviewMessageTime().format(formatter);

        return new ReviewDto(review.getReviewMessage(),
                formattedString,
                review.getReviewStars(), user.getFirstName() + " " +
                user.getLastName(), image);
    }
}
