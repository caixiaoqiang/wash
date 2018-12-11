package com.cookie.wash.utils;

import java.math.BigDecimal;

/**
 * author : cxq
 * Date : 2018/12/11
 */
public class DoubleUtils {

    /**
     * double 保留两位小数
     * @param price
     * @return
     */
    public static Double get2Double(Double price ){
        BigDecimal b = new BigDecimal(price);
        return  b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    public static void main(String[] args) {
//        Double a = 12.52D ;
//        Double b = 11.54D ;
        String a = "12.52";
        String b = "11.54";
        System.out.println(get2Double(Double.parseDouble(a) + Double.parseDouble(b) ));
    }
}
