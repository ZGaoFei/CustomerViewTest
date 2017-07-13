package cn.com.nggirl.bigimagetestapp.mylistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.com.nggirl.bigimagetestapp.R;

/**
 * Created by zgfei on 2017/7/13.
 */

public class MyAdapter extends BaseAdapter {
    private List<String> list;
    private Context context;

    public MyAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_view, null);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.tv_item_lv);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(list.get(position));

        return convertView;
    }

    static class ViewHolder {
        private TextView textView;
    }
}
