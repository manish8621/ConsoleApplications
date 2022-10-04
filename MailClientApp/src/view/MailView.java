package view;

import java.util.Date;

import controller.AuthenticationController;
import controller.MailController;

import java.util.ArrayList;
import java.util.Arrays;

import model.Mail;
import model.MailType;

public class MailView {
    
    MailController mailController = new MailController();

    public void start() {
        //print startup details
        System.out.println("Logged in as " + mailController.getMailId());
        ViewUtil.printBanner("Welcome " + mailController.getUsername());
        // show unread inbox mails count
        ArrayList<Mail> unreadMails = mailController.getNewMails();
        if (unreadMails.size() != 0) {
            System.out.printf("You have (%d) new mail(s)\n", unreadMails.size());
            for (Mail mail : unreadMails)
                System.out.println(mail);
        }
        
        while (true) {
            System.out.println();
            ViewUtil.printBanner("Dashbboard");
            //get choice from user
            byte choiceIndex = ViewUtil.getNumberChoiceInput(new ArrayList<>(Arrays.asList(
                    "Compose mail", "Inbox", "Outbox", "Change password", "Change mobile number", "Logout")),false);
            ViewUtil.cleanTheScreen();

            switch (choiceIndex) {
                case 1:
                    ViewUtil.printBanner("Compose Mail");

                    Mail mail = composeMail("");
                    mailController.sendMail(mail);

                    System.out.println("[OK] Mail sent successfully!");
                    break;
                case 2:
                    showMailBox(MailType.INBOX);
                    break;
                case 3:
                    showMailBox(MailType.OUTBOX);
                    break;
                case 4:
                    // change passwd
                    changePassword();
                    System.out.println("Password changed successfully !");
                    break;
                case 5:
                    // change passwd
                    changeMobNum();
                    System.out.println("Mobile number changed successfully !");
                    break;
                case 6:
                    AuthenticationController.logout();
                    ViewUtil.cleanTheScreen();
                    System.out.println("Logged out");
                    return;
                default:
                    return;
            }
        }

    }

    private void changeMobNum() {
        String oldPassword, mobileNumber;
        while (true) {
            oldPassword = ViewUtil.getPasswdInput();

            if (AuthenticationController.login(mailController.getMailId(), oldPassword)) {
                System.out.println("Enter new mobile number");
                mobileNumber = ViewUtil.getMobNumInput();
                break;
            }
            System.out.println("Wrong password");
        }
        MailController.requestChangeMobNum(mobileNumber);
    }

    private void changePassword() {
        String oldPassword, newPassword;
        while (true) {
            System.out.println("Enter old password");
            oldPassword = ViewUtil.getPasswdInput();

            if (AuthenticationController.login(mailController.getMailId(), oldPassword)) {
                System.out.println("Enter new password");
                newPassword = ViewUtil.getPasswdInput();
                break;
            }
            System.out.println("Wrong password");
        }
        MailController.requestChangePassword(newPassword);
    }

    private Mail composeMail(String fwdContent) {
        Date date = new Date();
        String subject, content;
        ArrayList<String> reciever = new ArrayList<>();
        ArrayList<String> cc = new ArrayList<>();
        ArrayList<String> bcc = new ArrayList<>();
        Mail mail;
        //check for forward if forward content is not empty 
        boolean isFwdMsg = (fwdContent.isEmpty()) ? false : true;
        // n is to get how many mail ids user going to give for cc,bcc,recievers
        int n = 0;
        // get reciever
        n = ViewUtil.getNumberInput("number of recievers", (byte) 1, (byte) 10);
        System.out.println("Enter mail id(s) for recievers :");
        for (int i = 0; i < n; i++) {
            String id = (ViewUtil.getMailIdInput());
            if (MailController.checkUserExists(id))
                reciever.add(id);
            else {
                System.out.println("[WARN] Account not found!\nPlease Enter again !");
                i--;
            }
        }
        // get cc
        n = ViewUtil.getNumberInput("number of CC", (byte) 0, (byte) 10);
        if (n != 0) {
            System.out.println("Enter mail id(s) for CC :");
            for (int i = 1; i <= n; i++) {
                String id = (ViewUtil.getMailIdInput());
                if (MailController.checkUserExists(id))
                    cc.add(id);
                else {
                    System.out.println("[WARN] Account not found!\nPlease Enter again !");
                    i--;
                }
            }
        }
        // get bcc
        n = ViewUtil.getNumberInput("number of BCC", (byte) 0, (byte) 10);
        if (n != 0) {
            System.out.println("Enter mail id(s) for BCC :");
            for (int i = 1; i <= n; i++) {
                String id = (ViewUtil.getMailIdInput());
                if (MailController.checkUserExists(id))
                    bcc.add(id);
                else {
                    System.out.println("[WARN] Account not found!\nPlease Enter again !");
                    i--;
                }
            }
        }
        // get subject
        subject = ViewUtil.getTextInput("Subject");
        // get content
        content = (isFwdMsg) ? fwdContent : ViewUtil.getTextInput("Content");
        //prepare mail
        mail = (new Mail(date, mailController.getMailId(), reciever, cc, bcc, subject, content, false));
        //set if forwarded mail
        if (isFwdMsg)
            mail.setAsForwarded();

        return mail;

    }

    private void showMailBox(MailType mailType) {
        
        ViewUtil.printBanner(mailType.toString());
        ArrayList<Mail> mails = mailController.getMails(mailType);

        if (mails.size() != 0) {
            byte choiceIndex = 0;
            
            //user input
            //allow user to go back with 0 key
            byte mailIndex = ViewUtil.getNumberChoiceInput(mails,true);
            // if user pressed 0 go back
            if (mailIndex == 0)
                return;
            
            // else use it as index to get mail
            Mail selectedMail = mailController.getMails(mailType).get(mailIndex - 1);
            
            //print mail to screeen
            System.out.println(selectedMail.read());
            
            // get user action on mail
            //allow user to go back with 0 key
            choiceIndex = ViewUtil.getNumberChoiceInput(new ArrayList<>(Arrays.asList(
                    "Forward", "Delete")),true);
            switch (choiceIndex) {
                case 0:
                    return;
                case 1:
                    ViewUtil.printBanner("Forwarding Mail");
                    mailController.sendMail(composeMail(selectedMail.read()));
                    System.out.println("[OK] Mail forwarded");
                    showMailBox(mailType);
                    break;
                case 2:
                    mailController.deleteMail(mailType, selectedMail);
                    System.out.println("[OK] Mail deleted !");
                    showMailBox(mailType);
                    break;
                default:
                    break;
            }

        } else {
            System.out.println(mailType + " is empty");
            System.out.println();
        }
    }

}
