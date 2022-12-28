package com.project.javatesttask.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import static java.util.concurrent.TimeUnit.MINUTES;

@Service
public class SchedulingService {

    private final CryptocurrencyService cryptocurrencyService;

    public SchedulingService(CryptocurrencyService cryptocurrencyService) {
        this.cryptocurrencyService = cryptocurrencyService;
    }

    @Scheduled(fixedRate = 30L, timeUnit = MINUTES)
    public void refreshLastBTCPrice() {
        cryptocurrencyService.addLastBTCPrice();
    }

    @Scheduled(fixedRate = 30L, timeUnit = MINUTES)
    public void refreshLastETHPrice() {
        cryptocurrencyService.addLastETHCPrice();
    }

    @Scheduled(fixedRate = 30L, timeUnit = MINUTES)
    public void refreshLastXRPPrice() {
        cryptocurrencyService.addLastXRPPrice();
    }


}
