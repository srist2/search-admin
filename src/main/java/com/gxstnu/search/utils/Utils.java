package com.gxstnu.search.utils;

import com.gxstnu.search.entity.dict.MissTypeDict;
import com.gxstnu.search.entity.dict.SeekTypeDict;
import com.gxstnu.search.entity.dict.SexDict;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Utils {

    /**
     * 获取当前时间
     *
     * @return {Date} yyyy-MM-dd HH:mm:ss
     */
    public Date getNowDate() {
        // 格式化时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 获取当前时间
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = sdf.parse(dateTime.format(formatter));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Date转换为String格式
     *
     * @param dateDate yyyy-MM-dd HH:mm:ss
     *
     * @return {String} yyyy-MM-dd HH:mm:ss
     */
    public String dateToString(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 获取当前时间
     * @return {String} yyyyMMddHHmmss
     */
    public String getDateString() {
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar = Calendar.getInstance();
        String dateName = df.format(calendar.getTime());
        return dateName;
    }

    /**
     * 失踪者寻找类型转换
     * @param list      寻找类型字典
     * @param dictId    要查询字典id
     * @return  {String} 对应id类型
     */
    public String dictChangeSeek(List<SeekTypeDict> list, String dictId) {
        for (SeekTypeDict seek : list) {
            if (seek.getDict_id() == Integer.parseInt(dictId)) {
                return seek.getDict_name();
            }
        }
        return "";
    }

    /**
     * 失踪者失踪类型转换
     * @param list      寻找类型字典
     * @param dictId    要查询字典id
     * @return  {String} 对应id类型
     */
    public String dictChangeMiss(List<MissTypeDict> list, String dictId) {
        for (MissTypeDict miss : list) {
            if (miss.getDict_id() == Integer.parseInt(dictId)) {
                return miss.getDict_name();
            }
        }
        return "";
    }

    /**
     * 性别类型转换
     * @param list      寻找类型字典
     * @param dictId    要查询字典id
     * @return  {String} 对应id类型
     */
    public String dictChangeSex(List<SexDict> list, String dictId) {
        for (SexDict sex : list) {
            if (sex.getDict_id() == Integer.parseInt(dictId)) {
                return sex.getDict_name();
            }
        }
        return "";
    }
}
