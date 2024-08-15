package com.backend.cashrich.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.backend.cashrich.entity.Coin;

@Repository
public interface CoinRespoitory extends JpaRepository<Coin, Long>{

}
