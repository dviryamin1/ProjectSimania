package com.example.dvir.projectsimania;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dvir.projectsimania.Bookmark;
import com.example.dvir.projectsimania.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;

/**
 * Created by dvir on 12/6/2015.
 */
public class ListBookmarkAdapter extends BaseAdapter {
    Context context;
    protected List<Bookmark> listMasechet;
    LayoutInflater inflater;

    public ListBookmarkAdapter(Context context, List<Bookmark> listMasechet) {
        this.listMasechet = listMasechet;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public static ArrayList<Bookmark> bmListFromArray(String[] bmArr) {
        ArrayList<Bookmark> list = new ArrayList<Bookmark>();
        String[] res;
        for (int i = 0; i < bmArr.length; i++) {
            res = bmArr[i].split(Pattern.quote("|"));
            list.add(new Bookmark(res[0], res[1], res[2]));
        }
        return list;
    }

    public int getCount() {
        return listMasechet.size();
    }

    public String[] getValues() {
        ArrayList<String> values = new ArrayList<String>();

        for (int i = 0; i < this.getCount(); i++) {
            values.add(listMasechet.get(i).getLabel() + "|" + listMasechet.get(i).getMasechet() + "|" + listMasechet.get(i).getMasechet() + "|" + listMasechet.get(i).getPage());
        }
        String[] array = values.toArray(new String[values.size()]);
        return array;
    }

    public Bookmark getItem(int position) {
        return listMasechet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {

            holder = new ViewHolder();
            convertView = this.inflater.inflate(R.layout.bookmark_settings,
                    parent, false);

            holder.txtName = (TextView) convertView
                    .findViewById(R.id.txt_name);
            holder.txtStrikes = (TextView) convertView
                    .findViewById(R.id.txt_num_strikes);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Bookmark bookmark = listMasechet.get(position);
        holder.txtName.setText(bookmark.getLabel());
        holder.txtStrikes.setText(bookmark.getMasechet() + " " + bookmark.getPage()); //using ("" +) instead of .toString()

        return convertView;
    }

    private class ViewHolder {
        TextView txtName;
        TextView txtStrikes;
    }

}
