
package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Mail implements Cloneable{
    private Date sentDate;
    private String sender,subject,content;
    private ArrayList<String> reciever,cc,bcc;
    private boolean forwarded,opened;

    public Mail(Date sentDate,String sender,ArrayList<String> reciever,ArrayList<String> cc,ArrayList<String> bcc,String subject,String content,boolean opened)
    {
        this.sentDate = sentDate;
        this.sender = sender;
        this.reciever = reciever;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.content = content;
        this.forwarded = false;
        this.opened = opened;
    }
    public String getSender() {
        return sender;
    }
    public ArrayList<String> getReciever() {
        return reciever;
    }

    public ArrayList<String> getBcc() {
        return bcc;
    }
    public ArrayList<String> getCc() {
        return cc;
    }
    
    public boolean isForwarded()
    {
        return forwarded;
    }
    public boolean isNotOpened()
    {
        return !opened;
    }
    public void setAsForwarded() {
        forwarded = true;
    }

    public void setBcc(ArrayList<String> bcc) {
        this.bcc = bcc;
    }

    
    public String read() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy ,HH:mm:ss");
        StringBuilder result= new StringBuilder();

        if(forwarded)
            result.append("Fwd:");

        result.append(subject+"\n");
        result.append("From    : "+sender+"\n");
        result.append("To      : "+reciever+"\n");

        if(!cc.isEmpty())
            result.append("CC      : "+cc+"\n");
            
        if(!bcc.isEmpty())
            result.append("BCC     : "+bcc+"\n");

        result.append("Date    : "+formatter.format(sentDate).toString()+"\n\n");

        if(forwarded)
            result.append("---- forwarded message ----\n");

        result.append("Content : \n"+content+"\n");
        
        //set opened true
        opened = true;
        return result.toString();
    }
    @Override
    public String toString()
    {
        StringBuilder result= new StringBuilder((!opened?"[NEW] ":""));
        result.append("From:"+sender+" ");
        result.append((forwarded?"Fwd:":"")+subject);
        return result.toString();
    }

    @Override
    protected Mail clone() {
        Mail mail = new Mail(sentDate, sender, reciever, cc, bcc, subject, content, opened);
        if (this.forwarded) 
            mail.setAsForwarded();
        return mail;
        
    }
    

}
