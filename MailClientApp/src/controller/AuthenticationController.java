package controller;

    //MailDomain initialized here for first time
public class AuthenticationController {
    public static boolean signup(String mailid,String pwd,String mobNumber) {
        return model.MailDomain.getInstance().signUp(mailid, pwd,mobNumber);
    } 
    public static boolean login(String mailid,String pwd) {
        return model.MailDomain.getInstance().login(mailid, pwd);
    } 
    public static void logout() {
        model.MailDomain.getInstance().logout();
    }
}
