package com.example.demo.service;

import com.example.demo.model.Image;

import javax.servlet.http.Part;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

public interface ImageService {
    Image findById(Long id);

    Set<Image> saveImage(String path, Collection<Part> parts) throws IOException;
}
