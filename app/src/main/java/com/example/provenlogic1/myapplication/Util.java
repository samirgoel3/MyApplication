package com.example.provenlogic1.myapplication;


public class Util {

    public static final String EXTRA_MESSAGE = "message";
    public static final String PROPERTY_REG_ID = "registration_id";
    public static final String PROPERTY_APP_VERSION = "appVersion";
    public static final String EMAIL = "email";
    public static final String USER_NAME = "user_name";

    public final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public final static String SENDER_ID = "264689151574";

    public static String base_url = "http://apporio.in/gcm_demo/";

    public final static String  register_url=base_url+"register.php?name=";
    public final static String  send_chat_url=base_url+"sendChatmessage.php?email_id=";


}
