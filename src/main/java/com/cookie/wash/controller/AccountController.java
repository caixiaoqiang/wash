package com.cookie.wash.controller;

import com.cookie.wash.domian.Account;
import com.cookie.wash.result.TResult;
import com.cookie.wash.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService ;

    @PostMapping("")
    public TResult<Integer> add(Account account ){
        return  new TResult<>(accountService.save(account));
    }
}
