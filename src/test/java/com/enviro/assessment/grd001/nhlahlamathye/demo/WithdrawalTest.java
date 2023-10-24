package com.enviro.assessment.grd001.nhlahlamathye.demo;

import com.enviro.assessment.grd001.nhlahlamathye.data.product;
import com.enviro.assessment.grd001.nhlahlamathye.data.withdrawalNotice;
import com.enviro.assessment.grd001.nhlahlamathye.repositories.productRepository;
import com.enviro.assessment.grd001.nhlahlamathye.repositories.withdrawalNoticeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class WithdrawalTest {

    @Mock
    private withdrawalNoticeRepository.WithdrawalNoticeRepository withdrawalNoticeRepository;

    @Mock
    private productRepository.ProductRepository productRepository;

    public WithdrawalTest() {
    }

    @Test
    public void testGetNoticesByDateRange() throws ChangeSetPersister.NotFoundException, ParseException {
        product product = new product();
        product.setId(1L);

        withdrawalNotice notice1 = new withdrawalNotice();
        notice1.setId(1L);
        notice1.setProductId(1L);
        notice1.setWithdrawalAmount(100.00);

        withdrawalNotice notice2 = new withdrawalNotice();
        notice2.setId(2L);
        notice2.setProductId(1L);
        notice2.setWithdrawalAmount(150.00);

        List<withdrawalNotice> notices = Arrays.asList(notice1, notice2);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String startDateStr = "2023-01-01";
        String endDateStr = "2023-03-31";

        Date startDate;
        Date endDate;
        try {
            startDate = dateFormat.parse(startDateStr);
            endDate = dateFormat.parse(endDateStr);
        } catch (ParseException e) {
            return;
        }

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        when(withdrawalNoticeRepository.findByProductAndWithdrawalDateBetween(
                product, startDate, endDate))
                .thenReturn(notices);

        List<withdrawalNotice> result = com.enviro.assessment.grd001.nhlahlamathye.services.withdrawalNoticeService.getNoticesByDateRange(1L, startDateStr, endDateStr);

        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());

        verify(productRepository, times(1)).findById(1L);
        verify(withdrawalNoticeRepository, times(1)).findByProductAndWithdrawalDateBetween(
                product, startDate, endDate);
    }
}

