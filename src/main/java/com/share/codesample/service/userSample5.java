package com.share.codesample.service;

public class userSample5 {
    private static Integer MAX_NAME_LEN = 3; //姓名最大长度
    private static Integer MIN_AGE = 0; //最小年龄
    private static Integer MAX_AGE = 130; //最大年龄
    private static Integer DEFAULT_AGE = -1; //年龄非法时候默认设置的年龄

    /**
     * 姓名规范化；过滤前后空格，~等非法字符；并限制最大长度
     * @param str 待规范化的姓名
     * @return 格式化后的姓名
     */
    public static String nameNormalization(String str) {
        return str.replace("~", "").trim().substring(0,MAX_NAME_LEN);
    }

    /**
     * 地址规范化；过滤前后空格，~等非法字符
     * @param str 待规范化的地址
     * @return 格式化后的地址
     */
    public static String addressNormalization(String str) {
        return str.replace("~", "").trim();
    }

    /**
     * 年龄规范化；当年龄小于0或者大于130时返回0
     * @param age 待规范化的年龄
     * @return 已经规范化的年龄
     */
    public static Integer ageNormalization(Integer age) {
        if (age < MIN_AGE) {
            return DEFAULT_AGE;
        }

        if (age > MAX_AGE) {
            return DEFAULT_AGE;
        }

        return age;
    }

    public static void main(String[] args) {
        String name = " tom ~aaa bb cc dd";
        String address = "~ guangdong shenzhen ";
        Integer age = 999;

        name = nameNormalization(name);
        address = addressNormalization(address);
        age = ageNormalization(age);

        System.out.println("name:" + name + ",address:" + address + ",age=" + age);
    }

}
