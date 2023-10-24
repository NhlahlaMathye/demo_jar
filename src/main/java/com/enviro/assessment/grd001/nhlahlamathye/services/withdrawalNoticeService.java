package com.enviro.assessment.grd001.nhlahlamathye.services;

import com.enviro.assessment.grd001.nhlahlamathye.data.product;
import com.enviro.assessment.grd001.nhlahlamathye.data.withdrawalNotice;
import com.enviro.assessment.grd001.nhlahlamathye.repositories.productRepository;
import com.enviro.assessment.grd001.nhlahlamathye.repositories.withdrawalNoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class withdrawalNoticeService {

    public static withdrawalNoticeRepository.WithdrawalNoticeRepository withdrawalNoticeRepository = null;

    public static productRepository.ProductRepository productRepository = null;

    @Autowired
    public withdrawalNoticeService(withdrawalNoticeRepository.WithdrawalNoticeRepository withdrawalNoticeRepository) {
        withdrawalNoticeService.withdrawalNoticeRepository = withdrawalNoticeRepository;
    }

    public static List<withdrawalNotice> getNoticesByDateRange(Long productId, String startDate, String endDate) throws ChangeSetPersister.NotFoundException, ParseException {

        Date startDateParsed = parseDate(startDate);
        Date endDateParsed = parseDate(endDate);

        product product = productRepository.findById(productId).orElse(null);
        if (product == null) {
            throw new ChangeSetPersister.NotFoundException();
        }
        return withdrawalNoticeRepository.findByProductAndWithdrawalDateBetween(product, startDateParsed, endDateParsed);
    }

    private static Date parseDate(String dateStr) throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new ParseException("Invalid date format. Please provide dates in yyyy-MM-dd format.", 0);
        }
    }
}
