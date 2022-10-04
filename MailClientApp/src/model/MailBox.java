package model;

import java.util.ArrayList;

public class MailBox {
    private ArrayList<Mail> inbox;
    private ArrayList<Mail> outbox;
    MailBox()
    {
        inbox=new ArrayList<>();
        outbox=new ArrayList<>();
    }

    public ArrayList<Mail> getInbox() {
        return inbox;
    }
    public ArrayList<Mail> getOutbox() {
        return outbox;
    }
    public void addToInbox(Mail mail)
    {
        inbox.add(0,mail);
    }
    
    public void addToOutbox(Mail mail)
    {
        outbox.add(0,mail);
    }

    public void deleteInboxMail(Mail mail)
    {
        inbox.remove(mail);
    }
    public void deleteOutboxMail(Mail mail)
    {
        outbox.remove(mail);
    }
    

    @Override
    public String toString() {
        return inbox.toString();
    }
}
