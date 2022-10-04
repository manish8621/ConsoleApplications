Mail client

Audience : mail users
Duration:5Hrs

Features:
1.Login mail account
2.sign up
4.send a mail with subject and content (CC,BCC,Multiple recipients)
5.view inbox with (readed / unreaded)
6.delete a mail
7.forward a mail
8.change user details


mvc

Model :
interface Session
    public String getMailId();
    public String getUsername();
    public String getMobileNumber();
Mail
Private:
    sentTime:date
    sender:String
    reciever:String
    forworded:boolean
    opened : boolean
    cc:String
    bcc:String
    subject
    content

MailBox
    inbox:ArrayList<mail>
    outbox:ArrayList<mail>

//every user owns a mailbox
User extends MailBox implements session
    mailId:String
    username:String
    password:String
    mobileNumber:String
    mailBox:MailBox

//mail domain is where all the operations performed
MailDomain
    Fields
    mailDomainName:String
    Private users:Hashmap<String,User>          //string is 
    currentUser:user = null             //mail client can ony access this user
    
    Methods
    boolean login(mailId,password)
    signUp(mailId,username,password,mobnumber)
    logut()
    boolean checkUserAlreadyExists(mailid)
    
    // to restrict the client not tohave direct access user object
    getSession()
    
    sendMail(mail)
    deleteMail(mailType<enum> ,mail)
    getMails(mailType<enum>)
    addMails(mailType<enum>,mail)
    changePassword(String password)
    changeMobileNumber(String mobileNum)
    getNewMails()



Rest of the functionalities will be provided by View,Controller Classes


Technical details

 * MailDomain instance will be created at once 

 * it will be accessed by the controller classses

 * view classes do operations via controller classes
