package com.share.codesample.service.classSample3;

public class Main {

    public static void main(String[] args) {
        String aUserName = "tom  ";
        Integer aUserSex = 1;
        Integer aUserAge = 18;
        String aUserMobile = "13522113573";
        String aUserIdNo = "621135xxx";

        String bUserName = "tom~ ";
        Integer bUserSex = 2;
        Integer bUserAge = 999;
        String bUserMobile = "13522113573";
        String bUserIdNo = "621135xxx";

        UserInfo srcUser = new UserInfo(aUserIdNo, aUserMobile, aUserName, aUserSex, aUserAge);
        UserInfo cmpUser = new UserInfo(bUserIdNo, bUserMobile, bUserName, bUserSex, bUserAge);

        SameUserCheck userChecker = new SameUserSimpleRuleCheck();
        //SameUserCheck userChecker = new SameUserMultiRuleCheck();
        Boolean cmpResult = userChecker.isSameUser(srcUser, cmpUser);


        System.out.println("A用户和B用户是同一个人:" + cmpResult);
    }
}
