package com.example.provenlogic1.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Dell on 3/19/2015.
 */
public class ChatAdapter extends BaseAdapter {

    Context context;
    int resource;
    public ChatManager cm ;
    List<ChatTable>  data ;


    public ChatAdapter(Context context, int resource) {

        this.context=context;
        this.resource=resource;
        cm = new ChatManager(context);
        data =  cm.getFullChat();
    }




    private class ViewHolder{
        TextView textView_left_chat;
        TextView textView_right_chat;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if(convertView==null){
           LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.chat_view,null);
            holder = new ViewHolder();
            holder.textView_left_chat = (TextView) convertView.findViewById(R.id.textView_left_chat);
            holder.textView_right_chat = (TextView) convertView.findViewById(R.id.textView_right_chat);

            convertView.setTag(holder);
        }else{

            holder = (ViewHolder) convertView.getTag();

        }

        Log.d("Chat Size ","message "+ data.size());



//            if(chat_data.get(position).getType().equals("sent")){
//
//                holder.textView_left_chat.setText(chat_data.get(position).getMessage());
//                holder.textView_right_chat.setVisibility(View.GONE);
//                holder.textView_left_chat.setVisibility(View.VISIBLE);
//            }else{
//
//                holder.textView_right_chat.setText(chat_data.get(position).getMessage());
//                holder.textView_left_chat.setVisibility(View.GONE);
//                holder.textView_right_chat.setVisibility(View.VISIBLE);
//            }

        Toast.makeText(context , ""+data.get(position).MessageType , Toast.LENGTH_SHORT).show();



        if(data.get(position).MessageType.equals("sent")){
                holder.textView_left_chat.setText(data.get(position).getMessage());
                holder.textView_right_chat.setVisibility(View.GONE);
                holder.textView_left_chat.setVisibility(View.VISIBLE);
            }else{

                holder.textView_right_chat.setText(data.get(position).getMessage());
                holder.textView_left_chat.setVisibility(View.GONE);
                holder.textView_right_chat.setVisibility(View.VISIBLE);
            }


        return convertView;
    }
}
