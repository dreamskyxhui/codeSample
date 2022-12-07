package com.share.codesample.service;

import com.sun.org.apache.xpath.internal.operations.Bool;

public class userSample3 {

    public static void main(String[] args) {
        String aUserName = "tom";
        Integer aUserSex = 1;
        Integer aUserAge = 18;
        String aUserMobile = "13522113573";
        String aUserIdNo = "621135xxx";

        String bUserName = "tom";
        Integer bUserSex = null;
        Integer bUserAge = null;
        String bUserMobile = "13522113573";
        String bUserIdNo = "";

        Boolean isSameUser = false;

        if (aUserIdNo.equals(bUserIdNo) || aUserMobile.equals(bUserMobile)) {

            //判断姓名是否一致
            Boolean sameName = true;
            if (null != aUserName && !aUserName.isEmpty()
                && null != bUserName && !bUserName.isEmpty()
                && !aUserName.equals(bUserName)) {
                sameName = false;
            }

            //判断性别是否一致
            Boolean sameSex = true;
            if (null != aUserSex && null != bUserSex
                && !aUserSex.equals(bUserSex)) {
                sameSex = false;
            }

            //判断年龄是否一致
            Boolean sameAge = true;
            if (null != aUserAge && null != bUserAge
                    && !aUserAge.equals(bUserAge)) {
                sameAge = false;
            }

            //判断是否为同一个人
            if (sameName && sameSex && sameAge) {
                isSameUser = true;
            }
        }

        System.out.println("A用户和B用户是同一个人:" + isSameUser);
    }

}
