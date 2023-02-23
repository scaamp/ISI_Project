package com.example.REST_API.dtos;

import com.example.REST_API.entities.User;
import com.example.REST_API.entities.Wallet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class WalletRequest {
    private long walletId;
    private String cryptoName;
    private long amount;
    private User user;
}
