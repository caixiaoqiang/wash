package com.cookie.wash.service;

import com.cookie.wash.domian.Account;
import com.fantasi.common.db.IDBPool;
import com.fantasi.common.db.service.BaseDictDaoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 用户记录表
 * cxq
 */
@Service
public class AccountService extends BaseDictDaoService<Account> {

    protected AccountService(IDBPool pool) {
        super(pool);
    }


    public int update(Account account ){
        int num = 0 ;
        Map<String,String> map = getAccountByUuid(account.getUuid());
        if (map != null ){
            if (StringUtils.isNotBlank(account.getName())){
                map.put("name",account.getName());
            }
            if (StringUtils.isNotBlank(account.getPhone())){
                map.put("phone",account.getPhone());
            }

            if (StringUtils.isNotBlank(account.getStatus())){
                map.put("status",account.getStatus());
            }
            if (StringUtils.isNotBlank(account.getSex())){
                map.put("sex",account.getSex());
            }

            num = getDao().update(map);


        }

        return  num ;
    }

    public Map<String,String> getAccountByUuid (String uuid ){
        return  getDao().queryForMap("uuid = ? ", new String[]{uuid});
    }

}
