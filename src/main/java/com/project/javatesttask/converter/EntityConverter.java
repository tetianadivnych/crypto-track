package com.project.javatesttask.converter;

import com.project.javatesttask.entity.Cryptocurrency;
import com.project.javatesttask.model.InternalCurrencyResponse;


public class EntityConverter {

    public static InternalCurrencyResponse convertCurrency(Cryptocurrency cryptocurrency) {
        InternalCurrencyResponse response = new InternalCurrencyResponse();
        response.setName(cryptocurrency.getName());
        response.setId(cryptocurrency.getId());
        response.setCreatedAt(cryptocurrency.getCreatedAt());
        response.setLastPriceInUSD(cryptocurrency.getLastPriceInUSD());
        return response;
    }
}
