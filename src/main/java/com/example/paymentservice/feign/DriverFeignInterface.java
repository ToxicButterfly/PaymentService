package com.example.paymentservice.feign;

import com.example.paymentservice.model.BankCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "${feign.client.driver.name}", path = "${feign.client.driver.path}", url = "${feign.client.driver.url}")
public interface DriverFeignInterface {

    @GetMapping("{id}/bank")
    public ResponseEntity<BankCard> getBankData(@PathVariable int id,  @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader);
}
