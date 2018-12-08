package com.cookie.wash.service;

import com.cookie.wash.domian.Account;
import com.fantasi.common.db.IDBPool;
import com.fantasi.common.db.service.BaseDictDaoService;
import org.springframework.stereotype.Service;

/**
 * 用户记录表
 * cxq
 */
@Service
public class AccountService extends BaseDictDaoService<Account> {

    protected AccountService(IDBPool pool) {
        super(pool);
    }

}
