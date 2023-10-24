package com.enviro.assessment.grd001.nhlahlamathye.services;

import com.enviro.assessment.grd001.nhlahlamathye.repositories.investorRepository;
import com.enviro.assessment.grd001.nhlahlamathye.data.investor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class investorService {


    private final investorRepository.InvestorRepository investorRepository;

    @Autowired
    public investorService(investorRepository.InvestorRepository investorRepository) {
        this.investorRepository = investorRepository;
    }

    public List<investor> getAllInvestors(){
        return investorRepository.findAll();
    }

    public Optional<investor> getInvestorById(Long investorId) {
        return investorRepository.findById(investorId);
    }
}
