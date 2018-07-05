package com.tw.utils.base;


public final class ArrayUtil {
    private ArrayUtil() {
        throw new IllegalStateException("No instance!");
    }

    public static <T> boolean isEmpty(T[] arrays) {
        if (arrays == null || arrays.length <= 0) {
            return true;
        }
        return false;
    }

    public static <T> boolean notEmpty(T[] arrays) {
        return !isEmpty(arrays);
    }

    public static <T> T[] requireNotEmpty(T[] arrays) {
        if (isEmpty(arrays)) {
            throw new IllegalArgumentException();
        }
        return arrays;
    }

    public static <T> T[] requireNotEmpty(T[] arrays, String message) {
        if (isEmpty(arrays)) {
            throw new IllegalArgumentException(message);
        }
        return arrays;
    }

    public static <T> void checkNotEmpty(T[] arrays) {
        if (isEmpty(arrays)) {
            throw new IllegalArgumentException();
        }
    }

    public static <T> void checkNotEmpty(T[] arrays, String message) {
        if (isEmpty(arrays)) {
            throw new IllegalArgumentException(message);
        }
    }

    public static boolean isEmpty(int[] arrays) {
        if (arrays == null || arrays.length <= 0) {
            return true;
        }
        return false;
    }

    public static boolean notEmpty(int[] arrays) {
        return !isEmpty(arrays);
    }

    public static int[] requireNotEmpty(int[] arrays) {
        if (isEmpty(arrays)) {
            throw new IllegalArgumentException();
        }
        return arrays;
    }

    public static int[] requireNotEmpty(int[] arrays, String message) {
        if (isEmpty(arrays)) {
            throw new IllegalArgumentException(message);
        }
        return arrays;
    }

    public static void checkNotEmpty(int[] arrays) {
        if (isEmpty(arrays)) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkNotEmpty(int[] arrays, String message) {
        if (isEmpty(arrays)) {
            throw new IllegalArgumentException(message);
        }
    }
}
