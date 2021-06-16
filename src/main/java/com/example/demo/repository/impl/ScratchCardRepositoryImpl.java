package com.example.demo.repository.impl;

import com.example.demo.model.ScratchCard;
import com.example.demo.repository.ScratchCardRepository;

import javax.persistence.NoResultException;
import java.util.Objects;

public class ScratchCardRepositoryImpl extends CrudImpl<ScratchCard, Long> implements ScratchCardRepository {
    private static ScratchCardRepositoryImpl instance;

    private ScratchCardRepositoryImpl() {
    }

    public static synchronized ScratchCardRepositoryImpl getInstance() {
        return instance = Objects.requireNonNullElseGet(instance, ScratchCardRepositoryImpl::new);
    }

    @Override
    public ScratchCard findByNumberAndValid(String cardNumber, Boolean valid) {
        ScratchCard card = null;
        try {
            card = (ScratchCard) getEntityManager()
                    .createNamedQuery("ScratchCard.findByNumberAndValid")
                    .setParameter("cardNumber", cardNumber)
                    .setParameter("valid", valid).getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return card;
    }
}

