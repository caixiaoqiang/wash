package com.cookie.wash.controller;

import com.cookie.wash.domian.Account;
import com.cookie.wash.result.TResult;
import com.cookie.wash.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService ;

    /**
     * 新增
     * @param account
     * @return
     */
    @PostMapping("")
    public TResult<Integer> add(Account account ){
        return  new TResult<>(accountService.save(account));
    }

    /**
     * 修改
     * @param account
     * @return
     */
    @PutMapping("")
    public TResult<Integer> update(Account account ){
        return  new TResult<>(accountService.update(account));
    }


    /**
     * 用户列表
     * @param keyword
     * @param order ： 排序类型 1--加入时间 2--上次充值时间 3--上次消费时间 4--账户余额
     * @param order_type    排序方式：1--正序 2--倒叙
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping("")
    public TResult<Map<String,Object>> getAccounts(@RequestParam("keyword") String keyword ,
                                    @RequestParam("order") int order ,
                                    @RequestParam("orderType") int order_type ,
                                    @RequestParam("pageNumber") int pageNumber ,
                                    @RequestParam("pageSize") int pageSize
                                        ){
        return  new TResult<>(accountService.getAccounts(keyword, order, order_type, pageNumber, pageSize));
    }

    /**
     * 用户消费清单
     * @param accountUuid
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping("/consume/{uuid}")
    public TResult<Map<String,Object>> getAccountConsumeByAccountUuid(@RequestParam("account_uuid") String accountUuid ,
                                    @RequestParam("pageNumber") int pageNumber ,
                                    @RequestParam("pageSize") int pageSize
                                        ){
        return  new TResult<>(accountService.getAccountConsumeByUuid(accountUuid, pageNumber, pageSize));
    }

    /**
     * 清单明细
     * @param indentNum
     * @return
     */
    @GetMapping("/consume/detail/{uuid}")
    public TResult<List<Map<String,String>>> getAccountConsumeDetailByIndentNum(@RequestParam("indent_num") String indentNum
    ){
        return  new TResult<>(accountService.getAccountConsumeDetailByIndentNum(indentNum));
    }


    /**
     * 充值清单
     * @param accountUuid
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping("/charge{uuid}")
    public TResult<Map<String,Object>> getAccountCharges(@RequestParam("uuid") String accountUuid ,
                                                               @RequestParam("pageNumber") int pageNumber ,
                                                               @RequestParam("pageSize") int pageSize
    ){
        return  new TResult<>(accountService.getAccountCharges(accountUuid, pageNumber, pageSize));
    }

}
