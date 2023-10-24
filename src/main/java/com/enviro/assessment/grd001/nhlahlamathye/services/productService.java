package com.enviro.assessment.grd001.nhlahlamathye.services;

import com.enviro.assessment.grd001.nhlahlamathye.repositories.productRepository;
import com.enviro.assessment.grd001.nhlahlamathye.data.product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productService {

    private final productRepository.ProductRepository repository;

    @Autowired
    public productService(productRepository.ProductRepository productRepository){
        this.repository = productRepository;
    }

    public List<product> getAllProducts(){
        return repository.findAll();
    }

    public List<product> getProductById(Long productId)
    {
        return repository.findProductById(productId);
    }
}
