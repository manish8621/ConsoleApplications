package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MailDomain {

    private static MailDomain mailDomain;
    private String mailDomainName;
    private HashMap<String, User> users;

    // add session
    private User currentUser = null; // mail client can ony access this user

    MailDomain() {
        users = new HashMap<>();
        mailDomainName = "kmail.com";
        signUp("sia@kmail.com", "12ww", "+911234561890");
        signUp("kia@kmail.com", "12ww", "+911234568890");
        signUp("hia@kmail.com", "12ww", "+911234566890");
        signUp("hatori@kmail.com", "12ww", "+91123456090");
    }

    public String getDomainName() {
        return this.mailDomainName;
    }

    public static MailDomain getInstance() {
        if (mailDomain == null)
            mailDomain = new MailDomain();
        return mailDomain;
    }

    public boolean login(String mailId, String password) {
        if (checkUserAlreadyExists(mailId)) {
            User user = users.get(mailId);
            if (user.getPassword().equals(password)) {
                currentUser = users.get(mailId);
                return true;
            }
        }
        return false;
    }

    public void logout() {
        if (currentUser != null)
            currentUser = null;

    }

    public boolean signUp(String mailId, String password, String mobileNumber) {
        if (checkUserAlreadyExists(mailId))
            return false;
        // extract username from mail
        Matcher usrnameMatcher = Pattern.compile("(\\w+)[@]").matcher(mailId);
        String username = (usrnameMatcher.find()) ? usrnameMatcher.group(1) : "unknown";

        users.put(mailId, new User(mailId, username, password, mobileNumber));
        return true;

    }

    public boolean checkUserAlreadyExists(String mailId) {
        return users.containsKey(mailId);
    }

    // restricted the client not to access sensitive method
    public Session getSession() {
        return (Session) currentUser;
    }

    public void sendMail(Mail mail) {
        // store mail in outbox
        currentUser.addToOutbox(mail.clone());

        // send the mail to reciever's inbox
        for (String mailId : mail.getReciever()) {
            users.get(mailId).addToInbox(mail.clone());
        }

        // check if mail has cc
        for (String mailId : mail.getCc()) {
            users.get(mailId).addToInbox(mail.clone());
        }

        // bcc-
        for (String mailId : mail.getBcc()) {
            // clear the cc fully and bcc except destination address
            Mail _mail = mail.clone();
            _mail.setBcc(new ArrayList<>(Arrays.asList(mailId)));
            users.get(mailId).addToInbox(_mail);
        }
    }

    public void deleteMail(MailType mailType, Mail mail) {
        switch (mailType) {
            case INBOX:
                currentUser.deleteInboxMail(mail);
                break;
            case OUTBOX:
                currentUser.deleteOutboxMail(mail);
                break;
            default:
                break;
        }
    }

    public ArrayList<Mail> getMails(MailType mailType) {
        switch (mailType) {
            case INBOX:
                return currentUser.getInbox();
            case OUTBOX:
                return currentUser.getOutbox();
            default:
                return currentUser.getInbox();
        }
    }

    public void addMails(MailType mailType, Mail mail) {
        switch (mailType) {
            case INBOX:
                currentUser.addToInbox(mail);
                break;
            case OUTBOX:
                currentUser.addToOutbox(mail);
                break;
            default:
                currentUser.addToInbox(mail);
                break;
        }
    }

    public void changePassword(String password) {
        // if logged in
        if (currentUser != null)
            currentUser.setPassword(password);
    }

    public void changeMobileNumber(String mobileNum) {
        // if logged in
        if (currentUser != null)
            currentUser.setMobileNumber(mobileNum);
    }
    public ArrayList<Mail> getNewMails(){
        ArrayList<Mail> newMails =  new ArrayList<>();
        for (Mail mail : currentUser.getInbox()) 
            if(mail.isNotOpened())
                newMails.add(mail);
        return newMails;
    }

}
