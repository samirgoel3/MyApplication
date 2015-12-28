package com.example.provenlogic1.myapplication;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import de.greenrobot.event.EventBus;


public class ChatActivity extends Activity {

    EditText editText_mail_id;
    EditText editText_chat_message;
   public  static  ListView listView_chat_messages;
    LinearLayout button_send_chat;

    public ChatManager  cm ;

   // BroadcastReceiver recieve_chat;


    StringRequest sr ;
    RequestQueue queue ;

    private EventBus bus = EventBus.getDefault();
   public static ChatAdapter chatAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        queue = VolleySingleton.getInstance(ChatActivity.this).getRequestQueue();
        bus.register(this);
        cm = new ChatManager(ChatActivity.this);

        editText_mail_id= (EditText) findViewById(R.id.editText_mail_id);
        editText_chat_message= (EditText) findViewById(R.id.editText_chat_message);
        listView_chat_messages= (ListView) findViewById(R.id.listView_chat_messages);
        button_send_chat= (LinearLayout) findViewById(R.id.button_send_chat);

         chatAdapter =new ChatAdapter(ChatActivity.this,R.layout.chat_view);
        listView_chat_messages.setAdapter(chatAdapter);



        button_send_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editText_chat_message.getText().toString();
                cm.createNewRowInChatTable("My Name", "My eamil ", message, "sent");
                //  showChat();
                sendChat();
                editText_chat_message.setText("");


            }
        });


        BroadcastReceiver recieve_chat = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                String message = intent.getStringExtra("message");

                Log.d("pavan", "in local braod " + message);
                showChat();


            }
        };

        LocalBroadcastManager.getInstance(this).registerReceiver(recieve_chat, new IntentFilter("message_recieved"));











        



    }

    private void showChat(){

        ChatAdapter chatAdapter =new ChatAdapter(ChatActivity.this,R.layout.chat_view);
        listView_chat_messages.setAdapter(chatAdapter);
        chatAdapter.notifyDataSetChanged();

    }

    //This is the handler that will manager to process the broadcast intent
   private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            // Extract data included in the Intent
            String message = intent.getStringExtra("message");
            showChat();

            //do other stuff here
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        ActivityStatus.ChatActivity_isOpen = true ;
        ChatActivity.this.registerReceiver(mMessageReceiver, new IntentFilter("unique_name"));


    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        bus.unregister(this);
        ActivityStatus.ChatActivity_isOpen = false ;
        ChatActivity.this.unregisterReceiver(mMessageReceiver);

    }


    public void onEvent(ChatEvent event){
        showChat();

        Toast.makeText(ChatActivity.this, "received ", Toast.LENGTH_SHORT).show();
    }


    public void sendChat( ){
        String url = Util.send_chat_url+editText_mail_id.getText().toString() + "&message="+editText_chat_message.getText().toString();

        url=url.replace(" ","%20");
        Log.i("Send Chat Message API", "url " + url);

        sr = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        sr.setRetryPolicy(new DefaultRetryPolicy(
                30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(sr);
    }



}
