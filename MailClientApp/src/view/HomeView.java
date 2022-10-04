package view;
import java.util.ArrayList;
import java.util.Arrays;
public class HomeView {
    public void start() {
        var choiceIndex = 0;

        while (true) {
            System.out.println();
            ViewUtil.printBanner("KMAIL HOME");
            
            //pass the choices ViewUtil will handle errors and return the correct input 
            choiceIndex = ViewUtil.getNumberChoiceInput(new ArrayList<>(Arrays.asList("Login","Signup","Exit")),false);
            ViewUtil.cleanTheScreen();
            switch (choiceIndex) {
                case 1:
                    (new LoginView()).start();
                    break;
                case 2:
                    (new SignupView()).start();
                    break;
                case 3:
                    System.out.println("Have a nice day");
                    return;
                default:
                    return;
            }
        }
    }
}
