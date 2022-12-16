package com.share.codesample.service.classSample1;

public class BaseUserInfo {
    public String idNo;
    public String mobile;
    public String name;
    public Integer sex;
    public Integer age;

    public BaseUserInfo() {

    }

    public BaseUserInfo(String inIdNo, String inMobile, String inName, Integer inSex, Integer inAge) {
        idNo = inIdNo;
        mobile = inMobile;
        name = inName;
        sex = inSex;
        age = inAge;
    }
}
