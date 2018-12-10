package com.cookie.wash.service;

import com.cookie.wash.domian.Account;
import com.cookie.wash.enums.ReturnInfoEnum;
import com.cookie.wash.exceptions.ResultException;
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
public class ColthesService extends BaseDictDaoService<Account> {

    protected ColthesService(IDBPool pool) {
        super(pool);
    }


    public int addColor(String name) throws ResultException {
        String uuid = UUIDUtils.getUUID();
        Map<String,String> map = getDao().rawQueryForMap("select * from color where name = ? ",new String[]{name});
        if (map != null ){
            throw  new ResultException(ReturnInfoEnum.Repetition);
        }

        return  getDao().execute("insert into color (uuid , name ) values (?,?)", new String[]{uuid,name});
    }

    public Map<String,Object> getColors (String keyword , int pageNumber , int pageSize ){
        Map<String,Object> result = new HashMap<>();
        String limit = UUIDUtils.getLimit(pageNumber, pageSize);
        StringBuffer sb = new StringBuffer();
        List<String> params = new ArrayList<>();
        sb.append("select c.uuid , c.name " +
                "   from color c " +
                "   where 1 = 1 ");
        if (StringUtils.isNotBlank(keyword)){
            sb.append(" and c.name like ? ");
            params.add(keyword);
        }
        int count = getDao().rawQuery(sb.toString(),params.toArray(new String[]{})).size();
        sb.append(" order by c.add_time asc "+limit );
        List<Map<String,String>> color = getDao().rawQuery(sb.toString(),params.toArray(new String[]{}));

        result.put("count",count);
        result.put("data",color);

        return  result;

    }


    public int addColthes(String name) throws ResultException {
        String uuid = UUIDUtils.getUUID();
        Map<String,String> map = getDao().rawQueryForMap("select * from clothes where name = ? ",new String[]{name});
        if (map != null ){
            throw  new ResultException(ReturnInfoEnum.Repetition);
        }

        return  getDao().execute("insert into clothes (uuid , name ) values (?,?)", new String[]{uuid,name});
    }

    public Map<String,Object> getClothes (String keyword , int pageNumber , int pageSize ){
        Map<String,Object> result = new HashMap<>();
        String limit = UUIDUtils.getLimit(pageNumber, pageSize);
        StringBuffer sb = new StringBuffer();
        List<String> params = new ArrayList<>();
        sb.append("select c.uuid , c.name " +
                "   from clothes c " +
                "   where 1 = 1 ");
        if (StringUtils.isNotBlank(keyword)){
            sb.append(" and a.name like ? ");
            params.add(keyword);
        }
        int count = getDao().rawQuery(sb.toString(),params.toArray(new String[]{})).size();
        sb.append(" order by c.add_time asc "+limit );
        List<Map<String,String>> clothes = getDao().rawQuery(sb.toString(),params.toArray(new String[]{}));

        result.put("count",count);
        result.put("data",clothes);

        return  result;

    }

}
