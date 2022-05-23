package com.ery.getir.studycase.controllers;


import com.ery.getir.studycase.services.StatisticsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistic")
public class StatisticsController {

    private final StatisticsService statisticsService;


    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }


    @GetMapping("/getMonthlyStatisticByCustomerId/{customerId}")
    public ResponseEntity<?> getMonthlyStatistic(@PathVariable String customerId) {
        return new ResponseEntity<>(statisticsService.getCustomerMonthlyStatisticByCustomerId(customerId), HttpStatus.OK);
    }
}
