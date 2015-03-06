package com.echen.arthur;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.echen.androidcommon.Media.MediaCenter;
import com.echen.androidcommon.Model.FileSystemInfo;
import com.echen.androidcommon.Utility.PathUtility;
import com.echen.arthur.ActivityAdapter.FolderAdapter;
import com.echen.arthur.Data.DataManager;
import com.echen.androidcommon.Model.Folder;
import com.echen.arthur.Utility.StringConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by echen on 2015/2/11.
 */
public class FoldersActivity extends Activity {
    private MediaCenter.MediaType category;
    private FolderAdapter adapter;
    private List<Folder> folderList = new ArrayList<>();
    private final int MSG_LOADCOMPLETED = 1;
    private ProgressDialog proProgressDialog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folders);
//        setTitle("FoldersActivity");

        Intent intent = getIntent();
        String strCategory = intent.getStringExtra(StringConstant.CATEGORY_IMAGE);
        category = MediaCenter.MediaType.valueOf(strCategory);

        GridView gridView = (GridView) findViewById(R.id.foldersContainer);
        if (null != gridView) {
            adapter = new FolderAdapter(this, folderList);
            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(gridViewItemClickListener);
        }

        proProgressDialog = new ProgressDialog(this);
        proProgressDialog.show();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                GetContents(category);
                Message msg = new Message();
                msg.what = MSG_LOADCOMPLETED;
                loadContentsEventHandler.sendMessage(msg);
            }
        });
        thread.start();
    }

    private Handler loadContentsEventHandler = new Handler() {

        /**
         * Subclasses must implement this to receive messages.
         *
         * @param msg
         */
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOADCOMPLETED: {
                    adapter.notifyDataSetChanged();
                    if (null != proProgressDialog)
                        proProgressDialog.dismiss();
                }
                break;
            }
            super.handleMessage(msg);
        }
    };

    private void GetContents(MediaCenter.MediaType mediaType) {
        List<?> list = DataManager.getInstance().getList(mediaType);
        for (Object item : list) {
            FileSystemInfo file = (FileSystemInfo) item;
            if (null == file)
                continue;
            String path = file.getPath();
            if (path.isEmpty())
                continue;
            String parentPath = PathUtility.getParent(path);
            boolean isFind = false;

            for (Folder folder : folderList) {
                if (parentPath.equalsIgnoreCase(folder.getPath())) {
                    isFind = true;
                    folder.getChildren().add(file);
                    break;
                }
            }
            if (!isFind) {
                Folder newFolder = new Folder(parentPath);
                newFolder.getChildren().add(file);
                folderList.add((newFolder));
            }
        }
    }

    private GridView.OnItemClickListener gridViewItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Folder folder = (Folder) parent.getAdapter().getItem(position);
            Intent intent = new Intent(FoldersActivity.this, FilesActivity.class);
            Bundle bundleObject = new Bundle();
            bundleObject.putSerializable(StringConstant.IMAGES, (ArrayList<?>)folder.getChildren());
            intent.putExtras(bundleObject);
            startActivity(intent);

//            if (folder.getChildren().size() > 0) {
//                FileSystemInfo fileSystemInfo = folder.getChildren().get(0);
//                try {
//                    File fromFile = new File(fileSystemInfo.getPath());
//                    FileInputStream inputStream = new FileInputStream(fromFile);
//                    File sdCard = Environment.getExternalStorageDirectory();
//                    File directory = new File(sdCard.getAbsolutePath() + "/Arhtur");
//                    if (!directory.exists()) {
//                        directory.mkdirs();
//                    }
////                    AESUtility.encryptFile(toFile,toFile.getPath(),".jpg", "ckk");
////                    File decrptyedFile = AESUtility.encryptFile(fromFile,fileSystemInfo.getTitle(),".jpg",directory, "ckk");
//
//                    File decrptyedFile = new File(directory.getAbsolutePath() + "/A Star (15)-282228491.jpg");
//                    AESUtility.decryptFile(decrptyedFile, "decrptyedFile", ".jpg", directory, "ckk");
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }
////                catch (IOException e)
////                {
////                    e.printStackTrace();
////                }
//                finally {
//                    int i = 0;
//                    i++;
//                }
//            }
        }
    };
}
