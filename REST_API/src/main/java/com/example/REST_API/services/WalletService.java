package com.example.REST_API.services;

import com.example.REST_API.dtos.WalletRequest;
import com.example.REST_API.dtos.WalletResponse;
import com.example.REST_API.entities.Wallet;

import java.util.List;

public interface WalletService {
    List<WalletResponse> getAllWallets();

    void addWallet(WalletRequest walletRequest);

    Wallet getWalletById(Long id);

    void updateWallet(Long id, Wallet wallet);

    void deleteWallet(Long id);
}
