package com.enviro.assessment.grd001.nhlahlamathye.repositories;

import com.enviro.assessment.grd001.nhlahlamathye.data.investor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class investorRepository {

    public interface InvestorRepository extends JpaRepository<investor,Long >{

        List<investor> findAll();

        Optional<investor> findById(Long id);
    }
}
