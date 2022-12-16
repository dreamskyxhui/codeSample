package com.share.codesample.service.classSample;

public class Main2 {


    public static void main(String[] args) {
        String aUserName = "tom  ";
        Integer aUserSex = 1;
        Integer aUserAge = 18;
        String aUserMobile = "13522113573";
        String aUserIdNo = "621135xxx";

        String bUserName = "tom~ ";
        Integer bUserSex = -1;
        Integer bUserAge = 999;
        String bUserMobile = "13522113573";
        String bUserIdNo = "";

        UserInfo srcUser = new UserInfo(aUserIdNo, aUserMobile, aUserName, aUserSex, aUserAge);
        UserInfo cmpUser = new UserInfo(bUserIdNo, bUserMobile, bUserName, bUserSex, bUserAge);
        Boolean cmpResult = srcUser.isSameUser(cmpUser);


        System.out.println("A用户和B用户是同一个人:" + cmpResult);
    }
}
