package com.tw.utils.base;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description 字符串工具
 * @CreateDate
 */
public final class StringUtil {

    public static final int INDEX_NOT_FOUND = -1;

    private StringUtil() {
        throw new AssertionError();
    }

    /**
     * @Description 去除字符串所有空白
     * @CreateDate
     */
    public static String trimAllWhitespace(String str) {
        if (isBlank(str)) {
            return str;
        }
        return str.replaceAll(" ", "").replaceAll("\n", "").replaceAll("\t", "").toString();
    }

    /**
     * @Description 截取到指定的字符
     * @CreateDate
     */
    public static String stripEnd(String str, String stripChars) {
        if (isBlank(str)) {
            return str;
        }
        int end = str.length();
        if (isBlank(stripChars)) {
            return str;
        } else {
            while ((end != 0) && (stripChars.indexOf(str.charAt(end - 1)) != INDEX_NOT_FOUND)) {
                end--;
            }
        }
        return str.substring(0, end);
    }

    /**
     * @Description 判断字符串是否全是数字 true全是数字 fasle含有字符
     * @CreateDate
     */
    public static boolean isNumeric(String str) {
        if (isBlank(str)) {
            return false;
        }
        //Pattern pattern = Pattern.compile("[0-9]*");//0-9的数字
        Pattern pattern = Pattern.compile("^[0-9]+\\.?[0-9]*$");//0-9的数字和一个小数点
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * @Description 剔除包含数字的字符串中的字符
     * @CreateDate
     */
    public static String rmStringInNum(String str) {
        if (isBlank(str)) {
            return str;
        }
        String str1 = "";
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                str1 += str.charAt(i);
            }
        }
        return str1;
    }

    /**
     * @param str
     * @param code 默认编码GBK(中文2字节，英文1字节)UTF-8(中文3字节，英文1字节)ISO8859-1(中文1字节，英文1字节)
     * @return 混合字符长度
     * @description 获取各种编码的字符串长度
     */
    public static int getStringLength(String str, String code) throws UnsupportedEncodingException {
        int length = -1;
        if(isBlank(str)){
            return length;
        }
        byte[] bt = str.getBytes(code);
        length = bt.length;
        return length;
    }

    /**
     * @param str
     * @return 混合字符长度
     * @description 默认GBK编码下的字符串长度
     */
    public static int getStringLength(String str) {
        try {
            return getStringLength(str, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * @Description String不为空
     * @CreateDate
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * <p>Checks if a String is whitespace, empty ("") or null.</p>
     * <p>
     * <pre>
     * StringUtils.isBlank(null)      = true
     * StringUtils.isBlank("")        = true
     * StringUtils.isBlank(" ")       = true
     * StringUtils.isBlank("bob")     = false
     * StringUtils.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str the String to check, may be null
     * @return <code>true</code> if the String is null, empty or whitespace
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        if ("null".equalsIgnoreCase(str)) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if a String is not whitespace, empty ("") or null.
     *
     * @param str the String to check, may be null
     * @return the input <code>str<code/> if it's not blank, otherwise
     * throw {@link IllegalArgumentException}.
     * @throws IllegalArgumentException if the input <code>str<code/> is blank.
     */
    public static String requireNotBlank(String str) {
        if (isBlank(str)) {
            throw new IllegalArgumentException("Blank string");
        } else {
            return str;
        }
    }

    /**
     * Check if a String is not whitespace, empty ("") or null.
     *
     * @param str the String to check, may be null
     * @return the input <code>str<code/> if it's not blank, otherwise
     * throw {@link IllegalArgumentException}.
     * @throws IllegalArgumentException if the input <code>str<code/> is blank.
     */
    public static String requireNotBlank(String str, String msg) {
        if (isBlank(str)) {
            throw new IllegalArgumentException(msg);
        } else {
            return str;
        }
    }

    public static void checkNotBlank(String str) {
        if (isBlank(str)) {
            throw new IllegalArgumentException("Blank string");
        }
    }

    public static void checkNotBlank(String str, String msg) {
        if (isBlank(str)) {
            throw new IllegalArgumentException(msg);
        }
    }


    /**
     * Calls {@link String#getBytes(Charset)}
     *
     * @param string  The string to encode (if null, return null).
     * @param charset The {@link Charset} to encode the {@code String}
     * @return the encoded bytes
     */
    private static byte[] getBytes(String string, Charset charset) {
        checkNotBlank(string);
        ObjectUtil.checkNonNull(charset, "charset is null");
        return string.getBytes(charset);
    }

}
