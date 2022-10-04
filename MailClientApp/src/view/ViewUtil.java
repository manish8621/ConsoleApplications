package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.Validator;

public class ViewUtil {

    static Scanner scan = new Scanner(System.in);
    public static void cleanTheScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();  
    } 
    //allow zero is used for back functionality in some cases
    static public byte getNumberChoiceInput(ArrayList choices,boolean allowZero) {
        byte choiceIndex = 0;
        // print choices with numbers
        if(allowZero)
            System.out.println("0.Back");
        for (int index = 0; index < choices.size(); index++)
            System.out.println((index + 1) +"."+ choices.get(index).toString());
        while (true) {
            System.out.print("input:");
            try {
                choiceIndex = Byte.parseByte(scan.nextLine());
            } catch (Exception e) {
                System.err.println("Invalid input");
                continue;
            }
            if (choiceIndex >= (allowZero?0:1) && choiceIndex <= choices.size())
                break;
            else
                System.err.println("Invalid input");
        }
        return choiceIndex;
    }
    static public String getTextInput(String promptMsg) {
        String userInput="";
        while (true) {
            System.out.print(promptMsg+":");
            try {
                userInput = scan.nextLine();
            } catch (Exception e) {
                System.err.println("Invalid input");
                continue;
            }
            //might be unnessesary check n ******
            if (userInput.length()>0)
                break;
            else
                System.err.println("Invalid input");
        }
        return userInput;
    }
    public static void printBanner(String title)
    {
        byte marginLeftRight=3;
    
        System.out.println();
        System.out.print("+");
        for (int i = 0; i < (2*marginLeftRight)+title.length(); i++) 
            System.out.print("-");
        System.out.println("+");
        //2nd line
        System.out.print("|");
        for (int i = 0; i < marginLeftRight; i++) {
            System.out.print(" ");
        }
        System.out.print(title);
        for (int i = 0; i < marginLeftRight; i++) {
            System.out.print(" ");
        }
        System.out.println("|");
        //last line
        System.out.print("+");
        for (int i = 0; i < (2*marginLeftRight)+title.length(); i++) 
            System.out.print("-");
        System.out.println("+");
        System.out.println();
    }
    public static String getMailIdInput(){
        String mailId="";
        while (true) {
            mailId = ViewUtil.getTextInput("Mail ID");
            if (Validator.validateMailId(mailId))
                break;
            else
                System.out.println("Invalid mail id");
        }
        return mailId;
    }
    public static String getPasswdInput(){
        String passwd="";
        while (true) {
            passwd = ViewUtil.getTextInput("Password");
            if (Validator.validatePassword(passwd))
                break;
            else
                System.out.println("Invalid password");
        }
        return passwd;
    }
    public static String getMobNumInput(){
        String mobNum="";
        while (true) {
            mobNum = ViewUtil.getTextInput("Mobile number");
            if (Validator.validateMobileNumber(mobNum))
                break;
            else
                System.out.println("Invalid mobile number");
        }
        return mobNum;
    }
    public static byte getNumberInput(String promptMsg,byte min,byte max){
        byte number=0;
        while (true) {
            number = Byte.parseByte(ViewUtil.getTextInput(promptMsg));
            if (number >= min && number <=max)
                break;
            else
                System.out.println("number should be within "+min+"-"+max);
        }
        return number;
    }
}
