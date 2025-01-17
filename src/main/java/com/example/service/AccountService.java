package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepo;
    int status = 200;

    public int getStatus(){
        return status;
    }


    public Account addAccount(Account account){
        if (account.getUsername().length() == 0 || account.getPassword().length() < 4) {
            status = 400;
            return null;
        }
        if(!getAccount(account)){
            status = 409;
            return null;
        }
        accountRepo.save(account);
        status = 200;
        return account;
    }

    public Account accountLogin(Account account){
        Account checkAccount = accountRepo.getAccountByUsername(account.getUsername());
        if (checkAccount == null) {
            status = 401;
            return null;
        }
        if(checkAccount.getPassword().equals(account.getPassword())){
            status = 200;
            return checkAccount;
        }
        status = 401;
        return null;
    }

    Boolean getAccount(Account account){
        if(accountRepo.getAccountByUsername(account.getUsername()) == null){
            return true;
        }
        return false;
    }

    Boolean checkAccount(int accountNum){
        if(accountRepo.existsById(accountNum)){
            return true;
        }
        return false;
    }


}
