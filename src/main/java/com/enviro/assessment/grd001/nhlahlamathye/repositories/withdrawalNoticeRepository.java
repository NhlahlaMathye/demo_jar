package com.enviro.assessment.grd001.nhlahlamathye.repositories;

import com.enviro.assessment.grd001.nhlahlamathye.data.product;
import com.enviro.assessment.grd001.nhlahlamathye.data.withdrawalNotice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class withdrawalNoticeRepository {


    public interface WithdrawalNoticeRepository extends JpaRepository<withdrawalNotice, Long>{

        List<withdrawalNotice> findByProductAndWithdrawalDateBetween(product product, Date startDate, Date endDate);

    }
}
