package com.tw.utils.base;

import android.content.ContentValues;

import java.util.Collection;
import java.util.Map;


public final class CollectionUtil {
    private CollectionUtil() {
        throw new IllegalArgumentException("No instance");
    }

    /**
     * Verifies if the collection is not null or length > 0 throws {@link IllegalArgumentException} with the given message
     *
     * @param collection
     * @param message
     */
    public static void checkNotEmpty(Collection collection, String message) {
        if (isEmpty(collection)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Verifies if the map is not null or length > 0 throws {@link IllegalArgumentException} with the given message
     *
     * @param map
     * @param message
     */
    public static void checkNotEmpty(Map map, String message) {
        if (isEmpty(map)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Verifies if the map is not null or length > 0 throws {@link IllegalArgumentException} with the given message
     *
     * @param map
     * @param message
     * @return map
     */
    public static <T> Map<T, T> requireNotEmpty(Map<T, T> map, String message) {
        if (isEmpty(map)) {
            throw new IllegalArgumentException(message);
        }
        return map;
    }

    /**
     * Returns true if the collection is null or 0-length.
     *
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection collection) {
        return (collection == null || collection.isEmpty());
    }

    /**
     * Returns true if the collection is not null or length > 0.
     *
     * @param collection
     * @return
     */
    public static boolean notEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    /**
     * Returns true if the map is null or 0-length.
     *
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return (map == null || map.isEmpty());
    }

    /**
     * Returns true if the map is not null or length > 0.
     *
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> boolean notEmpty(Map<K, V> map) {
        return !isEmpty(map);
    }

    /**
     * Returns true if the contentValues is null or 0-length.
     *
     * @param values
     * @return
     */
    public static boolean isEmpty(ContentValues values) {
        return (values == null || values.size() == 0);
    }

    /**
     * Returns true if the map is not null or length > 0.
     *
     * @param values
     * @return
     */
    public static boolean notEmpty(ContentValues values) {
        return !isEmpty(values);
    }
}
