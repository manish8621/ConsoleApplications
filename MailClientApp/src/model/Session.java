package model;

//to restrict the client direct access of some methods and mailbox
public interface Session {
    public String getMailId();
    public String getUsername();
    public String getMobileNumber();
}
