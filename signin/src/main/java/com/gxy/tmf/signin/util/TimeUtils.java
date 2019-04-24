package com.gxy.tmf.signin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;



/**
 * @Author : tangmf
 * @Desription : 日期时间工具类
 * 提供一些常用的日期时间操作方法，所有方法都为静态，不用实例化该类即可使用。
 * @Date : 2019年3月31日 下午10:53:22
 */
public class TimeUtils {
    /** 西文日期表示 */
    /**
     * 月月日日格式
     */
    public static final String MMdd = "MMdd";
    /**
     * 年年月月格式
     */
    public static final String YYMM = "yyMM";
    public static final String YYYYMM = "yyyyMM";
    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYYMMDDHMS = "yyyyMMddHHmmss";
    public static final String YYMMDDHHMMSSSSS = "yyMMddHHmmssSSS";//年年月月日日时时分分秒秒毫毫毫

    public static final String YYYY_MM = "yyyy-MM";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String DD_MMM_YYYY = "dd MMM yyyy";
    public static final String XX_MMM_YYYY = "'XX' MMM yyyy";

    public static final String YYYY_MM_XX = "yyyy-MM-'XX'";

    public static final String YYYY_MM_DD_HM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD_HMS = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYY_MM_EN = "MM yyyy";
    public static final String YYYY_MMM_DD_EN = "dd MMM yyyy";
    public static final String YYYY_MM_DD_HM_EN = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD_HMS_EN = "yyyy-MM-dd HH:mm:ss";

    /**
     * 中文格式年月日表示
     */
    public static final String YYYY_MM_CN = "yyyy'年'MM'月'";
    public static final String YYYY_MM_DD_CN = "yyyy'年'MM'月'dd'日'";
    public static final String YYYY_MM_DD_HM_CN = "yyyy'年'MM'月'dd'日'HH'时'mm'分'";
    public static final String YYYY_MM_DD_HMS_CN = "yyyy'年'MM'月'dd'日'HH'时'mm'分'ss'秒'";

    public final static int TIMEZONE_OFFSET = TimeZone.getDefault()
            .getOffset(0);

    /**
     * 格式化输出日期，fmt是按已经设定的好格式。
     *
     * @param fmt 按SimpleDateFormat提供的格式输出
     * @param tm
     * @return 格式化后的串
     */
    public static String formatDate(String fmt, long tm) {
        SimpleDateFormat sdf = new SimpleDateFormat(fmt, Locale.ENGLISH);
        return sdf.format(new Date(tm));
    }

    /**
     * 按指定的格式化串格式日期，并输出日期串。
     *
     * @param format 格式
     * @param date   日期
     * @return 格式化后的串
     */
    public static String formatDate(String format, Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
        return sdf.format(date);
    }

    /**
     * 解析一个串按指定的格式，返回时间值。如果失败，返回当前时间.
     *
     * @param fmt  格式
     * @param date 日期时间串 如 2001-01-01或"2001-01-01 00:05"
     * @return
     */
    public static Date parseDate(String fmt, String date) {
        return parseDate(fmt, date, new Date());
    }

    /**
     * <b>按指定格式解析时间</b>
     * <p>
     * <p>
     * 解析一个串按指定的格式，返回时间值。如果失败，返回为职.
     *
     * @param fmt  格式
     * @param date 日期时间串 如 2001-01-01或"2001-01-01 00:05"
     * @return
     */
    public static Date parseDate(String fmt, String date, Date defDate) {
        assert date != null;
        // 为空的情况下，返回默认值
        if (date == null || date.length() < 0) {
            return defDate;
        }

        // 对应未知日期
        if (date.indexOf("XX") > 0) {
            fmt = YYYY_MM_XX;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(fmt, Locale.ENGLISH);
        // 2013-01-01 00:00:00.123
        if (date.length() < 11) {
            date = date.replaceAll("\\.|\\/", "-");
        }
        try {
            Date dd = sdf.parse(date);
            if (fmt.equals(YYYY_MM_XX)) {
                dd.setTime(dd.getTime() + 1);
            }
            return dd;
        } catch (ParseException e) {
            if (date.indexOf('年') > 0) {
                return parseDate(YYYY_MM_DD_CN, date, defDate);
            }
        }
        return defDate;
    }
    
    /**
     * <b>按指定格式解析时间</b>
     * <p>
     * <p>
     * 解析一个串按指定的格式，返回时间值。如果失败，返回null.
     *
     * @param fmt  格式
     * @param date 日期时间串 如 2001-01-01或"2001-01-01 00:05"
     * @return
     */
    public static Date parseDate1(String fmt, String date) {
    	assert date != null;
    	// 为空的情况下，返回默认值
    	if (date == null || date.length() < 0) {
    		return null;
    	}
    	
    	// 对应未知日期
    	if (date.indexOf("XX") > 0) {
    		fmt = YYYY_MM_XX;
    	}
    	SimpleDateFormat sdf = new SimpleDateFormat(fmt, Locale.ENGLISH);
    	// 2013-01-01 00:00:00.123
    	if (date.length() < 11) {
    		date = date.replaceAll("\\.|\\/", "-");
    	}
    	try {
    		Date dd = sdf.parse(date);
    		if (fmt.equals(YYYY_MM_XX)) {
    			dd.setTime(dd.getTime() + 1);
    		}
    		return dd;
    	} catch (Exception e) {
    			return null;
    	}
    }

    /**
     * 判断该年份是不是闰年
     *
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year) {
        if ((year % 100 == 0) && (year % 400 == 0) || (year % 100 != 0)
                && (year % 4 == 0)) {
            return true;
        }
        return false;
    }

    /**
     * 取得两个时间相差的天数
     *
     * @param from
     * @param to
     * @return
     */
    public static int getDateDiff(Date from, Date to) {
        long t1 = from.getTime();
        long t2 = to.getTime();
        long d1 = (t1 + TIMEZONE_OFFSET) / (1000L * 60 * 60 * 24);
        long d2 = (t2 + TIMEZONE_OFFSET) / (1000L * 60 * 60 * 24);
        return (int) (d2 - d1);
    }

    public static int getBetweenDate(Date start, Date end) {
        return getDateDiff(start, end) + 1;
    }

    /**
     * 获取两个日期之间的日期,包含开始和结束日期
     */
    public static List<Date> getBetweenDates(Date start, Date end) {
        List<Date> result = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            start = sdf.parse(sdf.format(start));
            end = sdf.parse(sdf.format(end));
            result = new ArrayList<Date>();
            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            while (tempStart.before(tempEnd)) {
                result.add(tempStart.getTime());
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }
            result.add(tempStart.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static int getMonthDiff(Date from, Date to) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(from);
        int yy1 = cal1.get(Calendar.YEAR);
        int mm1 = cal1.get(Calendar.MONTH);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(to);
        int yy2 = cal2.get(Calendar.YEAR);
        int mm2 = cal2.get(Calendar.MONTH);

        return 12 * (yy2 - yy1) + mm2 - mm1;
    }

    public static int getYearDiff(Date from, Date to) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(from);
        int yy1 = cal1.get(Calendar.YEAR);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(to);
        int yy2 = cal2.get(Calendar.YEAR);

        return yy2 - yy1;
    }

    /**
     * 计算指定日期增加几年或减少几年对应的日期。
     *
     * @param date 日期函数
     * @param n    年数
     * @return 计算结果日期
     */
    public static Date addYear(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DATE);

        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.YEAR, year + n);

        int lastDate = cal.getActualMaximum(Calendar.DATE);
        cal.set(Calendar.DATE, (lastDate < day) ? lastDate : day);
        Date date2 = new Date(cal.getTimeInMillis());
        return date2;
    }

    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);

        cal.set(Calendar.DATE, 1);
        cal.set(Calendar.MONTH, month + n);
        int lastDate = cal.getActualMaximum(Calendar.DATE);
        cal.set(Calendar.DATE, (lastDate < day) ? lastDate : day);
        Date date2 = new Date(cal.getTimeInMillis());
        return date2;
    }

    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DATE);
        cal.set(Calendar.DATE, day + n);
        Date date2 = new Date(cal.getTimeInMillis());
        return date2;
    }

    /**
     * @param @param dateTime
     * @param @param hours
     * @return void
     * @Description 增加小时
     * @author sanwei sanwei@xiongdi.cn
     * @Date 2015-9-21 下午09:48:46
     */
    public static Date addHours(Date date, int hours) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR, hours);
        return c.getTime();
    }

    /**
     * @param @param dateTime
     * @param @param hours
     * @return void
     * @Description 增加分钟
     * @author sanwei sanwei@xiongdi.cn
     * @Date 2015-9-21 下午09:48:46
     */
    public static Date addMin(Date date, int min) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MINUTE, min);
        return c.getTime();
    }

    /**
     * @param @param  time
     * @param @param  from
     * @param @return
     * @return Date
     * @Description 字符串转换成日期
     * @author 贲月 benyue@xiongdi.cn
     * @Date 2017年6月22日 下午4:07:35
     */
    public static Date StrToDate(String time, String from) {

        SimpleDateFormat format = new SimpleDateFormat(from);
        Date date = null;
        try {
            date = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    /**
     * 取得两个时间相差的分钟
     *
     * @param from
     * @param to
     * @return
     */
    public static int getMinDiff(Date from, Date to) {
        long t1 = from.getTime();
        long t2 = to.getTime();
        long d1 = (t1 + TIMEZONE_OFFSET) / (1000L * 60);
        long d2 = (t2 + TIMEZONE_OFFSET) / (1000L * 60);
        return (int) (d2 - d1);
    }

    public static int getYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.YEAR);
    }

    public static int getMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MONTH) + 1;
    }

    /**
     * 获取某年第一天日期
     *
     * @param year 年份
     * @return Date
     */
    public static Date getCurrYearFirst(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    public static Date getCurrYearLast(int year) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.clear();
//        calendar.set(Calendar.YEAR, year);
//        calendar.roll(Calendar.DAY_OF_YEAR, -1);
//        Date currYearLast = calendar.getTime();
    	Date nextYear = getCurrYearFirst(year+1);
    	Calendar c = Calendar.getInstance();
        c.setTime(nextYear);
    	c.add(Calendar.MILLISECOND, -1);
    	Date currYearLast = c.getTime();  
        return currYearLast;
    }

    /**
     * 获取两个日期之间的每一天
     *
     * @param dateFirst
     * @param dateSecond
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static List<String> getEveryDay(String dateFirst, String dateSecond) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList list = new ArrayList();
        try {
            Date dateOne = dateFormat.parse(dateFirst);
            Date dateTwo = dateFormat.parse(dateSecond);

            Calendar calendar = Calendar.getInstance();

            calendar.setTime(dateOne);

            while (calendar.getTime().before(dateTwo)) {
                list.add(dateFormat.format(calendar.getTime()));
//                System.out.println(dateFormat.format(calendar.getTime()));
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }

//            System.out.println(list.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 判断是否法定节假日
     *
     * @param date  日期
     * @param dates 法定假期集合
     * @return
     */
    public static boolean isFestival(Date date, ArrayList<Date> dates) {
        if (Util.isNotEmpty(dates) && Util.isNotEmpty(date)) {
            for (Date d : dates) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (sdf.format(date).equals(sdf.format(d))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断是否补班日期
     *
     * @param date  日期
     * @param dates 补班日期集合
     * @return
     */
    public static boolean isBb(Date date, ArrayList<Date> dates) {
        if (Util.isNotEmpty(dates) && Util.isNotEmpty(date)) {
            for (Date d : dates) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (sdf.format(date).equals(sdf.format(d))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断日期是否是周末
     *
     * @param date 日期
     * @return
     */
    public static boolean isWeekend(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            return true;
        }
        return false;
    }

    /**
     * 判断日期是否是工作日(法定节假日和周末为非工作日)
     *
     * @param date   日期
     * @param dates1 法定假期集合
     * @param dates2 补班日期集合
     * @return
     */
    public static boolean isWorkDay(Date date, ArrayList<Date> dates1, ArrayList<Date> dates2) {
        if ((!isFestival(date, dates1) && !isWeekend(date)) || isBb(date, dates2)) {
            return true;
        }
        return false;
    }

    /**
     * 计算假期年度区间　如：4年一度
     *
     * @param start 开始日期
     * @param year  几年一度
     * @return
     */
    public static Map<String, Date> getYearRegion(Date start, Date holiday, Integer year) {
        Map<String, Date> map = new HashMap<>();
        if (Util.isEmpty(start) || Util.isEmpty(year) || Util.isEmpty(holiday)) {
            return new HashMap<>();
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Calendar begin = Calendar.getInstance();
            begin.setTime(start);
            begin.add(Calendar.YEAR, 1);

            Calendar end = Calendar.getInstance();
            end.setTime(begin.getTime());
            end.add(Calendar.YEAR, year - 1);

            Date sd = getCurrYearFirst(getYear(begin.getTime()));
            Date ed = getCurrYearLast(getYear(end.getTime()));
            map.put("start", sdf.parse(sdf.format(sd)));
            map.put("end", sdf2.parse(sdf.format(ed) + " 23:59:59"));

//            System.out.println(sdf.format(sd) + "---------" + sdf.format(ed));

            if (holiday.before(sd)) {
                //return new HashMap<>();
            }

            if (sdf.parse(sdf.format(holiday)).after(ed)) {
                begin.add(Calendar.YEAR, year - 1);
                map = getYearRegion(begin.getTime(), holiday, year);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获取下一年度信息
     *
     * @param map
     * @return
     */
    public static Map<String, Date> getNextYearRegion(Map<String, Date> map) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        Date start = map.get("start");
        Date end = map.get("end");
        int count = getYear(end) - getYear(start);
        c1.setTime(start);
        c1.add(Calendar.YEAR, count + 1);
        c2.setTime(end);
        c2.add(Calendar.YEAR, count + 1);

        map.put("start", c1.getTime());
        map.put("end", c2.getTime());
        return map;
    }

    public static Map<String, Date> getPreYearRegion(Map<String, Date> map) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        Date start = map.get("start");
        Date end = map.get("end");
        int count = getYear(end) - getYear(start);
        c1.setTime(start);
        c1.add(Calendar.YEAR, -(count + 1));
        c2.setTime(end);
        c2.add(Calendar.YEAR, -(count + 1));

        Map<String, Date> newmap = new HashMap<>();
        newmap.put("start", c1.getTime());
        newmap.put("end", c2.getTime());
        return newmap;
    }

    /**
     * 获取num天后的日期
     *
     * @param start     开始日期
     * @param dates1    法定假日集合
     * @param dates2    补班日期集合
     * @param num       几天
     * @param isworkDay 是否工作日
     * @return
     */
    public static Date getWordDate(Date start, ArrayList<Date> dates1, ArrayList<Date> dates2, Integer num, boolean isworkDay) {
        Calendar begin = Calendar.getInstance();
        begin.setTime(start);
        int flag = 0;
        for (int i = 0; i < 999999; i++) {
            if (isworkDay) {//工作日
                if (isWorkDay(begin.getTime(), dates1, dates2)) {
                    flag++;
                }
            } else {//自然日
                flag++;
            }
            if (flag != num) {
                begin.add(Calendar.DAY_OF_MONTH, 1);
            } else {
                break;
            }
        }
        return begin.getTime();
    }

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ArrayList<Date> list1 = new ArrayList<>();
        ArrayList<Date> list2 = new ArrayList<>();
        try {
            list1.add(sdf.parse("2018-07-19"));
            list1.add(sdf.parse("2018-07-20"));
            list1.add(sdf.parse("2018-07-21"));
            list1.add(sdf.parse("2018-07-22"));
            list1.add(sdf.parse("2018-07-23"));
            list1.add(sdf.parse("2018-07-24"));
            list1.add(sdf.parse("2018-07-25"));
            list1.add(sdf.parse("2018-07-26"));

            list2.add(sdf.parse("2018-07-19"));
            list2.add(sdf.parse("2018-07-20"));
            list2.add(sdf.parse("2018-07-21"));
            list2.add(sdf.parse("2018-07-22"));
            list2.add(sdf.parse("2018-07-23"));

            //System.out.println(isWeekend(sdf.parse("2018-7-16")));
            //System.out.println(isFestival(sdf.parse("2018-4-5"), list));
            //System.out.println(isWorkDay(sdf.parse("2018-7-24"), list1, list2));
            //System.out.println(isFestival(sdf.parse("2018-04-09"), list1) || isWeekend(sdf.parse("2018-04-09")));
            //Map<String, Date> map = TimeUtils.getYearRegion(sdf.parse("2011-06-29"), sdf.parse("2018-08-22"), 4);
            //Date d = getWordDate(sdf.parse("2018-08-21"), null, null, 6, false);
            //System.out.println(sdf.format(d));
            //map = getNextYearRegion(map);
            //map = getPreYearRegion(map);
            //System.out.println(TimeUtils.getDateDiff(sdf.parse("2018-03-30"), sdf.parse("2018-04-01")));
            //System.out.println("身份证制新卡,96008".split("\\,")[1]);

//            System.out.println("");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


}
