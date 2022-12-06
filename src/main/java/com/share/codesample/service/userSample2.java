package com.share.codesample.service;

public class userSample2 {

    public static void main(String[] args) {
        String name = " tom ";
        String address = "~guangdong shenzhen";
        Integer age = -99;

        name = name.trim();
        address = address.replace("~", "");
        if (age < 0) {
            age = 0;
        }

        System.out.println("name:" + name + ",address:" + address + ",age=" + age);
    }

}
