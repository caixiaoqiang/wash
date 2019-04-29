package com.cookie.wash.thread;

/**
 * author : cxq
 * Date : 2019/4/23
 */
public class TicketSell extends  Thread {

    static int ticket = 20 ;

    static Object obj = "aa";

    public TicketSell(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (ticket > 0 ){
            synchronized (obj){
                if (ticket > 0 ){
                    System.out.println(this.getName() + "卖出了第"+ticket + "张票");
                    ticket -- ;
                }else {
                    System.out.println("票卖完了");
                }
            }
//            try {
//                sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

    public static void main(String[] args) {
        TicketSell t1 = new TicketSell("窗口1");
        TicketSell t2 = new TicketSell("窗口2");
        TicketSell t3 = new TicketSell("窗口3");

        t1.start();
        t2.start();
        t3.start();
    }
}
