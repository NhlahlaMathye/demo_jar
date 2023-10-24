package com.enviro.assessment.grd001.nhlahlamathye.repositories;

import com.enviro.assessment.grd001.nhlahlamathye.data.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class productRepository {


    public interface ProductRepository extends JpaRepository<product, Long>{

        List<product> findAll();

        List<product> findProductById(Long productId);

        Optional<product> findById(Long productId);
    }
}
