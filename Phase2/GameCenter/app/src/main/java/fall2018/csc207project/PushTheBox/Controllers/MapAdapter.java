package fall2018.csc207project.PushTheBox.Controllers;

/*
Taken from:
https://github.com/DaveNOTDavid/sample-puzzle/blob/master/app/src/main/java/com/davenotdavid/samplepuzzle/CustomAdapter.java

This Class is an overwrite of the Base Adapter class
It is designed to aid setting the button sizes and positions in the GridView
 */


import android.content.Context;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fall2018.csc207project.PushTheBox.Models.Box;

/**
 * The CustomAdapter that maps the buttons to the customized grid view.
 */
public class MapAdapter extends BaseAdapter {
    private Integer[] tileBgs;
    private int columnDim;
    private Context context;
    private SparseIntArray person = new SparseIntArray();
    private SparseIntArray boxes = new SparseIntArray();

    public MapAdapter(Integer[] tileBgs, int columnDim, Context context) {
        this.tileBgs = tileBgs;
        this.columnDim = columnDim;
        this.context = context;
    }

    public void setPerson(SparseIntArray personPosToImage){
        this.person = personPosToImage.clone();
    }

    public void setBoxesList(SparseIntArray boxes){
        this.boxes = boxes.clone();
    }

    @Override
    public int getCount() {
        return tileBgs.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        if(person.get(position) != 0){
            imageView.setImageResource(person.get(position));
        }else if (boxes.get(position) != 0){
            imageView.setImageResource(boxes.get(position));
        }else {
            imageView.setImageResource(tileBgs[position]);
        }
        android.widget.AbsListView.LayoutParams params =
                new android.widget.AbsListView.LayoutParams(columnDim, columnDim);
        imageView.setLayoutParams(params);
        return imageView;
    }
}
