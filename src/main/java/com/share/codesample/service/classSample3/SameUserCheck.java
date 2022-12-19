package com.share.codesample.service.classSample3;

/**
 * 接口类：判断是否是同一个用户
 */
public interface SameUserCheck {

    /**
     * 判断是否是同一个用户
     *
     * @param srcUser 输入用户
     * @param cmpUser 待比较用户
     * @return 是同一个用户返回true，否则返回false
     */
    public Boolean isSameUser(UserInfo srcUser, UserInfo cmpUser);

}
