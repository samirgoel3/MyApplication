package com.example.provenlogic1.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by spinnosolutions on 12/28/15.
 */
public class ChekChatTable {


    Context con ;
    boolean checkid = false ;

    @SuppressLint("LongLogTag")
    public boolean isuserexsist(String username){

        try {
            List<ChatTable> templist = ChatTable.find(ChatTable.class, " User_Name = ?", username);
            Log.d("Size of templist ", "" + templist.size());
            if (templist.size() == 1) {
                checkid = true;
            } else {
                checkid = false;
            }

        } catch (Exception e) {
            Log.d("Product Id is not saved in Databse ", "Id is not saved in Databse ");
        }

        return checkid;

    }

}
