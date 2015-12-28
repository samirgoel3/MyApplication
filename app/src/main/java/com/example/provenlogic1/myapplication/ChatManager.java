package com.example.provenlogic1.myapplication;

import android.content.Context;

import java.util.List;

import de.greenrobot.event.EventBus;

/**
 * Created by spinnosolutions on 12/28/15.
 */
public class ChatManager {

    Context con ;
    public static EventBus bus = EventBus.getDefault();


    public ChatManager(Context con ){
       this.con = con ;

    }


    public  static void createNewRowInChatTable(String UserName , String UserEmail , String Message , String MessageType  ) {
        new ChatTable(  UserName ,  UserEmail ,  Message ,  MessageType).save();
        ChatEvent event  = null;
        event = new ChatEvent("show");
        bus.post(event);

    }


    public static void receiveNewRowInChatTable(String UserName , String UserEmail , String Message , String MessageType ){
        new ChatTable(  UserName ,  UserEmail ,  Message ,  MessageType).save();
    }


    public static List<ChatTable> getFullChat(){
        List<ChatTable> templist = ChatTable.listAll(ChatTable.class);
        return templist ;
    }








}
