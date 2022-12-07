package com.share.codesample.service;

public class userSample {

    public static void main(String[] args) {
        String aUserName = "tom";
        Integer aUserSex = 1;
        Integer aUserAge = 18;
        String aUserMobile = "13522113573";
        String aUserIdNo = "621135xxx";

        String bUserName = "jack";
        Integer bUserSex = 2;
        Integer bUserAge = 6;
        String bUserMobile = "13522113573";
        String bUserIdNo = "";

        Boolean isSameUser = false;
        if(aUserIdNo.equals(bUserIdNo) || aUserMobile.equals(bUserMobile)) {
            isSameUser = true;
        }

        System.out.println("A用户和B用户是同一个人:" + isSameUser);
    }
}



