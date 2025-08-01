package com.aibank.wm.zd.client.util;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtils {
    public static final String DATETIME_DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String ISO8601_PATTERN = "yyyy-MM-dd'T'HH:mm:ssXXX";
    public static final String ISO8601_PATTERN_WITHOUT_MILLISECOND = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String yearMonthPattern = "^[0-9]{6}$";

    public DateUtils() {
    }

    public static LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now(getDefaultZoneId());
    }

    public static LocalDateTime min(LocalDateTime... date) {
        if (date.length == 0) {
            return null;
        }
        LocalDateTime minDate = date[0];
        for (LocalDateTime dateTime : date) {
            if (dateTime.isBefore(minDate)) {
                minDate = dateTime;
            }
        }
        return minDate;
    }

    public static LocalDateTime max(LocalDateTime... date) {
        if (date.length == 0) {
            return null;
        }
        LocalDateTime maxDate = date[0];
        for (LocalDateTime dateTime : date) {
            if (dateTime.isAfter(maxDate)) {
                maxDate = dateTime;
            }
        }
        return maxDate;
    }

    public static LocalDateTime getLocalDateTime(long timestamp) {
        return LocalDateTime.ofEpochSecond(timestamp, 0, getDefaultZoneOffset());
    }

    public static String toString(LocalDateTime dt) {
        return toString(dt, "yyyy-MM-dd HH:mm:ss");
    }

    public static String toString(LocalDateTime dt, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dt.format(formatter);
    }

    public static String toString(ZonedDateTime dt, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dt.format(formatter);
    }

    public static String getCurrentDateTimeString() {
        return toString(getCurrentDateTime());
    }

    public static ZoneOffset getDefaultZoneOffset() {
        return ZoneOffset.of("+08:00");
    }

    public static ZoneId getDefaultZoneId() {
         return ZoneId.of("Asia/Shanghai");
    }

    public static long getUnixTimeFromString(String datetime, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(datetime, formatter).atZone(getDefaultZoneId()).getLong(ChronoField.INSTANT_SECONDS);
    }

    public static long localDateTimeToLong(LocalDateTime localDateTime) {
        return localDateTime.toInstant(getDefaultZoneOffset()).getEpochSecond();
    }

    public static Date transfer(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String getYearMonth(LocalDateTime localDateTime) {
        return toString(localDateTime, "yyyyMM");
    }

    public static String getNowYearMonth() {
        return toString(getCurrentDateTime(), "yyyyMM");
    }

    public static LocalDateTime yearMonthToDate(String yearMonth) {
        LocalDateTime localDateTime = null;
        Pattern pattern = Pattern.compile("^[0-9]{6}$");
        Matcher matcher = pattern.matcher(yearMonth);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("时间格式错误,正确格式应该是如:201704");
        } else {
            try {
                localDateTime = LocalDate.of(Integer.valueOf(yearMonth.substring(0, 4)), Integer.valueOf(yearMonth.substring(4, 6)), 1).atStartOfDay();
            } catch (Exception var5) {
                throw new IllegalArgumentException("时间格式错误,正确格式应该是如:201704");
            }

            if (localDateTime == null) {
                throw new IllegalArgumentException("时间格式错误,正确格式应该是如:201704");
            } else {
                return localDateTime;
            }
        }
    }

    public static LocalDateTime toDate(String dateTime) {
        return toDate(dateTime, getDefaultZoneId());
    }

    public static LocalDateTime toDate(String dateTime, ZoneId zoneId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ISO8601_PATTERN_WITHOUT_MILLISECOND);
        ZonedDateTime zonedDateTime = LocalDateTime.parse(dateTime, formatter).atZone(zoneId);
       return zonedDateTime.withZoneSameInstant(getDefaultZoneId()).toLocalDateTime();
    }

    public static long getStartTimeOfMonth(String yearMonth) {
        return localDateTimeToLong(yearMonthToDate(yearMonth));
    }

    public static long getStartTimeOfCurrentMonth() {
        return getStartTimeOfMonth(toString(getCurrentDateTime(), "yyyyMM"));
    }

    public static long getEndTimeOfMonth(String yearMonth) {
        return localDateTimeToLong(yearMonthToDate(yearMonth).plusMonths(1L).minusSeconds(1L));
    }

    public static long getEndTimeOfCurrentMonth() {
        return getEndTimeOfMonth(toString(getCurrentDateTime(), "yyyyMM"));
    }

    public static String getYearMonthDay(LocalDateTime dateTime) {
        return toString(dateTime, "yyyyMMdd");
    }

    public static LocalDateTime getStartOfMonth(LocalDateTime dateTime) {
        return dateTime.toLocalDate().atStartOfDay().minusDays((long) (dateTime.getDayOfMonth() - 1));
    }

    public static LocalDateTime getEndOfMonth(LocalDateTime dateTime) {
        return getStartOfMonth(dateTime).plusMonths(1L).minusSeconds(1L);
    }

    public static LocalDateTime yearMonthDayToDate(String date) {
        if (date.length() != 8) {
            throw new IllegalArgumentException("日期参数错误");
        } else {
            long localDateTime = getUnixTimeFromString(date + " 00:00:00", "yyyyMMdd HH:mm:ss");
            return getLocalDateTime(localDateTime);
        }
    }

    public static long betweenInMilliSecond(LocalDateTime date1,LocalDateTime date2){
       return Duration.between(date1, date2).toMillis();
    }
}
