package com.echen.androidcommon.Utility;

/**
 * Created by echen on 2015/2/12.
 */
public class PathUtility {
    public static String getParent(String path)
    {
        String parent = "";
        if (null == path)
            return parent;

        if (!path.contains("/"))
            return parent;
        parent = path.substring(0, path.lastIndexOf("/"));
        return parent;
    }
}
