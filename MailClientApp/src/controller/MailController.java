package controller;

import java.util.ArrayList;

import model.Mail;
import model.MailDomain;
import model.MailType;

public class MailController {
    static private MailDomain maildomain=MailDomain.getInstance();
    public static boolean checkUserExists(String mailid){
        return maildomain.checkUserAlreadyExists(mailid);
    }

    public static void requestChangeMobNum(String monileNumber) {
        maildomain.changeMobileNumber(monileNumber);
    }

    public static void requestChangePassword(String newPassword) {
        maildomain.changePassword(newPassword);
    }

    public void deleteMail(MailType mailType, Mail selectedMail) {
        maildomain.deleteMail(mailType, selectedMail);
    }

    public void sendMail(Mail composeMail) {
        maildomain.sendMail(composeMail);
    }

    public ArrayList<Mail> getMails(MailType mailType) {
        return maildomain.getMails(mailType);
    }

    public String getMailId() {
        return maildomain.getSession().getMailId();
    }

    public ArrayList<Mail> getNewMails() {
        return maildomain.getNewMails();
    }

    public String getUsername() {
        return maildomain.getSession().getUsername();
    }
}
