package com.example.provenlogic1.myapplication;

import com.orm.SugarRecord;

/**
 * Created by spinnosolutions on 12/28/15.
 */
public class ChatTable extends SugarRecord<ChatTable> {


    public String UserName ;
    public String UserEmal ;
    public String Message ;
    public String MessageType ;





    public String getUserName() {
        return UserName;
    }

    public String getUserEmal() {
        return UserEmal;
    }

    public String getMessage() {
        return Message;
    }

    public String getMessageType() {
        return MessageType;
    }



    public ChatTable(){

    }

    public ChatTable(String UserName , String UserEmail , String Message , String MessageType){

        this.UserName =UserName;
        this.UserEmal = UserEmail ;
        this.Message  = Message ;
        this.MessageType   = MessageType ;

    }
}
