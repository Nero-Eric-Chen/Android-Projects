package com.echen.androidcommon.Utility;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;

/**
 * Created by echen on 2015/3/6.
 */
public class ImageUtility {
    public static int computeSampleSize(BitmapFactory.Options options,
                                        int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength,
                maxNumOfPixels);

        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }

    private static int computeInitialSampleSize(BitmapFactory.Options options,
                                                int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;

        int lowerBound = (maxNumOfPixels == -1) ? 1 :
                (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 :
                (int) Math.min(Math.floor(w / minSideLength),
                        Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            return lowerBound;
        }

        if ((maxNumOfPixels == -1) &&
                (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    public static Bitmap autoLoadImageFromUrl(String imageUrl, int minSideLength, int maxNumOfPixels){
        Bitmap bmp = null;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageUrl, opts);
        opts.inSampleSize = ImageUtility.computeSampleSize(opts, minSideLength, maxNumOfPixels);

        opts.inJustDecodeBounds = false;
        try {
            bmp = BitmapFactory.decodeFile(imageUrl, opts);
        } catch (OutOfMemoryError err) {
        }
        return bmp;
    }

    public static String getThumbnailUrl(ContentResolver cr, long origId, int kind)
    {
        String uri = "";
        Cursor cursor = MediaStore.Images.Thumbnails.queryMiniThumbnail(
                cr, origId,kind,null);
        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            uri = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Thumbnails.DATA));
        }
        cursor.close();
        return uri;
    }
}
