package com.example.REST_API.controllers;

import com.example.REST_API.dtos.WalletRequest;
import com.example.REST_API.dtos.WalletResponse;
import com.example.REST_API.entities.Wallet;
import com.example.REST_API.services.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {
    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    @GetMapping
    public ResponseEntity<List<WalletResponse>> getWallets() {
        List<WalletResponse> wallets = walletService.getAllWallets();

        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<Wallet> getWallet(@PathVariable("id") Long id) {
        return new ResponseEntity (walletService.getWalletById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity addWallet(@RequestBody WalletRequest walletRequest) throws URISyntaxException {
        walletService.addWallet(walletRequest);
        ResponseEntity.created(new URI("/wallets/" + walletRequest.getWalletId())).body(walletRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping({"/{id}"})
    public ResponseEntity <Wallet> updateWallet(@PathVariable("id") Long id, @RequestBody Wallet wallet) {
        walletService.updateWallet(id, wallet);
        return new ResponseEntity<>(walletService.getWalletById(id), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}"})
    public ResponseEntity<Wallet> deleteWallet(@PathVariable("id") Long id) {
        walletService.deleteWallet(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
