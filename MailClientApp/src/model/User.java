package model;

public class User extends MailBox implements Session{
    private String mailId, username,password,mobileNumber;
    
    public User(String mailId,String username,String password,String mobileNumber)
    {
        this.username = username;
        this.password = password;
        this.mailId = mailId;
        this.mobileNumber = mobileNumber;
    }
    public String getMailId() {
        return mailId;
    }
    public String getMobileNumber() {
        return mobileNumber;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
    @Override
    public String toString() {
        return this.mailId;
    }
    
}
