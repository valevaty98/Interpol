package by.training.interpol.util;

import java.util.regex.Pattern;

public class ParamsValidator {
    private final static String LOGIN_PATTERN = "^[\\w.-]{1,19}[a-zA-Z\\d]$";
    private final static String PASSWORD_PATTERN = "^[\\w]{4,20}$";
    private final static String EMAIL_PATTERN = "^([\\w\\-.]+)@([\\w\\-]+)\\.([a-zA-Z]{2,5})$";
    private final static int MAX_SUBJECT_LENGTH = 50;
    private final static int MAX_MESSAGE_LENGTH = 500;

    public static boolean isValidEmail(String email) {
        Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
        return email != null && emailPattern.matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        Pattern passwordPattern = Pattern.compile(PASSWORD_PATTERN);
        return password != null && passwordPattern.matcher(password).matches();
    }

    public static boolean isValidLogin(String login) {
        Pattern loginPattern = Pattern.compile(LOGIN_PATTERN);
        return login != null && loginPattern.matcher(login).matches();
    }

    public static boolean isValidSubject(String subject) {
        return subject != null && (subject.length() <= MAX_SUBJECT_LENGTH);
    }

    public static boolean isValidMessage(String message) {
        return message != null && (message.length() <= MAX_MESSAGE_LENGTH);
    }
}
