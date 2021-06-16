package com.example.demo.service.impl;

import java.util.Objects;

import com.example.demo.model.Store;
import com.example.demo.repository.impl.StoreRepositoryImpl;
import com.example.demo.service.StoreService;

public class StoreServiceImpl implements StoreService {


    private static StoreServiceImpl instance;

    StoreRepositoryImpl storeRepository;

    protected StoreServiceImpl() {
        this.storeRepository = StoreRepositoryImpl.getInstance();
    }

    public static synchronized StoreServiceImpl getInstance() {
        instance = Objects.requireNonNullElseGet(instance, StoreServiceImpl::new);
        return instance;
    }

    @Override
    public Store getStoreInfo() {
        return storeRepository.getStoreInfo();
    }

    @Override
    public void updateStoreInfo(Store store) {
        storeRepository.updateStoreInfo(store);
    }
    
}
