package com.echen.arthur;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import com.echen.arthur.ActivityAdapter.ImageAdapter;
import com.echen.arthur.Data.DataManager;

/**
 * Created by echen on 2015/2/17.
 */
public class FilesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);
        GridView gridView = (GridView)findViewById(R.id.imagesContainer);
        ImageAdapter adapter = new ImageAdapter(this, DataManager.getInstance().getImages());
        gridView.setAdapter(adapter);
    }
}
