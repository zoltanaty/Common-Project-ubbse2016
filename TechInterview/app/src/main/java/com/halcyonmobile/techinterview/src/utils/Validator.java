package com.halcyonmobile.techinterview.src.utils;

public class Validator{

    public static boolean isValidEmail(String email){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidName(String name){
        return ((name != null )&& (!name.equals("")) && (name.length() > 3));
    }

}
