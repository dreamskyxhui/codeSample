package com.share.codesample.service.classSample3;

public class SameUserSimpleRuleCheck implements SameUserCheck {

    @Override
    public Boolean isSameUser(UserInfo srcUser, UserInfo cmpUser) {
        //身份证或电话号码相同，且姓名、性别、年纪没有不一致时为相同用户
        if (srcUser.idNo.equals(cmpUser.idNo) || srcUser.mobile.equals(cmpUser.mobile)) {
            if (!srcUser.isDiffName(cmpUser.name)
                    && !srcUser.isDiffSex(cmpUser.sex)
                    && !srcUser.isDiffAge(cmpUser.age)) {
                return true;
            }
        }

        return false;
    }
}
