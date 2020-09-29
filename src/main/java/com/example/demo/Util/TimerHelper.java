package com.example.demo.Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimerHelper {



    public String GetNowyyyyMMddHHmmss()
    {
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowdate = sdf2.format(new Date());
        return nowdate;
    }

    public String GetTimerKey()
    {
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmss");
        String nowdate = sdf2.format(new Date());
        return nowdate;
    }

}
