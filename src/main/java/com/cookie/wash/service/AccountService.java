package com.cookie.wash.service;

import com.cookie.wash.domian.Account;
import com.cookie.wash.utils.UUIDUtils;
import com.fantasi.common.db.IDBPool;
import com.fantasi.common.db.service.BaseDictDaoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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


    public Map<String,Object> getAccounts(String keyword , int order , int orderType ,  int pageNumber , int pageSize ){
        Map<String,Object> result = new HashMap<>();
        String limit = UUIDUtils.getLimit(pageNumber, pageSize);
        StringBuffer sb = new StringBuffer();
        List<String> params = new ArrayList<>();
        sb.append("select c.uuid , c.name , c.money ,phone,sex , last_recharge_time,last_consume_time" +
                "   from account c " +
                "   where 1 = 1 ");
        if (StringUtils.isNotBlank(keyword)){
            sb.append(" and c.name like ? ");
            params.add(keyword);
        }
        int count = pageNumber == 1 ? getDao().rawQuery(sb.toString(),params.toArray(new String[]{})).size() : 0 ;
        switch (order){
            case 1 :
                sb.append(" order by c.add_time  " );
                break;

            case  2 :
                sb.append(" order by c.last_recharge_time  " );
                break;
            case  3 :
                sb.append(" order by c.last_consume_time  " );
                break;
            case  4 :
                sb.append(" order by c.money  " );
                break;
        }
        sb.append(orderType == 1 ? " asc " : " desc ");
        sb.append(limit );
        List<Map<String,String>> accounts = getDao().rawQuery(sb.toString(),params.toArray(new String[]{}));

        result.put("count",count);
        result.put("datas",accounts);

        return  result ;
    }

    public Map<String,Object> getAccountDetailByUuid(String uuid , int pageNumber , int pageSize ){
        Map<String,Object> result = new HashMap<>();
        Map<String,String> account = getDao().rawQueryForMap("select a.uuid , a.money , a.sex , a.phone , " +
                "   a.last_recharge_time , a.last_consume_time " +
                "   from account a " +
                "   where uuid = ? ", new String[]{uuid});
        if (account != null ){
            // TODO 消费记录
            String limit = UUIDUtils.getLimit(pageNumber, pageSize);
            StringBuffer sb = new StringBuffer();
            sb.append("select uuid , account_uuid , money ,indent_num,indent_status,status ,add_time " +
                    "   from consume_record " +
                    "   where account_uuid = ? ");
            int count = pageNumber == 1 ? getDao().rawQuery(sb.toString(), new String[]{uuid}).size() : 0 ;

            sb.append( " order by add_time desc "+limit );
            List<Map<String,String>> consumes = getDao().rawQuery(sb.toString(), new String[]{uuid});

            result.put("account",account);
            result.put("count",count);
            result.put("datas",consumes);

        }

        return  result ;
    }

    public Map<String,Object> getAccountCharges(String uuid , int pageNumber , int pageSize ){
        Map<String,Object> result = new HashMap<>();
        String limit = UUIDUtils.getLimit(pageNumber, pageSize);
        StringBuffer sb = new StringBuffer();
        sb.append("select uuid , account_uuid ,charge_money,present_money,money ,add_time " +
                "   from charge_record " +
                "   where account_uuid = ? ");
        int count = pageNumber == 1 ? getDao().rawQuery(sb.toString(), new String[]{uuid}).size() : 0 ;

        sb.append( " order by add_time desc "+limit );
        List<Map<String,String>> charges = getDao().rawQuery(sb.toString(), new String[]{uuid});

        result.put("count",count);
        result.put("datas",charges);

        return  result ;
    }


    public Map<String,String> getAccountByUuid (String uuid ){
        return  getDao().queryForMap("uuid = ? ", new String[]{uuid});
    }

}
