package com.share.codesample.service;

public class userSample3 {

    public static void main(String[] args) {
        String name = " tom ~";
        String address = "~ guangdong shenzhen ";
        Integer age = 999;

        name = name.replace("~", "").trim();
        address = address.replace("~", "").trim();
        if (age > 130) {
            age = -1;
        }

        System.out.println("name:" + name + ",address:" + address + ",age=" + age);
    }

}
