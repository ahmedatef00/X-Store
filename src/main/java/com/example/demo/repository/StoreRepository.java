package com.example.demo.repository;

import com.example.demo.model.Store;

public interface StoreRepository extends Crud<Store, Long> {
    Store getStoreInfo();
    void updateStoreInfo(Store store);
}
