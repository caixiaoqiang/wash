package com.cookie.wash.service;

import com.cookie.wash.domian.Account;
import com.cookie.wash.domian.vo.ConsumeBean;
import com.cookie.wash.domian.vo.ConsumeList;
import com.cookie.wash.utils.DateUtils;
import com.cookie.wash.utils.DoubleUtils;
import com.cookie.wash.utils.UUIDUtils;
import com.fantasi.common.db.IDBPool;
import com.fantasi.common.db.service.BaseDictDaoService;
import com.fantasi.common.db.service.TransactionException;
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
            sb.append(" or c.phone like ? ");
            params.add("%"+keyword+"%");
            params.add("%"+keyword+"%");
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

    public Map<String,Object> getAccountConsumeByUuid(String uuid , int pageNumber , int pageSize ){
        Map<String,Object> result = new HashMap<>();
        Map<String,String> account = getDao().rawQueryForMap("select a.uuid , a.money , a.sex , a.phone , " +
                "   a.last_recharge_time , a.last_consume_time " +
                "   from account a " +
                "   where uuid = ? ", new String[]{uuid});
        if (account != null ){
            // TODO 消费记录
            String limit = UUIDUtils.getLimit(pageNumber, pageSize);
            StringBuffer sb = new StringBuffer();
            sb.append("select uuid , account_uuid , money ,indent_num,indent_status,status ,add_time , goods_count" +
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

    public List<Map<String,String>> getAccountConsumeDetailByIndentNum(String indentNum ){
        String sql = "select a.clothes_money , a.discount , a.indent_status , a.add_time , a.status , " +
                "   d.name as color , b.len " +
                "   from consumer_record_children a " +
                "   left join clothes_order b on a.clothes_order_uuid = b.uuid " +
                "   left join clothes c on c.uuid = b.clothes_uuid " +
                "   left join color d on d.uuid = b.color_uuid " +
                "   where a.indent_num = ?　";
        return  getDao().rawQuery(sql , new String[]{indentNum}) ;
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

    public int addCharge(String accountUuid , Double chargeMoney , Double presentMoney ){
        int result = 0;
        Double money = DoubleUtils.get2Double(chargeMoney + presentMoney);
        try {
            result = this.execute(connection -> {

                int  num = getDao().execute(connection," update account set  money = money + ?  , last_recharge_time = ? " +
                        "   where uuid = ? ", new String[]{String.valueOf(money),DateUtils.getCurrentTime(),accountUuid});

                return  getDao().execute(" insert into charge_record (uuid , account_uuid , charge_money , present_money , money )" +
                        "   values (?,?,?,?,?)", new String[]{UUIDUtils.getUUID(),accountUuid, String.valueOf(chargeMoney), String.valueOf(presentMoney), String.valueOf(money)}) ;

            });
        } catch (TransactionException e) {
            e.printStackTrace();
        }

        return  result ;
    }


    public int addConsume(String accountUuid , ConsumeList consumeList ){
        int result = 0;
        try {
            result = this.execute(connection -> {

                return  0 ;
            });
        } catch (TransactionException e) {
            e.printStackTrace();
        }

        return  result ;
    }

    public Map<String,Object> getConsumes(String accountUuid , ConsumeList consumeList ){

        Map<String,String> consume = new HashMap<>();
        List<Map<String,String>> consumeChildren = new ArrayList<>();
        List<Map<String,String>> clothesOrders = new ArrayList<>();

        String consumeUuid = UUIDUtils.getUUID();
        String indentNum = UUIDUtils.getUUID();
        for (ConsumeBean consumeBean  : consumeList.getConsumeList() ){
            // color_uuid , colthes_uuid , clothes_money ,  discount , money , len
            Map<String,String> consumeChild  = new HashMap<>();
            Map<String,String> clothesOrder  = new HashMap<>();


        }


        return  null ;
    }


}
