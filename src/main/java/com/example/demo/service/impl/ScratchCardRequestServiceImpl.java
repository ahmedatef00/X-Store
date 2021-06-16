package com.example.demo.service.impl;

import com.example.demo.model.ScratchCardRequest;
import com.example.demo.model.User;
import com.example.demo.repository.ScratchCardRequestRepository;
import com.example.demo.repository.impl.ScratchCardRequestRepositoryImpl;
import com.example.demo.service.ScratchCardRequestService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class ScratchCardRequestServiceImpl implements ScratchCardRequestService {
    ScratchCardRequestRepository scratchCardRequestRepository = ScratchCardRequestRepositoryImpl.getInstance();
    private static ScratchCardRequestServiceImpl instance;

    protected ScratchCardRequestServiceImpl() {
    }

    @Override
    public List<ScratchCardRequest> getApprovedRequests(Boolean approved) {
        return scratchCardRequestRepository.getApprovedRequests(approved);
    }

    @Override
    public List<ScratchCardRequest> getApprovedRequestsByUser(User user, Boolean approved) {
        return scratchCardRequestRepository.getApprovedRequestsByUser(user, approved);
    }


    // enter card number
    public ScratchCardRequest updateScratchCardRequest(ScratchCardRequest scratchCardRequest) {
        return scratchCardRequestRepository.update(scratchCardRequest);
    }

    @Override
    public ScratchCardRequest findById(Long id) {
        return scratchCardRequestRepository.findById(id);
    }

    @Override
    public boolean deleteScratchCardRequest(ScratchCardRequest scratchCardRequest) {
        scratchCardRequestRepository.delete(scratchCardRequest);
        return true;
    }

    @Override
    public Boolean requestBalance(User user, Double amount) {
        // request card
        ScratchCardRequest scratchCardRequest = new ScratchCardRequest();
        scratchCardRequest.setUser(user);
        scratchCardRequest.setApproved(false);
        scratchCardRequest.setAmount(amount);
        scratchCardRequest.setRequestDateAndTime(LocalDateTime.now());
        ScratchCardRequest save = scratchCardRequestRepository.save(scratchCardRequest);
        if (save.getScratchCardRequestId() > 0) {
            return true;
        }
        return false;
    }

    public static synchronized ScratchCardRequestServiceImpl getInstance() {
        return instance = Objects.requireNonNullElseGet(instance, ScratchCardRequestServiceImpl::new);
    }
}
