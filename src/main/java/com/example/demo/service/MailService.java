package com.example.demo.service;

import com.example.demo.model.ScratchCard;
import com.example.demo.model.dto.UserDto;

public interface MailService {

    boolean sendScratchCardMail(UserDto user, ScratchCard scratchCard);

    boolean sendForgetPasswordMail(UserDto user, String randomPassword);
}
