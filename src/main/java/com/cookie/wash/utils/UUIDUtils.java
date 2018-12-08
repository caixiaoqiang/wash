package com.cookie.wash.utils;

import java.util.*;

/**
 * Created by Helena on 2017/6/22.
 */
public class UUIDUtils {

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static String getUpUUID(){
        return UUID.randomUUID().toString().toUpperCase().replaceAll("-","");
    }

    public static String getUuids(List<Map<String,String>> datas, String key){
        String uuids = "" ;
        if (datas != null && datas.size() > 0 ){
            Set<String> set = new HashSet<String>();
            for(Map<String,String> data : datas){
                if (data.containsKey(key)){
                    set.add(data.get(key));
                }
            }
            uuids = org.apache.commons.lang.StringUtils.join(set,",");
        }


        return uuids;
    }

    public static List<Map<String,String>> removeListId(List<Map<String,String>> datas){
        for (Map<String,String> data : datas){
            if (data.containsKey("id")){
                data.remove("id");
            }
        }

        return  datas;
    }

    public static String getLimit(int pageNumber ,int pageSize){
        if (pageNumber<1){
            pageNumber = 1 ;
        }
        String limit = "limit " + ((pageNumber - 1) * pageSize) + "," + pageSize;
        return  limit;
    }

    public static String arrayToString(String [] columns){
        String result = "";
        for (int i = 0 ; i < columns.length; i++){
            if (i!=columns.length-1){
                result += columns[i]+",";
            }else{
                result +=columns[i];
            }
        }
        return  result;

    }
    public static String[] stringToArray(String str){
        return   str.split(",");

    }
    
    
    public static List<String> getUuid(List<Map<String,String>> datas, String key){
    	List<String> list=new ArrayList<>();
        String uuids = "" ;
        int num=0;
        if (datas != null && datas.size() > 0 ){
            Set<String> set = new HashSet<String>();
            for(Map<String,String> data : datas){
                if (data.containsKey(key)){
                	 if(num>100) {
                		 uuids = org.apache.commons.lang.StringUtils.join(set,",");
                		 list.add(uuids);
                		 set.clear();
                		 uuids="";
                		 num=0;
                	 }
                	 num++;
                    set.add(data.get(key));
                }
               
            }
            uuids = org.apache.commons.lang.StringUtils.join(set,",");
            list.add(uuids);
        }

        return list;
    }

}
