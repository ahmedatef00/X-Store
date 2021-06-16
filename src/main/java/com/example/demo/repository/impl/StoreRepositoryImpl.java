package com.example.demo.repository.impl;

import java.util.List;
import java.util.Objects;

import com.example.demo.model.Address;
import com.example.demo.model.Store;
import com.example.demo.repository.StoreRepository;

public class StoreRepositoryImpl extends CrudImpl<Store, Long> implements StoreRepository {

    private static StoreRepositoryImpl instance;

    private StoreRepositoryImpl() {
    }

    public static synchronized StoreRepositoryImpl getInstance() {
        instance = Objects.requireNonNullElseGet(instance, StoreRepositoryImpl::new);
        return instance;
    }

    @Override
    public Store getStoreInfo() {
        List<Store> stores = findAll();
        if (stores.size() > 1) {
            for (Store store : stores) {
                delete(store);
            }
            save(createNewStore());
        } else if(stores.size() < 1) {
            save(createNewStore());
        }
        return findAll().get(0);
    }

    @Override
    public void updateStoreInfo(Store updatedStore) {
        List<Store> stores = findAll();
        if (stores.size() > 1) {
            for (Store store : stores) {
                delete(store);
            }
            save(createNewStore());
        } else if(stores.size() < 1) {
            save(createNewStore());
        }
        updatedStore.setStoreId(findAll().get(0).getStoreId());
        save(updatedStore);
    }

    private Store createNewStore() {
        Store storeInstance = new Store();

        storeInstance.setStoreName("ITI Store");
        storeInstance.setDescription("This is where you can buy ITI souvniers to remember this place and the AMAZING people you've met.");
        storeInstance.setEmail("store@jets.iti.gov.eg");
        storeInstance.setPhoneNumber("+ 02 353 556 56");
        storeInstance.setFaxNumber("+ 02 353 556 56");
        
        Address address = new Address();
        address.setCountry("Egypt");
        address.setState("Cairo");
        address.setCity("6 October");
        address.setStreet("1st Street");
        address.setZipCode("12345");        
        storeInstance.setAddress(address);

        storeInstance.setFaceBook("https://www.facebook.com/");
        storeInstance.setTwitter("https://www.twitter.com/");
        storeInstance.setYoutube("https://www.youtube.com/");
        storeInstance.setLinkedin("https://www.linkedin.com/");
        storeInstance.setInstagram("https://www.instagram.com/");

        storeInstance.setLinksTitle("Useful Links");
        storeInstance.setLink1("Link One");
        storeInstance.setLinkAddress1("http://www.iti.gov.eg/");
        storeInstance.setLink2("Link Two");
        storeInstance.setLinkAddress2("http://www.iti.gov.eg/");
        storeInstance.setLink3("Link Three");
        storeInstance.setLinkAddress3("http://www.iti.gov.eg/");
        storeInstance.setLink4("Link Four");
        storeInstance.setLinkAddress4("http://www.iti.gov.eg/");

        storeInstance.setSliderTitle1("First slide label");
        storeInstance.setSliderSubTitle1("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        storeInstance.setSliderTitle2("Second slide label");
        storeInstance.setSliderSubTitle2("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        storeInstance.setSliderTitle3("Third slide label");
        storeInstance.setSliderSubTitle3("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        storeInstance.setSliderTitle4("Fourth slide label");
        storeInstance.setSliderSubTitle4("Lorem ipsum dolor sit amet, consectetur adipiscing elit.");

        return storeInstance;
    }
}
