package controller;
import java.util.regex.Pattern;
public class Validator {
    static private String mailPattern = "\\w{3,}[@]kmail\\.com";
    static private String passwordPattern = ".{4,8}";
    static private String mobileNumberPattern = "\\+[\\d]{12}";

    public static boolean validateMailId(String mailId)
    {
        return Pattern.matches(mailPattern, mailId);
    }
    public static boolean validatePassword(String password)
    {
        return Pattern.matches(passwordPattern, password);
    }
    public static boolean validateMobileNumber(String mobNumber)
    {
        return Pattern.matches(mobileNumberPattern, mobNumber);
    }
    
}
