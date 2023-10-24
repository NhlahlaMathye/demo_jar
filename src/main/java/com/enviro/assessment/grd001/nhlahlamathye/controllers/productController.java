package com.enviro.assessment.grd001.nhlahlamathye.controllers;

import com.enviro.assessment.grd001.nhlahlamathye.data.withdrawalNotice;
import com.enviro.assessment.grd001.nhlahlamathye.data.withdrawalRequest;
import com.enviro.assessment.grd001.nhlahlamathye.services.productService;
import com.enviro.assessment.grd001.nhlahlamathye.data.investor;
import com.enviro.assessment.grd001.nhlahlamathye.data.product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@RestController
@RequestMapping("/api/products")
public class productController {

    @PostMapping("/withdraw")
    public ResponseEntity<String> makeWithdrawal(@RequestBody withdrawalRequest withdrawRequest){

        productService productService = null;
        investor investor = null;
        assert false;
        product product = (com.enviro.assessment.grd001.nhlahlamathye.data.product)
                productService.getProductById(withdrawRequest.getProductId());

        if(product == null){
            return ResponseEntity.badRequest().body("product not found");
        }

        if ("Retirement".equals(product.getProductType())){
            if (investor.getAge() <= 65){
                return ResponseEntity.badRequest().body("Investor must be above the age of 65 ");
            }
        }

        if (withdrawRequest.getAmount() <= 0){
            return ResponseEntity.badRequest().body("Enter a valid amount to be withdrawn");
        }

        if (withdrawRequest.getAmount() > product.getCurrentBalance()){
            return ResponseEntity.badRequest().body("Withdrawal amount exceeds the current balance available");
        }
        //check 90% limit for withdrawal
        double balance =  product.getCurrentBalance() * 0.9;
        if (withdrawRequest.getAmount() * 0.9 > 0 && balance > 0) {
            return ResponseEntity.badRequest().body("Withdrawal amount exceeds 90% of the current balance");
        }

        withdrawalNotice notice = new withdrawalNotice();
        notice.setProductId(withdrawRequest.getProductId());
        notice.setWithdrawalAmount(withdrawRequest.getAmount());
        notice.setWithdrawalDate(Date.from(Instant.from(LocalDate.now())));

        return ResponseEntity.ok("Withdrawal notice created successfully.");

    }
}
