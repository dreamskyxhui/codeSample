package com.share.codesample.service.functionSample;

public class UserSampleFunction2 {
    private static Integer MAX_NAME_LEN = 3; //姓名最大长度

    private static Integer MIN_AGE = 0; //最小年龄
    private static Integer MAX_AGE = 130; //最大年龄
    private static Integer UNKNOWN_AGE = -1; //未知年龄

    private static Integer MAN_SEX_CODE = 1; //男性
    private static Integer FEMALE_SEX_CODE = 2; //女性
    private static Integer UNKNOWN_SEX_CODE = -1; //未知性别

    /**
     * 姓名规范化；过滤前后空格，~等非法字符；并限制最大长度
     * @param str 待规范化的姓名
     * @return 格式化后的姓名
     */
    public static String normalizeName(String str) {
        return str.replace("~", "").trim().substring(0,MAX_NAME_LEN);
    }

    /**
     * 比较姓名是否不一致
     * @param srcName 输入名称
     * @param cmpName 待比对的名称
     * @return 不一致返回true，一致返回false
     */
    public static Boolean isDiffName(String srcName, String cmpName) {
        if (null == srcName || srcName.isEmpty()) {
            return false;
        }

        if (null == cmpName || cmpName.isEmpty()) {
            return false;
        }

        srcName = normalizeName(srcName);
        cmpName = normalizeName(cmpName);

        if (srcName.equals(cmpName)) {
            return false;
        }

        return true;
    }

    /**
     * 性别规范化；当性别非法时返回未知性别
     * @param sex 待规范化的性别
     * @return 已经规范化的性别
     */
    public static Integer normalizeSex(Integer sex) {
        if (sex != MAN_SEX_CODE && sex != FEMALE_SEX_CODE) {
            return UNKNOWN_SEX_CODE;
        }

        return sex;
    }

    /**
     * 比较性别是否不一致
     * @param srcSex 输入名称
     * @param cmpSex 待比对的名称
     * @return 不一致返回true，一致返回false
     */
    public static Boolean isDiffSex(Integer srcSex, Integer cmpSex) {
        if (null == srcSex) {
            return false;
        }

        if (null == cmpSex) {
            return false;
        }

        srcSex = normalizeSex(srcSex);
        if (srcSex.equals(UNKNOWN_SEX_CODE)) {
            return false;
        }

        cmpSex = normalizeSex(cmpSex);
        if (cmpSex.equals(UNKNOWN_SEX_CODE)) {
            return false;
        }

        if (srcSex.equals(cmpSex)) {
            return false;
        }

        return true;
    }

    /**
     * 年龄规范化；当年龄非法时返回未知年龄值
     * @param age 待规范化的年龄
     * @return 已经规范化的年龄
     */
    public static Integer normalizeAge(Integer age) {
        if (age < MIN_AGE) {
            return UNKNOWN_AGE;
        }

        if (age > MAX_AGE) {
            return UNKNOWN_AGE;
        }

        return age;
    }

    /**
     * 比较年龄是否不一致
     * @param srcAge 输入名称
     * @param cmpAge 待比对的名称
     * @return 不一致返回true，一致返回false
     */
    public static Boolean isDiffAge(Integer srcAge, Integer cmpAge) {
        if (null == srcAge) {
            return false;
        }

        if (null == cmpAge) {
            return false;
        }

        srcAge = normalizeAge(srcAge);
        if (srcAge.equals(UNKNOWN_AGE)) {
            return false;
        }

        cmpAge = normalizeAge(cmpAge);
        if (cmpAge.equals(UNKNOWN_AGE)) {
            return false;
        }

        if (srcAge.equals(cmpAge)) {
            return false;
        }

        return true;
    }

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

        Boolean isSameUser = false;

        //判断是否是同一个人
        if (aUserIdNo.equals(bUserIdNo) || aUserMobile.equals(bUserMobile)) {
            if (!isDiffName(aUserName, bUserName)
                    && !isDiffSex(aUserSex, bUserSex)
                    && !isDiffAge(aUserAge, bUserAge)) {
                isSameUser = true;
            }
        }

        System.out.println("A用户和B用户是同一个人:" + isSameUser);
    }

}
