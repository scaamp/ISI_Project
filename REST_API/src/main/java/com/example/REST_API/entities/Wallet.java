package com.example.REST_API.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "wallets")
public class Wallet {
    @Id
    @GeneratedValue
    @Column(unique = true, nullable = false)
    private long walletId;
    private String cryptoName;
    private long amount;
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE})
    @JsonBackReference
    @JoinColumn(name = "userId")
    private User userId;
}
