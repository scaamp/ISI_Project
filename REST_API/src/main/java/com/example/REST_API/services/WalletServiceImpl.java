package com.example.REST_API.services;

import com.example.REST_API.dtos.WalletRequest;
import com.example.REST_API.dtos.WalletResponse;
import com.example.REST_API.entities.Wallet;
import com.example.REST_API.repositories.UserRepository;
import com.example.REST_API.repositories.WalletRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Transactional
@Service
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;
    private UserRepository userRepository;
    private final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public List<WalletResponse> getAllWallets()
    {
        /*return StreamSupport.stream(walletRepository.findAll().spliterator(), false)
                .map(entity -> new WalletResponse(entity.getWallet_id(), entity.getName(), entity.getAge(), entity.getMovie_id().getMovie_id()))
                .collect(Collectors.toList());*/
        List<Wallet> wallets = walletRepository.findAll();
        List<WalletResponse> walletResponses = new ArrayList<>(wallets.size());

        for (Wallet w : wallets) {
            WalletResponse walletResponse = new WalletResponse(w.getWalletId(), w.getCryptoName(), w.getAmount(), w.getUserId()); //, a.getMovie());
            walletResponses.add(walletResponse);
        }

        LOG.info("getAll - number of wallets: " + walletResponses.size());
        return walletResponses;
    }

    @Override
    public void addWallet(WalletRequest walletRequest)
    {
        Wallet wallet = new Wallet();
        wallet.setWalletId(walletRequest.getWalletId());
        wallet.setCryptoName(walletRequest.getCryptoName());
        wallet.setAmount(walletRequest.getAmount());
        wallet.setUserId(walletRequest.getUser());

        walletRepository.save(wallet);

    }

    @Override
    public Wallet getWalletById(Long id) {
        return walletRepository.findById(id).get();
    }

    @Override
    public void updateWallet(Long id, Wallet walletArg) {
        Wallet wallet = walletRepository.findById(id).get();
        System.out.println(wallet.toString());
        wallet.setWalletId(walletArg.getWalletId());
        wallet.setCryptoName(walletArg.getCryptoName());
        wallet.setAmount(walletArg.getAmount());
        wallet.setUserId(walletArg.getUserId());
        walletRepository.save(wallet);


    }

    @Override
    public void deleteWallet(Long id) {
        walletRepository.deleteById(id);
    }
}
