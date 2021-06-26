package com.ldnhat.Utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeAgo {
    
    private static String handleTime(Long countTime){

        String result = "";

        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));

        //long countTime = time.getTime();
        Timestamp date = new Timestamp(System.currentTimeMillis());
        long currentTime = date.getTime();
        System.out.println(currentTime);

        long second = (currentTime - countTime);
        System.out.println("second "+second);
        int minute = Math.round((second / 60)/1000);
        System.out.println("minute"+minute);

        int hours = (int) (second % 60);//(int) ((second / (1000*60*60)) % 24);
        String hourFormat = String.format("%02d Hour", hours);
        System.out.println(hourFormat);
        System.out.println("hours "+hours);


        int months = Math.round(second / 2600640);

        if(second <= 60){
            if (second == 0){
                result = "now";
            }else{
                result = second + "s";
            }
        }else if(minute <= 60){
            result = minute+"m";
        }else if(hours <= 24){
            result = hours+"h";
        }else if(months <= 12){
            result = "s";
        }else{
            result = "sd";
        }
        return result;
    }

    static Pattern MENTION = Pattern.compile("(#+[a-zA-Z0-9(_)]{1,})", Pattern.CASE_INSENSITIVE);

    public static Boolean checkMention(String hashtag, Pattern regex){

        Matcher matcher = regex.matcher(hashtag);

        while (matcher.find()){
            if (!hashtag.substring(matcher.start(), matcher.end()).isEmpty()){
                return true;
            }
        }
        return false;
    }

    public static String getHashtag(String hashtag, Pattern regex){
        String s = "";
        Matcher matcher = regex.matcher(hashtag);

        while (matcher.find()){
            s = hashtag.substring(matcher.start(), matcher.end());
            System.out.println(matcher.start() + ":"+matcher.end());
        }
        return s;
    }

    public static void main(String[] args) throws ParseException {

//        SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd");
//        Date date3 = formatter3.parse("2021-06-30");
//        System.out.println("time 3 "+date3.getTime());

//        //1621517055000L
//        String time = TimeAgo.handleTime(1621089755000L);
//        Date d = new Date(1621089755000L);
//        System.out.println("date handle: "+d);
//        System.out.println(time);

        String has = "@LENHAT @SDSDS SDSFSD #CUOI";
        System.out.println(TimeAgo.getHashtag(has, MENTION));
        System.out.println(TimeAgo.checkMention(has, MENTION));
    }
}
