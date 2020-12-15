package com.htf.zdh.utils;

/**
 * @author zhangfei
 * @version 1.0.0
 * @className Utils.java
 * @description TODO
 * @createTime 2020/11/27 13:14
 */
public class Utils {

    public static void main(String[] args){}


    private static final String[] MOBILE_SEGMENT;

    static {
        MOBILE_SEGMENT = new String[]{"130", "131", "132", "133", "134", "135", "136", "137", "138", "139", "147", "150", "151", "152", "153", "154", "155", "156", "157", "158", "159", "180", "185", "186", "187", "188", "189"};
    }


    private static int getRandomNum(int max) {
        if (max < 0) {
            throw new RuntimeException("最大值需要大于或等于0");
        }
        return (int) (Math.random() * max);
    }

    public static final String getRange(String src, int len) {
        if (src == null || src.length() == 0) {
            throw new RuntimeException("来源字符串为空");
        }
        if (len < 1) {
            throw new RuntimeException("返回字符串长度需要大于0");
        }
        String[] arr = src.split(",");
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < len; i++) {
            int index = getRandomNum(arr.length);
            buf.append(arr[index].trim());
        }
        return buf.toString();
    }

    public static String getMobile() {
        int index = getRandomNum(MOBILE_SEGMENT.length);
        StringBuffer buf = new StringBuffer(MOBILE_SEGMENT[index]);
        final String numbers = "0, 1, 2, 3, 4, 5, 6, 7, 8, 9";
        for (int i = 0; i < 8; i++) {
            buf.append(getRange(numbers, 1));
        }
        return buf.toString();
    }


}
