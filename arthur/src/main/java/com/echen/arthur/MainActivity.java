package com.echen.arthur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.echen.androidcommon.Media.MediaCenter;
import com.echen.arthur.Data.DataManager;
import com.echen.arthur.Utility.StringConstant;


public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        DataManager.getInstance().init(getApplicationContext());

//        LoadDataAsyncTask loadDataAsyncTask = new LoadDataAsyncTask(proProgressDialog);
//        loadDataAsyncTask.execute();
    }

    @Override
    protected void onDestroy() {
        DataManager.getInstance().uninit();
        super.onDestroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onImagesClick(View view)
    {
        Intent intent = new Intent(MainActivity.this, FoldersActivity.class);
        intent.putExtra(StringConstant.CATEGORY_IMAGE, MediaCenter.MediaType.Image.toString());
        startActivity(intent);
    }

    public void onVideosClick(View view)
    {
        Intent intent = new Intent(MainActivity.this, FoldersActivity.class);
        intent.putExtra(StringConstant.CATEGORY_IMAGE, MediaCenter.MediaType.Video.toString());
        startActivity(intent);
    }

    public void onAudiosClick(View view)
    {
        Intent intent = new Intent(MainActivity.this, FoldersActivity.class);
        intent.putExtra(StringConstant.CATEGORY_IMAGE, MediaCenter.MediaType.Audio.toString());
        startActivity(intent);
    }
}
