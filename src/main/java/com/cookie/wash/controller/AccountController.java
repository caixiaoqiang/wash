package com.cookie.wash.controller;

import com.cookie.wash.RedisLock;
import com.cookie.wash.domian.Account;
import com.cookie.wash.domian.vo.ConsumeList;
import com.cookie.wash.result.TResult;
import com.cookie.wash.service.AccountService;
import com.cookie.wash.service.Consumer;
import com.cookie.wash.service.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final static Logger logger = LoggerFactory.getLogger(AccountController.class);

    private static final int TIMOUT = 10 * 1000; //超时时间 10秒

    @Autowired
    private Producer producer ;

    @Autowired
    private Consumer consumer ;

    @Autowired
    private AccountService accountService ;

    @Autowired
    private RedisLock redisLock ;


    @GetMapping("lock")
    public TResult<Boolean> lock(){
        long time = System.currentTimeMillis() + TIMOUT;
        return  new TResult<>(redisLock.lock("aa", String.valueOf(time)));
    }


    @GetMapping("unlock")
    public TResult<Boolean> unlock(){
        redisLock.unlock("aa","1");
        return  new TResult<>(true);
    }




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
     * 收取衣物
     * @param accountUuid
     * @param consumeList
     * @return
     */
    @PostMapping("/consume")
    public TResult<Integer> addConsume(@RequestParam("account_uuid") String accountUuid ,
                                                        ConsumeList consumeList
    ){
        return  new TResult<>(accountService.addConsume(accountUuid,consumeList));
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


    /**
     * 充值
     * @param accountUuid
     * @param chargeMoney
     * @param presentMoney
     * @return
     */
    @PostMapping("charge")
    public TResult<Integer> addCharge(@RequestParam("uuid") String accountUuid ,
                                      @RequestParam("charge_money") Double chargeMoney ,
                                      @RequestParam("present_money") Double presentMoney){
        return  new TResult<>(accountService.addCharge(accountUuid,chargeMoney,presentMoney));
    }


    @PostMapping("mq")
    public TResult<Integer> mq() throws Exception {
        String queueName = "hello";
        producer.sendMsgConfirmSync(queueName," it is second  msg ! ");
        consumer.consumerMsg(queueName);
        return  new TResult<>(1);
    }



}
