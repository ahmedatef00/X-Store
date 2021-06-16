package com.example.demo.repository;

import com.example.demo.model.ScratchCardRequest;
import com.example.demo.model.User;

import java.util.List;

public interface ScratchCardRequestRepository extends Crud<ScratchCardRequest, Long> {
    public List<ScratchCardRequest> getApprovedRequests(Boolean approved);

    public List<ScratchCardRequest> getApprovedRequestsByUser(User user, Boolean approved);
}
