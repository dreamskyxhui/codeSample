package com.share.codesample.service.classSample3;

public class SameUserSimpleRuleCheck implements SameUserCheck{

    @Override
    public Boolean isSameUser(UserInfo srcUser, UserInfo cmpUser) {
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
