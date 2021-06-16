package com.example.demo.service;

import com.example.demo.model.ScratchCard;

public interface ScratchCardService {
    ScratchCard checkScratchCardWithNumber(String cardNumber);

    ScratchCard updateScratchCard(ScratchCard scratchCard);

    ScratchCard create(Double amount);
}
