package com.example.demo.repository;

import com.example.demo.model.ScratchCard;

public interface ScratchCardRepository extends Crud<ScratchCard, Long> {
    ScratchCard findByNumberAndValid(String cardNumber, Boolean valid);
}
