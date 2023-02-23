package com.example.REST_API.dtos;

import com.example.REST_API.entities.Wallet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest
{
    private long userId;
    private String name;
    private String surname;
    private List<Wallet> wallets = new ArrayList<Wallet>();
}
