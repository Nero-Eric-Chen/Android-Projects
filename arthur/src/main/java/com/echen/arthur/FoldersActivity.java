package com.echen.arthur;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.echen.androidcommon.Crypto.AESUtility;
import com.echen.androidcommon.Media.Image;
import com.echen.androidcommon.Model.FileSystemInfo;
import com.echen.androidcommon.PathUtility;
import com.echen.arthur.ActivityAdapter.FolderAdapter;
import com.echen.arthur.Data.DataManager;
import com.echen.arthur.Model.Folder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by echen on 2015/2/11.
 */
public class FoldersActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folders);
//        setTitle("FoldersActivity");

        List<Folder> folderList = new ArrayList<>();
        List<Image> imageList = DataManager.getInstance().GetImages();
        for(Image image : imageList)
        {
            String path = image.getPath();
            if(path.isEmpty())
                continue;
            String parentPath = PathUtility.getParent(path);
            boolean isFind = false;
            for(Folder folder : folderList)
            {
                if(parentPath.equalsIgnoreCase(folder.getPath()))
                {
                    isFind = true;
                    folder.getChildren().add(image);
                    break;
                }
            }
            if(!isFind) {
                Folder newFolder = new Folder(parentPath);
                folderList.add((newFolder));
                newFolder.getChildren().add(image);
            }
        }


        GridView gridView = (GridView)findViewById(R.id.foldersContainer);
        if (null != gridView) {
            FolderAdapter adapter = new FolderAdapter(this, folderList);
            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(gridViewItemClickListener);
        }
    }

    private GridView.OnItemClickListener gridViewItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Folder folder = (Folder)parent.getAdapter().getItem(position);
            if (folder.getChildren().size() > 0)
            {
                FileSystemInfo fileSystemInfo = folder.getChildren().get(0);
                try {
                    File fromFile = new File(fileSystemInfo.getPath());
                    FileInputStream inputStream = new FileInputStream(fromFile);
                    File sdCard = Environment.getExternalStorageDirectory();
                    File directory = new File (sdCard.getAbsolutePath() +  "/Arhtur");
                    if (!directory.exists()) {
                        directory.mkdirs();
                    }
                    File toFile = new File(directory, fileSystemInfo.getDisplayName());
                    boolean isCreated = false;
                    if (!toFile.exists()) {
                        isCreated = toFile.createNewFile();
                    }
                    FileOutputStream outputStream = new FileOutputStream(toFile);
                    byte bt[] = new byte[1024];
                    int c;
                    while ((c = inputStream.read(bt)) > 0) {
                        outputStream.write(bt, 0, c); //将内容写到新文件当中
                        }
                        inputStream.close();
                        outputStream.close();
                    AESUtility.encryptFile(toFile,".jpg", "ckk");
                }catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                finally {
                    int i =0;
                    i++;
                }
            }
        }
    };
}
