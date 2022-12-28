package com.project.javatesttask.controller;

import com.project.javatesttask.model.InternalCurrencyResponse;
import com.project.javatesttask.service.CryptocurrencyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cryptocurrencies")
public class CryptocurrencyController {

    private final CryptocurrencyService cryptocurrencyService;

    public CryptocurrencyController(CryptocurrencyService cryptocurrencyService) {
        this.cryptocurrencyService = cryptocurrencyService;
    }

    @PostMapping("/prices/BTC")
    public void addLastBTCPrice() {
        cryptocurrencyService.addLastBTCPrice();
    }

    @PostMapping("/prices/ETH")
    public void addLastETHPrice() {
        cryptocurrencyService.addLastETHCPrice();
    }

    @PostMapping("/prices/XRP")
    public void addLastXRPPrice() {
        cryptocurrencyService.addLastXRPPrice();
    }

    @GetMapping("/minprice")
    public InternalCurrencyResponse getCryptocurrencyWithLowestPrice(@RequestParam String name) {
        return cryptocurrencyService.getCryptocurrencyWithLowestPrice(name);
    }

    @GetMapping("/maxprice")
    public InternalCurrencyResponse getCryptocurrencyWithHighestPrice(@RequestParam String name) {
        return cryptocurrencyService.getCryptocurrencyWithHighestPrice(name);
    }

    @GetMapping()
    public List<InternalCurrencyResponse> getCryptocurrencyRecords(@RequestParam String name,
                                                                   @RequestParam(required = false) Integer page,
                                                                   @RequestParam(required = false) Integer size) {
        return cryptocurrencyService.getCryptocurrencyRecords(name, page, size);
    }

}
