package view;
import controller.AuthenticationController;

public class SignupView {

    public void start() {
        String mailId, passwd,mobNumber;
        ViewUtil.printBanner("Create account");
        while (true) {
            // get mail id
            mailId = ViewUtil.getMailIdInput();
            // get mobile number id
            mobNumber = ViewUtil.getMobNumInput();
            // get pwd
            passwd = ViewUtil.getPasswdInput();
            // login check
            if (AuthenticationController.signup(mailId, passwd,mobNumber)) break;
            else System.err.println("[FAILED] Mail id already exists !");
        }
        System.out.println("[OK] Account created successfully");

        //login automatically after creating account
        if(!AuthenticationController.login(mailId, passwd))
        {
            System.err.println("[ERR] Error while logging in !");
            System.out.println("[RE] Try logging in!");
            return;
        }
        //on successfull login
        ViewUtil.cleanTheScreen();
        (new MailView()).start();
    }
}
