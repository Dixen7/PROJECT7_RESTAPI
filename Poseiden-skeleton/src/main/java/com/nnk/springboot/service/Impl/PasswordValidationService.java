package com.nnk.springboot.service.Impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidationService {

    public static boolean passwordValidator(String password) {
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{8,20}$";

        if(password==null) return false;

        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);

        return m.matches();
    }
}