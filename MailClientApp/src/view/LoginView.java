package view;

import controller.AuthenticationController;

public class LoginView {

    public void start() {
        String mailId, passwd;
        ViewUtil.printBanner("Login account");
        while (true) {
            // get mail id
            mailId = ViewUtil.getMailIdInput();
            // get pwd
            passwd = ViewUtil.getPasswdInput();

            // login check
            if (AuthenticationController.login(mailId, passwd)) break;
            else System.err.println("Incorrect mail id or password");

        }
        
        ViewUtil.cleanTheScreen();
        //on success login
        (new MailView()).start();
    }

}
