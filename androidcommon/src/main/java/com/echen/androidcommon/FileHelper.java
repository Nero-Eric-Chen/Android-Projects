package com.echen.androidcommon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by echen on 2015/2/15.
 */
public class FileHelper {
    public static boolean CopyFile(File fromFile, File toFile) {
        boolean bRel = true;
        try {
            FileInputStream inputStream = new FileInputStream(fromFile);
            FileOutputStream outputStream = new FileOutputStream(toFile);
            byte bt[] = new byte[1024];
            int c;
            while ((c = inputStream.read(bt)) > 0) {
                outputStream.write(bt, 0, c); //Write content into new file
            }
            inputStream.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            bRel = false;
            e.printStackTrace();
        } catch (IOException e) {
            bRel = false;
            e.printStackTrace();
        }
        return bRel;
    }
}
