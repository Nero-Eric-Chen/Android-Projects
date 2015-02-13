package com.echen.arthur.Model;

import com.echen.androidcommon.Media.Image;
import com.echen.androidcommon.Model.FileSystemInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by echen on 2015/2/12.
 */
public class Folder {
    private String name = "";
    private String path = "";
    private List<FileSystemInfo> children = new ArrayList<>();

    public Folder(String path)
    {
        this.path = path;
        name = getFolderName(this.path);
    }

    private String getFolderName(String path)
    {
        return  path.substring(path.lastIndexOf("/")+1, path.length());
    }

    public String getName() {return name;}
    public void setName(String name){this.name = name;}

    public String getPath() {return path;}
    public void setPath(String path){this.path = path;}

    public List<FileSystemInfo> getChildren() {return children;}
}
