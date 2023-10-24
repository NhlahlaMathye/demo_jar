package com.enviro.assessment.grd001.nhlahlamathye.controllers;

import com.enviro.assessment.grd001.nhlahlamathye.data.withdrawalNotice;
import com.enviro.assessment.grd001.nhlahlamathye.services.investorService;
import com.enviro.assessment.grd001.nhlahlamathye.data.investor;
import com.enviro.assessment.grd001.nhlahlamathye.services.withdrawalNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.opencsv.CSVWriter;

import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

public class investorController {

    @Autowired
    private investorService investorService;

    @GetMapping("/{investorId}")
    public ResponseEntity<String> getInvestorDetails(@PathVariable Long investorId) {
        Optional<investor> investor = investorService.getInvestorById(investorId);
        if (investor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Got Investors");
    }

    @GetMapping("/notices/csv")
    public ResponseEntity<String> downloadNoticesCSV(@RequestParam Long productId, @RequestParam String startDate,
                                                     @RequestParam String endDate)
            throws ChangeSetPersister.NotFoundException, ParseException {
        List<withdrawalNotice> notices = withdrawalNoticeService.getNoticesByDateRange(productId, startDate, endDate);

        if (notices.isEmpty()) {
            return ResponseEntity.badRequest().body("No withdrawal notices found in the specified date range.");
        }
        try {
            StringWriter stringWriter = new StringWriter();
            CSVWriter csvWriter = new CSVWriter(stringWriter);

            String[] header = { "ID", "Product ID", "Withdrawal Amount", "Withdrawal Date", "Status" };
            csvWriter.writeNext(header);

            for (withdrawalNotice notice : notices) {
                String[] data = {
                        notice.getId().toString(),
                        notice.getProductId().toString(),
                        String.valueOf(notice.getWithdrawalAmount()),
                        notice.getWithdrawalDate().toString(),
                        notice.getStatus()
                };
                csvWriter.writeNext(data);
            }
            csvWriter.close();

            byte[] bytes = stringWriter.toString().getBytes();
            ByteArrayResource resource = new ByteArrayResource(bytes);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("text/csv"))
                    .build();

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error generating CSV file.");
        }
    }
}
