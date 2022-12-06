package com.share.codesample.service;

public class userSample4 {

    /**
     * 字符串规范化；过滤前后空格，~等非法字符
     * @param str 待规范化的字符串
     * @return 格式化后的字符串
     */
    public static String normalizeString(String str) {
        return str.replace("~", "").trim();
    }

    /**
     * 年龄规范化；当年龄小于0或者大于130时返回0
     * @param age 待规范化的年龄
     * @return 已经规范化的年龄
     */
    public static Integer normalizeAge(Integer age) {
        if (age < 0) {
            return -1;
        }

        if (age > 130) {
            return -1;
        }

        return age;
    }

    public static void main(String[] args) {
        String name = " tom ~";
        String address = "~ guangdong shenzhen ";
        Integer age = 999;

        name = normalizeString(name);
        address = normalizeString(address);
        age = normalizeAge(age);

        System.out.println("name:" + name + ",address:" + address + ",age=" + age);
    }

}
