package com.share.codesample.service;

public class userSampleFunction1 {

    /**
     * 比较两个有内容的字符串是否不一致；任意一个为空或null时不比较
     * @param srcStr 输入字符串
     * @param cmpStr 待比对的字符串
     * @return 不一致返回true，一致返回false
     */
    public static Boolean isDiffString(String srcStr, String cmpStr) {
        if (null == srcStr || srcStr.isEmpty()) {
            return false;
        }

        if (null == cmpStr || cmpStr.isEmpty()) {
            return false;
        }

        if (srcStr.equals(cmpStr)) {
            return false;
        }

        return true;
    }

    /**
     * 判断两个有数值的整型是否一致；任意一个为null时不比较
     * @param srcInt 输入的数值
     * @param cmpInt 待比较的数值
     * @return 不一致返回true，一致返回false
     */
    public static Boolean isDiffInteger(Integer srcInt, Integer cmpInt) {
        if (null == srcInt) {
            return false;
        }

        if (null == cmpInt) {
            return false;
        }

        if (srcInt.equals(cmpInt)) {
            return false;
        }

        return true;
    }

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
            if (!isDiffString(aUserName, bUserName)
                    && !isDiffInteger(aUserSex, bUserSex)
                    && !isDiffInteger(aUserAge, bUserAge)) {
                isSameUser = true;
            }
        }

        System.out.println("A用户和B用户是同一个人:" + isSameUser);
    }

}
