package com.example.crossfire.myfourthapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by CrossFire on 2016/7/1.
 */
class ContactAdaptor extends BaseAdapter{

    private Context context;
    private List<String> Names;
    private List<String> Numbers;

    public ContactAdaptor(List<String> names,List<String> numbers,Context context){
        Names = names;
        Numbers = numbers;
        this.context = context;
    }

    @Override
    public Object getItem(int position){
        return null;
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public int getCount(){
        return Names.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list1_item,parent,false);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.list_name);
            holder.number = (TextView) convertView.findViewById(R.id.list_phone);
            convertView.setTag(holder);   //将Holder存储到convertView中
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(Names.get(position));
        holder.number.setText(Numbers.get(position));
        return convertView;
    }

    static class ViewHolder{
        TextView name;
        TextView number;
    }
}
