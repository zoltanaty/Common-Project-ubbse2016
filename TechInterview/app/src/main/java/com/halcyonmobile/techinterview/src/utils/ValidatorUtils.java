package com.halcyonmobile.techinterview.src.utils;

public abstract class ValidatorUtils {

    public static boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isValidName(String name) {
        //TODO CR: This method could be improved. It might be a better idea to trim the string and then check for the number of whitespaces. [Peter]
        return ((name != null) && (!name.equals("")) && (name.length() > 3));
    }
}