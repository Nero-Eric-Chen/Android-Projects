package com.echen.arthur.Data;

import android.app.Application;
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.widget.ImageView;

/**
 * Created by echen on 2015/2/17.
 */
public class AsyncImageLoader {
    private final String TAG = "AsyncImageLoader";
    private HashMap<String, SoftReference<Drawable>> imageCache;
    private BlockingQueue queue;
    private ThreadPoolExecutor executor;

    public interface ImageCallback {
        public void imageLoaded(Drawable imageDrawable, String imageUrl, ImageView imageView);
    }

    public AsyncImageLoader() {
        imageCache = new HashMap<String, SoftReference<Drawable>>();
        queue = new LinkedBlockingDeque();
        executor = new ThreadPoolExecutor(1, 50, 180, TimeUnit.SECONDS, queue);
    }

    public Drawable loadDrawable(final Context context, final String imageUrl, final ImageView imageView, final ImageCallback imageCallback) {
        if (imageCache.containsKey(imageUrl)) {
            SoftReference<Drawable> softReference = imageCache.get(imageUrl);
            Drawable drawable = softReference.get();
            if (null != drawable)
                return drawable;
        }
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                imageCallback.imageLoaded((Drawable) msg.obj, imageUrl, imageView);
            }
        };

        executor.execute(new Runnable() {
            @Override
            public void run() {
                Drawable drawable = loadImageFromUrl(context, imageUrl);
                imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
                Message msg = handler.obtainMessage(0, drawable);
                handler.sendMessage(msg);
            }
        });

        return null;
    }

    public static Drawable loadImageFromUrl(Context context, String imageUrl) {
        Drawable drawable = Drawable.createFromPath(imageUrl);
        return drawable;
    }
}
