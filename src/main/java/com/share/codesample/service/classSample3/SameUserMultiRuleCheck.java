package com.share.codesample.service.classSample3;

public class SameUserMultiRuleCheck implements SameUserCheck{

    @Override
    public Boolean isSameUser(UserInfo srcUser, UserInfo cmpUser) {
        //身份证相同，且姓名相同才是同一个用户；否则不是同一个用户
        if (srcUser.idNo.equals(cmpUser.idNo)) {
            if (srcUser.isDiffName(cmpUser.name)) {
                return false;
            } else {
                return true;
            }
        }

        //电话号码相同，且姓名、性别、年龄相同：才是同一个用户
        if (srcUser.mobile.equals(cmpUser.mobile)) {
            if (!srcUser.isDiffName(cmpUser.name)
                    && !srcUser.isDiffSex(cmpUser.sex)
                    && !srcUser.isDiffAge(cmpUser.age)) {
                return true;
            }
        }

        return false;
    }
}
