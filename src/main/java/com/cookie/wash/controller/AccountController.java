package com.cookie.wash.controller;

import com.cookie.wash.domian.Account;
import com.cookie.wash.result.TResult;
import com.cookie.wash.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService ;

    @PostMapping("")
    public TResult<Integer> add(Account account ){
        return  new TResult<>(accountService.save(account));
    }


    @PutMapping("")
    public TResult<Integer> update(Account account ){
        return  new TResult<>(accountService.update(account));
    }



    @GetMapping("")
    public TResult<Map<String,Object>> getAccounts(@RequestParam("keyword") String keyword ,
                                    @RequestParam("order") int order ,
                                    @RequestParam("orderType") int order_type ,
                                    @RequestParam("pageNumber") int pageNumber ,
                                    @RequestParam("pageSize") int pageSize
                                        ){
        return  new TResult<>(accountService.getAccounts(keyword, order, order_type, pageNumber, pageSize));
    }

}
