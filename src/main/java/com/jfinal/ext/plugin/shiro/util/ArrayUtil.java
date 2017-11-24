package com.jfinal.ext.plugin.shiro.util;

public class ArrayUtil {
    /**
     * 数组是否为非空
     *
     * @param array 数组
     * @return 是否为非空
     */
    public static <T> boolean isNotEmpty(final T[] array) {
        return (array != null && array.length != 0);
    }

}
