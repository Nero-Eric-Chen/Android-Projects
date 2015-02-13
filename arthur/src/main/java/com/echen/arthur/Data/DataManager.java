package com.echen.arthur.Data;

import android.content.Context;
import android.provider.MediaStore;

import com.echen.androidcommon.Media.Audio;
import com.echen.androidcommon.Media.IMediaProvider;
import com.echen.androidcommon.Media.Image;
import com.echen.androidcommon.Media.MediaCenter;
import com.echen.androidcommon.Media.Video;

import java.util.List;

/**
 * Created by echen on 2015/2/12.
 */
public class DataManager {
    private Context context = null;
    private IMediaProvider imageProvider = null;
    private IMediaProvider videoProvider = null;
    private IMediaProvider audioProvider = null;

    private volatile static DataManager instance;
    public static  DataManager getInstance()
    {
        if (null == instance)
        {
            synchronized (DataManager.class)
            {
                if (null == instance)
                {
                    instance = new DataManager();
                }
            }
        }
        return instance;
    }

    public boolean init(Context context)
    {
        this.context = context;
        if(null == this.context)
            return false;
        imageProvider = MediaCenter.CreateMediaProvider(this.context, MediaCenter.MediaType.Image);
        videoProvider = MediaCenter.CreateMediaProvider(this.context, MediaCenter.MediaType.Video);
        audioProvider = MediaCenter.CreateMediaProvider(this.context, MediaCenter.MediaType.Audio);
        if (null == imageProvider || null == videoProvider || null == audioProvider)
            return false;
        return true;
    }

    public void uninit()
    {

    }

    public List<Image> GetImages()
    {
        return (List<Image>)imageProvider.getList();
    }

    public List<Video> GetVideos()
    {
        return (List<Video>)videoProvider.getList();
    }

    public List<Audio> GetAudios()
    {
        return (List<Audio>)audioProvider.getList();
    }
}
