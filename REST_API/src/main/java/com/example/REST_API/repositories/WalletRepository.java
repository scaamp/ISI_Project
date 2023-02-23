package com.example.REST_API.repositories;

import com.example.REST_API.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository <Wallet, Long> {
}
