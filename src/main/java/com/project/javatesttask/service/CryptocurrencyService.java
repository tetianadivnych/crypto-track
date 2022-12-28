package com.project.javatesttask.service;

import com.project.javatesttask.converter.EntityConverter;
import com.project.javatesttask.entity.Cryptocurrency;
import com.project.javatesttask.exception.CryptocurrencyNotFoundException;
import com.project.javatesttask.exception.InvalidCurrencyException;
import com.project.javatesttask.model.ExternalCurrencyResponse;
import com.project.javatesttask.model.InternalCurrencyResponse;
import com.project.javatesttask.repository.CurrencyRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CryptocurrencyService {

    private final CurrencyRepository currencyRepository;
    private final RestTemplate restTemplate;

    public CryptocurrencyService(CurrencyRepository currencyRepository, RestTemplate restTemplate) {
        this.currencyRepository = currencyRepository;
        this.restTemplate = restTemplate;
    }

    public ExternalCurrencyResponse fetchLastBTCPrice() {
        ResponseEntity<ExternalCurrencyResponse> response = restTemplate.getForEntity("https://cex.io/api/last_price/BTC/USD", ExternalCurrencyResponse.class);
        return response.getBody();
    }

    public ExternalCurrencyResponse fetchLastETHPrice() {
        ResponseEntity<ExternalCurrencyResponse> response = restTemplate.getForEntity("https://cex.io/api/last_price/ETH/USD", ExternalCurrencyResponse.class);
        return response.getBody();
    }

    public ExternalCurrencyResponse fetchLastXRPPrice() {
        ResponseEntity<ExternalCurrencyResponse> response = restTemplate.getForEntity("https://cex.io/api/last_price/XRP/USD", ExternalCurrencyResponse.class);
        return response.getBody();
    }

    public void addLastBTCPrice() {
        ExternalCurrencyResponse response = fetchLastBTCPrice();
        addLastPrice(response);
    }

    public void addLastETHCPrice() {
        ExternalCurrencyResponse response = fetchLastETHPrice();
        addLastPrice(response);
    }

    public void addLastXRPPrice() {
        ExternalCurrencyResponse response = fetchLastXRPPrice();
        addLastPrice(response);
    }

    private void addLastPrice(ExternalCurrencyResponse response) {
        Cryptocurrency cryptocurrency = new Cryptocurrency();
        cryptocurrency.setName(response.getCurr1());
        String stringPrice = response.getLprice();
        BigDecimal bigDecimalPrice = new BigDecimal(stringPrice);
        cryptocurrency.setLastPriceInUSD(bigDecimalPrice);
        LocalDateTime timeNow = LocalDateTime.now();
        cryptocurrency.setCreatedAt(timeNow);
        currencyRepository.save(cryptocurrency);
    }

    public InternalCurrencyResponse getCryptocurrencyWithLowestPrice(String name) {
        return currencyRepository.findAll().stream()
                .filter(cryptocurrency -> cryptocurrency.getName().equals(name))
                .min((Comparator.comparing(Cryptocurrency::getLastPriceInUSD)))
                .map(cryptocurrency -> EntityConverter.convertCurrency(cryptocurrency))
                .orElseThrow(() -> new CryptocurrencyNotFoundException("Cryptocurrency with name " + name + " is not found"));
    }

    public InternalCurrencyResponse getCryptocurrencyWithHighestPrice(String name) {
        validateCurrencyName(name);
        return currencyRepository.findByNameOrderByLastPriceInUSDDesc(name)
                .stream()
                .findFirst()
                .map(cryptocurrency -> EntityConverter.convertCurrency(cryptocurrency))
                .orElseThrow(() -> new CryptocurrencyNotFoundException("Cryptocurrency with name " + name + " is not found"));
    }

    public List<InternalCurrencyResponse> getCryptocurrencyRecords(String name, Integer page, Integer size) {
        validateCurrencyName(name);
        Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 10);
        return currencyRepository.findByNameOrderByLastPriceInUSDAsc(name, pageable).stream()
                .map(cryptocurrency -> EntityConverter.convertCurrency(cryptocurrency))
                .collect(Collectors.toList());
    }

    private void validateCurrencyName(String name) {
        List<String> possibleCurrencies = List.of("BTC", "ETH", "XRP");
        if (!possibleCurrencies.contains(name)) {
            throw new InvalidCurrencyException("Currency " + name + " doesn't exist");
        }
    }
}
