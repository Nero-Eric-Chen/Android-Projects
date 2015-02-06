package com.example.echen.myapplication.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.echen.androidcommon.Media.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by echen on 2015/2/6.
 */
public class ImageAdapter extends BaseAdapter {
    protected List<Image> m_images = new ArrayList<>();

    @Override
    public int getCount() {
        return m_images.size();
    }

    @Override
    public Object getItem(int position) {
        return m_images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
