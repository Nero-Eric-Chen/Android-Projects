package com.echen.androidcommon.Model;

/**
 * Created by echen on 2015/3/5.
 */
public class File extends FileSystemInfo {

    public File(){
        super();
    }

    public File(int id, String title, String displayName, String mimeType,
                String path, long size)
    {
        super(id, title, displayName, mimeType, path, size);
    }

}
