package com.cookie.wash.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by cxq on 2017/7/3.
 */
public class DateUtils {

    public static String  getAfterMonth(int month) {
        Calendar c = Calendar.getInstance();//获得一个日历的实例
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        c.setTime(date);//设置日历时间
        c.add(Calendar.MONTH,month);//在日历的月份上增加指定月
        String strDate = sdf.format(c.getTime());//的到你想要得指定月后的日期
        return strDate;

    }
    public static String  getAfterMonth(int month,String time) {
        Calendar c = Calendar.getInstance();//获得一个日历的实例
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try{
            date = sdf.parse(time);//初始日期
        }catch(Exception e){

        }
        c.setTime(date);//设置日历时间
        c.add(Calendar.MONTH,month);//在日历的月份上增加指定月
        String strDate = sdf.format(c.getTime());//的到你想要得指定月后的日期
        return strDate;

    }
    public static String  getAfterDay(int day) {

        Calendar calendar2 = Calendar.getInstance();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        calendar2.add(Calendar.DATE, day);
        String days_after = sdf2.format(calendar2.getTime());

        return days_after;

    }

    public static String getSpecifiedDayBefore(String specifiedDay){
        Calendar c = Calendar.getInstance();
        Date date=null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day-1);

        String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }

    public static String getSpecifiedDayBefore(String specifiedDay , int betweenDay){
        Calendar c = Calendar.getInstance();
        Date date=null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day-betweenDay);

        String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }


    public static String getSpecifiedDayAfter(String specifiedDay){
        Calendar c = Calendar.getInstance();
        Date date=null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day+1);

        String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }

    public static String getSpecifiedDayAfter(String specifiedDay , int betweenDay ){
        Calendar c = Calendar.getInstance();
        Date date=null;
        try {
            date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.setTime(date);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day+betweenDay);

        String dayBefore=new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }



    public static String getTimeToDate(String time){
        SimpleDateFormat sim = new SimpleDateFormat("yyyy-HH-DD");
        Date date = null;
        try {
            date = sim.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  sim.format(date);
    }

    public static int compareDate(String DATE1, String DATE2) {


        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }


    public static  String getCurrentTime (){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

    public static  String getCurrentDay (){
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }
    public static  String getCurrentDayString (){
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }


    public static String getSpecifiedMonthDayFromToday(int month){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        //过去一月
        c.setTime(new Date());
        c.add(Calendar.MONTH, month);
        Date m = c.getTime();
        String mon = format.format(m);
        return  mon ;
    }

    public static String getSpecifiedDayFromToday(int day){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        c.setTime(new Date());
        c.add(Calendar.DATE, day);
        Date d = c.getTime();
        return  format.format(d);
    }

    public static String getSpecifiedDayFromTodayString(int day){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        c.setTime(new Date());
        c.add(Calendar.DATE, day);
        Date d = c.getTime();
        return  format.format(d);
    }

    // 是通过计算两个日期相差的毫秒数来计算两个日期的天数差的。一样会有一个小问题，就是当他们相差是23个小时的时候，它就不算一天了。如下面的两个日期
    public static int differentDaysByMillisecond(Date date1,Date date2){
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }

    public static int differentDaysByMillisecond(String time1,String time2){
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(time1);
            date2 = new SimpleDateFormat("yyyy-MM-dd").parse(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }

    public static Long TimeDiffHour(String pBeginTime, String pEndTime) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Long beginL = format.parse(pBeginTime).getTime();
        Long endL = format.parse(pEndTime).getTime();
        Long hour = ((endL - beginL)%86400000)/3600000;
        return hour;
    }

    public static Long TimeDiffDay(String pBeginTime, String pEndTime) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Long beginL = format.parse(pBeginTime).getTime();
        Long endL = format.parse(pEndTime).getTime();
        Long day = (endL - beginL)/86400000;
        return day ;
    }


    public static int getHour() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    public static String getSpecifiedTimeMinutesFromNowString(int minutes ){
        Calendar now=Calendar.getInstance();
        now.add(Calendar.MINUTE,minutes);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:00:00");
        String dateStr=sdf.format(now.getTimeInMillis());
        return  dateStr ;
    }



}

