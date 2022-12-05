package com.share.codesample.util;

import java.text.DateFormat;
import java.util.TimeZone;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Slf4j
public class DateUtils {

    /**
     * 日期格式：yyyyMMdd
     */
    public static final String DATE_SHORT = "yyyyMMdd";

    /**
     * 日期格式：yyyy-MM-dd
     */
    public static final String DATE_LONG = "yyyy-MM-dd";

    public static final String MONTH_LONG = "yyyy-MM";

    public static final String HOURE_LONG = "yyyy-MM-dd HH";

    public static final String MINUTE_LONG = "yyyy-MM-dd HH:mm";

    /**
     * 时间格式：HHmmss
     */
    public static final String TIME_SHORT = "HHmmss";

    /**
     * 时间格式：HH:mm:ss
     */
    public static final String TIME_LONG = "HH:mm:ss";

    /**
     * 日期时间格式：yyyyMMddHHmmss
     */
    public static final String DATETIME_SHORT = "yyyyMMddHHmmss";

    /**
     * 日期时间格式：yyyy-MM-dd HH:mm:ss
     */
    public static final String DATETIME_LONG = "yyyy-MM-dd HH:mm:ss";

    /**
     * 日期时间毫秒格式：yyyyMMddHHmmssSSS
     */
    public static final String DATETIMEMILLIS = "yyyyMMddHHmmssSSS";

    /**
     * 比较时间大小
     * @param date1
     * @param date2
     * @return date1>date2 true; date1<date2 false;
     */
    public static boolean compareDate(Date date1, Date date2) {
        if (date1.getTime() > date2.getTime()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 格式化时间
     *
     * @param time
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date getDate(String time, String format) throws ParseException {
        DateFormat df = new SimpleDateFormat(format);

        return df.parse(time);
    }

    /**
     * 获取昨天日期
     * 日期格式:yyyyMMdd
     *
     * @return
     */
    public static String getLastDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        Date date = cal.getTime();
        SimpleDateFormat sp = new SimpleDateFormat(DATE_SHORT);
        return sp.format(date);
    }


    /**
     * 获取n天前的提起
     * 日期格式:yyyyMMdd
     *
     * @return
     */
    public static String getLastNDate(Integer n) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1 * n);
        Date date = cal.getTime();
        SimpleDateFormat sp = new SimpleDateFormat(DATE_SHORT);
        return sp.format(date);
    }

    /**
     * 获取当天日期
     * 日期格式:yyyyMMdd
     *
     * @return
     */
    public static String getTodayDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        Date date = cal.getTime();
        SimpleDateFormat sp = new SimpleDateFormat(DATE_SHORT);
        return sp.format(date);
    }

    /**
     * 获取当天日期
     * 日期格式:yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getNow() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        Date date = cal.getTime();
        SimpleDateFormat sp = new SimpleDateFormat(DATETIME_LONG);
        return sp.format(date);
    }

    /**
     * 将yyyyMMdd字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static String addDateLineFormat(String strDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            Date date = dateFormat.parse(strDate);
            return (new SimpleDateFormat("yyyy-MM-dd")).format(date);
        } catch (ParseException e) {
            return "";
        }
    }

    /**
     * 获取时间差
     * @param startTime 起始时间，格式为：yyyy-MM-dd HH:mm:ss
     * @param endTime 结束时间，格式为：yyyy-MM-dd HH:mm:ss
     * @return 时间差，单位为秒
     */
    public static long getTimeDuration(String startTime, String endTime){
        try {
            DateFormat df = new SimpleDateFormat(DATETIME_LONG);
            Calendar cal = Calendar.getInstance();
            cal.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

            long startMs = df.parse(startTime).getTime();
            long endMs = df.parse(endTime).getTime();
            long durationSecond = (endMs - startMs) / 1000;

            return durationSecond;
        }catch (ParseException e) {
            return -1;
        }
    }

    /**
     * 将秒数转换为 时：分：秒形式的字符串，例如："05:33:52"
     * @param timeSecond 秒数
     * @return 转换后的字符串
     */
    public static String getSecondShowString(long timeSecond){
        long hour = timeSecond/3600;
        long minute = (timeSecond - hour*3600)/60;
        long second = timeSecond - hour*3600 - minute*60;

        return String.format("%02d", hour) + ":" + String.format("%02d", minute) + ":" + String.format("%02d", second);
    }

    /**
     * 将日期补齐，统一转换为yyyy-MM-dd HH:mm:ss
     * @param strDate yyyy-MM 或 yyyy-MM-DD 或者 yyy-MM-DD HH:mm:ss
     * @return 日期格式:yyyy-MM-dd HH:mm:ss
     */
    public static String getFullDate(String strDate) {
        try {
            String formatStr = "";
            switch (strDate.length()) {
                case 7:
                    formatStr = MONTH_LONG;
                    break;
                case 10:
                    formatStr = DATE_LONG;
                    break;
                case 13:
                    formatStr = HOURE_LONG;
                    break;
                case 16:
                    formatStr = MINUTE_LONG;
                    break;
                default:
                    formatStr = DATETIME_LONG;
            }

            SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
            Date date = dateFormat.parse(strDate);
            return (new SimpleDateFormat(DATETIME_LONG)).format(date);
        }catch (ParseException e) {
            return "";
        }
    }


    /**
     * 获取天数集合
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<String> getDays(String startTime, String endTime) {
        // 返回的日期集合
        List<String> days = new ArrayList<String>();

        if (null == startTime || null == endTime) {
            return days;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = dateFormat.parse(startTime.length() > 10 ? startTime.substring(0, 10) : startTime);
            Date end = dateFormat.parse(endTime.length() > 10 ? endTime.substring(0, 10) : endTime);

            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)

            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    /**
     * 获取小时集合
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<String> getHour(String startTime, String endTime, String interval) {
        // 返回的日期集合
        List<String> hour = new ArrayList<String>();
        //拼接时分秒
        startTime = getFullDate(startTime);
        endTime = getFullDate(endTime);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);

            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.HOUR, +1);// 日期加1(包含结束)

            while (tempStart.before(tempEnd)) {
                hour.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.HOUR, Integer.parseInt(interval));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return hour;
    }


    public static String formatDateTime(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return formatter.format(localDateTime);
    }


    public static String formatDateTime(LocalDateTime localDateTime, String format) {
        if (Objects.isNull(localDateTime)) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return formatter.format(localDateTime);
    }


    public static String formatDateTime(Date date) {
        if (Objects.isNull(date)) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    public static String getCurrDate(LocalDateTime localDateTime) {
        if (Objects.isNull(localDateTime)) {
            return "";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return formatter.format(localDateTime);
    }


    /**
     * dateTextToDate 解析处理提取函数
     *
     * @param dateText
     * @return
     */
    public static Date dateTextToDateParseDeal(String dateText) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dateObj = sdf.parse(dateText);
            return dateObj;
        } catch (ParseException e) {
            log.info("DateUtils - dateTextToDateParseDeal: " + e.getMessage());
            return null;
        }
    }

    /**
     * dateTextToDate 解析处理提取函数
     *
     * @param dateText
     * @param mode 0, 1, 2
     * @return
     */
    public static Date dateTextToDateParseDeal(String dateText, int mode) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dateObj = sdf.parse(dateText);

            if (1 == mode) {
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
                String dt = sdf1.format(dateObj);
                return sdf.parse(dt);
            }

            if (2 == mode) {
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
                String dt = sdf1.format(dateObj);
                return sdf.parse(dt);
            }

            return dateObj;
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * localDateTime 转 String
     *
     * @param localDateTime
     * @param format
     * @return
     */
    public static String format(LocalDateTime localDateTime, String format) {
        if (null == localDateTime || StringUtils.isBlank(format)) {
            return null;
        }
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(df);
    }

    /**
     * dateStr 转 LocalDateTime
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static LocalDateTime parseLocalDateTime(String dateStr, String format) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(format);
        LocalDateTime ldt = LocalDateTime.parse(dateStr, df);
        return ldt;
    }

    /**
     * 支持将 2021-09-01 12:01:01 / 2021-09-01 / 12:01:01 / 12:01 字符串转化为 Date 类型
     *
     * @param dateText
     * @return 日期类型数据
     */
    public static Date dateTextToDate(String dateText) {

        // 标准长时间
        if (dateText.length() == 19
                && dateText.indexOf("-") > 0
                && dateText.indexOf(":") > 0
                && dateText.indexOf(" ") > 0) {
            return dateTextToDateParseDeal(dateText);
        }

        // 年月日时间
        if (dateText.length() == 10
                && dateText.indexOf("-") > 0
                && dateText.indexOf(":") < 0
                && dateText.indexOf(" ") < 0) {
            return dateTextToDateParseDeal(String.format("%s 00:00:00", dateText));
        }

        // 时分秒时间
        if (dateText.length() == 8
                && dateText.indexOf("-") < 0
                && dateText.indexOf(":") > 0
                && dateText.indexOf(" ") < 0) {
            return dateTextToDateParseDeal(String.format("0000-00-00 %S", dateText));
        }

        // 时分时间
        if (dateText.length() == 5
                && dateText.indexOf("-") < 0
                && dateText.indexOf(":") > 0
                && dateText.indexOf(" ") < 0) {
            return dateTextToDateParseDeal(String.format("0000-00-00 %s:00", dateText));
        }

        return null;
    }

    /**
     * 支持将 2021-09-01 12:01:01 / 2021-09-01 / 12:01:01 / 12:01 字符串转化为 Date 类型
     *
     * @param dateText
     * @return 日期类型数据
     */
    public static Date dateTextToDate(String dateText, int mode) {

        // 标准长时间
        if (dateText.length() == 19
                && dateText.indexOf("-") > 0
                && dateText.indexOf(":") > 0
                && dateText.indexOf(" ") > 0) {
            return dateTextToDateParseDeal(dateText, mode);
        }

        // 年月日时间
        if (dateText.length() == 10
                && dateText.indexOf("-") > 0
                && dateText.indexOf(":") < 0
                && dateText.indexOf(" ") < 0) {
            return dateTextToDateParseDeal(String.format("%s 00:00:00", dateText), mode);
        }

        // 时分秒时间
        if (dateText.length() == 8
                && dateText.indexOf("-") < 0
                && dateText.indexOf(":") > 0
                && dateText.indexOf(" ") < 0) {
            return dateTextToDateParseDeal(String.format("0000-00-00 %S", dateText));
        }

        // 时分时间
        if (dateText.length() == 5
                && dateText.indexOf("-") < 0
                && dateText.indexOf(":") > 0
                && dateText.indexOf(" ") < 0) {
            return dateTextToDateParseDeal(String.format("0000-00-00 %s:00", dateText));
        }

        return null;
    }

    /**
     * 将 时间字符串 转化为 时间戳
     *
     * @param dateText
     * @return
     */
    public static long getTimeStampByDateText(String dateText) {
        return (dateTextToDate(dateText).getTime());
    }

    /**
     * 获取两个时间字符串的差值，返回单位是 秒
     *
     * @param dateText1
     * @param dateText2
     * @return
     */
    public static long getSecDiffDateText(String dateText1, String dateText2) {
        long dt1 = getTimeStampByDateText(dateText1);
        long dt2 = getTimeStampByDateText(dateText2);
        long diff = dt2 - dt1;
        return diff / 1000;
    }

    /**
     * 获取两个 Date 类型的差值，返回单位是 秒
     *
     * @param dateObj1
     * @param dateObj2
     * @return
     */
    public static long getSecDiffDate(Date dateObj1, Date dateObj2) {
        long dt1 = dateObj1.getTime();
        long dt2 = dateObj2.getTime();
        long diff = dt2 - dt1;
        return diff / 1000;
    }

    /**
     * 根据 格式 获取最近的 时间字符串
     *
     * @param sdfText
     * @return
     */
    public static String getLatestDateText(String sdfText) {
        long st = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat(sdfText);
        return sdf.format(new Date(st));
    }


    /**
     * 规范化时间操作
     *
     * @param dateText eg: "2021-10-14 10:22:00"
     * @param sourceSdfText "yyyy-MM-dd HH:mm:ss"
     * @param targetSdfText "yyyy-MM-dd HH:00:00" / "yyyy-MM-dd 00:00:00"
     * @return eg: 2021-10-14 10:00:00
     * @throws ParseException
     */
    public static String normalDateTextDeal(String dateText,
            String sourceSdfText,
            String targetSdfText)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(sourceSdfText);
        Date dateT = sdf.parse(dateText);
        sdf = new SimpleDateFormat(targetSdfText);
        return sdf.format(dateT);
    }

    /**
     * 获取 累加 的 时分秒
     *
     * @param orgiHms 原始 时分秒 字符串       eg: 03:00:00
     * @param accumulationMin 累加值（单位：分钟） eg: 60
     * @return eg: 03:01:00
     * @throws ParseException
     */
    public static String getAccumulationHms(String orgiHms, long accumulationMin) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            Date dt = sdf.parse(orgiHms);
            Date newDt = new Date(dt.getTime() + accumulationMin * 1000 * 60);
            String newDtTx = sdf.format(newDt);
            newDt = sdf.parse(newDtTx);
            long dtl = dt.getTime();
            long ndtl = newDt.getTime();
            //  时间逆转
            if (dtl > ndtl) {
                newDt = sdf.parse("23:59:50");
            }
            return sdf.format(newDt);
        } catch (Exception e) {
            return orgiHms;
        }
    }

    /**
     * 判断 某个时间点 是否存在于 某个时间区间
     *
     * @param pointDt 时间点     eg: 03:10:00
     * @param begDt 开始时间点  eg: 03:00:00
     * @param endDt 结束时间点  eg: 04:00:00
     * @param isClosedInterval 是否闭区间  eg: false
     * @param sdfFormat 日期格式    eg: HH:mm:ss
     * @return eg: true
     * @throws ParseException
     */
    public static boolean isPointDtOnDuration(String pointDt,
            String begDt,
            String endDt,
            boolean isClosedInterval,
            String sdfFormat
    ) throws ParseException {
        String currTime = pointDt;
        String dpBeginTime = begDt;
        String dpEndTime = endDt;
        SimpleDateFormat sdf = new SimpleDateFormat(sdfFormat);
        Date currTimeDt = sdf.parse(currTime);
        Date dpBeginTimeDt = sdf.parse(dpBeginTime);
        Date dpEndTimeDt = sdf.parse(dpEndTime);

        if (isClosedInterval) {
            if (currTimeDt.getTime() >= dpBeginTimeDt.getTime() && currTimeDt.getTime() <= dpEndTimeDt.getTime()) {
                return true;
            } else {
                return false;
            }
        } else {
            if (currTimeDt.getTime() > dpBeginTimeDt.getTime() && currTimeDt.getTime() < dpEndTimeDt.getTime()) {
                return true;
            } else {
                return false;
            }
        }
    }


    /**
     * 判断 某个时间点 是否存在于 某个时间区间
     *
     * @param pointDt 时间点     eg: 03:10:00
     * @param begDt 开始时间点  eg: 03:00:00
     * @param endDt 结束时间点  eg: 04:00:00
     * @param isClosedInterval 是否闭区间  eg: false
     * @return eg: true
     * @throws ParseException
     */
    public static boolean isPointDtOnDurationByDt(Date pointDt,
            Date begDt,
            Date endDt,
            boolean isClosedInterval
    ) throws ParseException {
        Date currTimeDt = pointDt;
        Date dpBeginTimeDt = begDt;
        Date dpEndTimeDt = endDt;

        if (isClosedInterval) {
            if (currTimeDt.getTime() >= dpBeginTimeDt.getTime() && currTimeDt.getTime() <= dpEndTimeDt.getTime()) {
                return true;
            } else {
                return false;
            }
        } else {
            if (currTimeDt.getTime() > dpBeginTimeDt.getTime() && currTimeDt.getTime() < dpEndTimeDt.getTime()) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 当前时间是否在某一个时间区间内
     *
     * @param begDt
     * @param endDt
     * @param isClosedInterval
     * @param sdfFormat
     * @return
     */
    public static boolean isCurrDtOnDuration(String begDt, String endDt, boolean isClosedInterval, String sdfFormat) {
        Date curr = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat(sdfFormat);
        String currDt = sdf.format(curr);
        try {
            return isPointDtOnDuration(currDt, begDt, endDt, isClosedInterval, sdfFormat);
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 根据格式 获取当前时间
     *
     * @param sdfFormat eg: "HH:mm:ss"/"yyyy-MM-dd HH:mm:ss"
     * @return eg: 14:43:22/2021-10-28 14:44:55
     */
    public static String getCurrDt(String sdfFormat) {
        Date curr = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat(sdfFormat);
        String currDt = sdf.format(curr);
        return currDt;
    }

    /**
     * 给时间加上几个小时
     *
     * @param day 当前时间 格式：yyyy-MM-dd HH:mm:ss
     * @param min 需要加的时间
     * @return
     */
    public static String addDateMin(String day, int min) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        Date date = null;
        try {
            date = format.parse(day);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (date == null) {
            return "";
        }
        System.out.println("front:" + format.format(date)); //显示输入的日期
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, min);// 24小时制
        date = cal.getTime();
        System.out.println("after:" + format.format(date));  //显示更新后的日期
        cal = null;
        return format.format(date);
    }


    /**
     * 是否指定的时间格式
     *
     * @param dtStr
     * @param sdfStr
     * @return
     */
    public static boolean isPointFormatDateStr(String dtStr, String sdfStr) {
        SimpleDateFormat sdf = new SimpleDateFormat(sdfStr);
        try {
            sdf.parse(dtStr);
            return true;
        } catch (ParseException e) {
//            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(addDateLineFormat("20190606"));
        System.out.println(getSecondShowString(11950));
        System.out.println(getFullDate("2021-08"));
        System.out.println(getFullDate("2021-08-01"));
        System.out.println(getFullDate("2021-08-01 11"));
        System.out.println(getFullDate("2021-08-01 12:00"));
        System.out.println(getFullDate("2021-08-01 13:00:00"));
    }

}
