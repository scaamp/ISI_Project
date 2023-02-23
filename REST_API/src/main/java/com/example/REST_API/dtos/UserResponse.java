package com.example.REST_API.dtos;
import com.example.REST_API.entities.Wallet;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse
{
    private long userId;
    private String name;
    private String surname;
    private List<Wallet> wallets = new ArrayList<Wallet>();

    public UserResponse(long id) {
    }
}

