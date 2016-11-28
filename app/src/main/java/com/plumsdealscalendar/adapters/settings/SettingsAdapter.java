package com.plumsdealscalendar.adapters.settings;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.plumsdealscalendar.R;
import com.plumsdealscalendar.models.login.Payload;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by NickNb on 28.11.2016.
 */
public class SettingsAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;

    List<String> list;

    public SettingsAdapter(Context context, Payload payload){
        this.context = context;

        list = new ArrayList<>();
        list.add(payload.getName());
        list.add(payload.getBirthday());
        list.add(payload.getEmail());
        list.add(payload.getName());
        list.add(payload.getName());
        list.add(payload.getName());
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null)
            view = inflater.inflate(R.layout.listitem_settings, viewGroup, false);
        TextView text = (TextView)view.findViewById(R.id.textView);
        text.setText((String)getItem(i));
        return view;
    }
}
