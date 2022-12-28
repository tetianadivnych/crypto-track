package com.project.javatesttask.repository;

import com.project.javatesttask.entity.Cryptocurrency;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.List;

public interface CurrencyRepository extends MongoRepository<Cryptocurrency, BigInteger> {

    List<Cryptocurrency> findByNameOrderByLastPriceInUSDDesc(String name);

    List<Cryptocurrency> findByNameOrderByLastPriceInUSDAsc(String name, Pageable pageable);
}
