package cn.com.nggirl.bigimagetestapp.textview;


import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.com.nggirl.bigimagetestapp.R;
import cn.com.nggirl.bigimagetestapp.textview.view.ExpandableTextView;

public class ListViewAdapter extends BaseAdapter implements ExpandableTextView.OnExpandStateChangeListener {
    private List<String> list;

    private SparseBooleanArray array;

    public ListViewAdapter() {
        list = new ArrayList<>();
        array = new SparseBooleanArray();
    }

    public void setData(List<String> list) {
        this.list.clear();
        if (list != null) {
            this.list.addAll(list);
            setArray();
        }

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_expand_list_view, null);
            viewHolder.expandableTextView = (ExpandableTextView) convertView.findViewById(R.id.expand_text_view);
            viewHolder.expandableTextView.setOnExpandStateChangeListener(this);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.expandableTextView.setTag(position);

        viewHolder.expandableTextView.setText(list.get(position), array, position);
        return convertView;
    }

    @Override
    public void onExpandStateChanged(TextView textView, boolean isExpanded) {
        int position = (int) textView.getTag();
        array.put(position, !isExpanded);
    }

    static class ViewHolder {
        ExpandableTextView expandableTextView;
    }

    private void setArray() {
        for (int i = 0; i < list.size(); i++) {
            array.put(i, true);
        }
    }
}
