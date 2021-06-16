package com.example.demo.repository.impl;

import com.example.demo.model.ScratchCardRequest;
import com.example.demo.model.User;
import com.example.demo.repository.ScratchCardRequestRepository;

import java.util.List;
import java.util.Objects;

public class ScratchCardRequestRepositoryImpl
        extends CrudImpl<ScratchCardRequest, Long> implements ScratchCardRequestRepository {
    private static ScratchCardRequestRepositoryImpl instance;

    private ScratchCardRequestRepositoryImpl() {
    }

    public static ScratchCardRequestRepositoryImpl getInstance() {
        return instance = Objects.requireNonNullElseGet(instance, ScratchCardRequestRepositoryImpl::new);
    }

    @Override
    public List<ScratchCardRequest> getApprovedRequests(Boolean approved) {
        return (List<ScratchCardRequest>) getEntityManager()
                .createNamedQuery("ScratchCardRequest.getApprovedRequests")
                .setParameter("approved", approved).getResultList();

    }

    @Override
    public List<ScratchCardRequest> getApprovedRequestsByUser(User user, Boolean approved) {
        return (List<ScratchCardRequest>) getEntityManager()
                .createNamedQuery("ScratchCardRequest.getApprovedRequestsByUser")
                .setParameter("approved", approved)
                .setParameter("user", user)
                .getResultList();
    }
}
