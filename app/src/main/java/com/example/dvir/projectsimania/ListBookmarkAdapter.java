package com.example.dvir.projectsimania;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dvir.projectsimania.Bookmark;
import com.example.dvir.projectsimania.R;

import java.util.List;

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

    public int getCount() {
        return listMasechet.size();
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
        holder.txtName.setText(bookmark.getMasechet());
        holder.txtStrikes.setText("" + bookmark.getPage()); //using ("" +) instead of .toString()

        return convertView;
    }

    private class ViewHolder {
        TextView txtName;
        TextView txtStrikes;
    }

}
