package com.example.fish.escpart1;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Li Xueqing on 15/3/2018.
 */

public class RegexTesting {

    @Test public void test(){
        String Pattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(Pattern);
        java.util.regex.Matcher m = p.matcher("xueqing_li@mymail.sutd.edu.sg");
    }

}
