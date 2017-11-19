package com.yy.demo.util;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Description:
 * <pre>
 *     倒计时执行器
 * </pre>
 * NB.
 * Created by skyler on 2017/11/15 at 下午6:32
 */
public class CountDowner {

    /**
     * 指定 年 月 日 时 分 秒的倒计时
     * <span>
     * 注：倒计时基础方法
     * </span>
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minite
     * @param seconds
     */
    public void countDown(int year, int month, int day, int hour, int minite, int seconds) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day, hour, minite, seconds);
        // 返回自 1970年1月1日00:00:00 GMT 到指定时间的毫秒数
        long longTime = cal.getTimeInMillis();
        timerExecutor(longTime);
    }

    /**
     * 倒计时的天数
     * @param dayNum
     */
    public void countDownByDay(int dayNum) {

        Calendar cal = Calendar.getInstance();
        countDown(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH) + dayNum,
                cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
    }

    /**
     * 指定时间的毫秒数
     * @param scheduleTime
     */
     private void timerExecutor(long scheduleTime) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                // 返回自 1970年1月1日00:00:00 GMT 到当前时间的毫秒数
                long currentTime = System.currentTimeMillis();
                long distTime = scheduleTime - currentTime;
                // 一分钟秒数
                int secondsOfMin = 60;
                // 一小时秒数
                int secondsOfHour = 3600;
                // 一天秒数
                int secondsOfDay = secondsOfHour * 24;
                long day = (distTime / 1000) / secondsOfDay;
                long hour = ((distTime / 1000) - day * secondsOfDay) / secondsOfHour;
                long minutes = ((distTime / 1000) - day * secondsOfDay - hour * secondsOfHour) / secondsOfMin;
                long seconds = (distTime / 1000) - day * secondsOfDay - hour * secondsOfHour - minutes * secondsOfMin;
                System.out.println("活动倒计时" + day + " 天 " + hour + "小时 :" + minutes
                        + "分钟 :" + seconds + "秒");
            }
        }, 0, 1000);
    }

    public static void main(String[] args) {

        CountDowner countDowner = new CountDowner();
        countDowner.countDownByDay(1);
        //countDowner.countDown(2017, 10, 16, 0, 0, 0);
    }
}
