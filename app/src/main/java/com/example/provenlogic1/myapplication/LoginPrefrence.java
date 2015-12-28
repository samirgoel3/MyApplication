package com.example.provenlogic1.myapplication;

import com.lacronicus.easydatastorelib.Preference;
import com.lacronicus.easydatastorelib.StringEntry;

/**
 * Created by spinnosolutions on 12/28/15.
 */
public interface LoginPrefrence {

    @Preference("user_name")
    StringEntry user_name();

    @Preference("user_mail")
    StringEntry user_mail();



}
