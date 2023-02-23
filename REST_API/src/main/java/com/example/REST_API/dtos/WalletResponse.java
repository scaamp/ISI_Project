package com.example.REST_API.dtos;

import com.example.REST_API.entities.User;
import com.example.REST_API.entities.Wallet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletResponse {
    private long walletId;
    private String cryptoName;
    private long amount;
    private User user;
}
