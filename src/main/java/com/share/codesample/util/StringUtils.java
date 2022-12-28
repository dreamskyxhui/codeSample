package com.share.codesample.util;

public class StringUtils {

    /**
     * 判断是否有效
     *
     * @param obj
     * @return
     */
    public static boolean isValidValue(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj instanceof String) {
            String strVal = (String) obj;
            if (strVal.trim().isEmpty()
                    || strVal.equals("null")
                    || strVal.equals("NULL")
                    || strVal.trim().equals("-")) {
                return false;
            }
        }

        return true;
    }

    /**
     * 字符串参数特殊字符过滤
     *
     * @param src
     * @return
     */
    public static String purify(String src) {
        String dst = src
                .replace("\\", "")
                .replace("\"", "")
                .replace(";", "")
                .replace(" ", "")
                .replace("NULL", "")
                .replace("null", "")
                .replace("'", "");

        return dst;
    }

    /**
     * 判断字符串内容是否一致
     *
     * @param src
     * @param dst
     * @return
     */
    public static boolean isSameContent(String src, String dst) {
        if (!isValidValue(src) || !isValidValue(dst)) {
            return false;
        }

        if (!src.equals(dst)) {
            return false;
        }

        return true;
    }

    /**
     * 是否是不同内容的字符串；两个字符串必须都有内容
     *
     * @param src
     * @param dst
     * @return
     */
    public static boolean isDifferentContent(String src, String dst) {
        if (!isValidValue(src) || !isValidValue(dst)) {
            return false;
        }

        if (src.equals(dst)) {
            return false;
        }

        return true;
    }

    /**
     * 判断字符串是否为null或空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (null == str) {
            return true;
        }

        if (str.isEmpty()) {
            return true;
        }

        return false;
    }

}
