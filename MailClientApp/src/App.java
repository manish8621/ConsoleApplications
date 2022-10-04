import view.HomeView;

public class App {
    public static void main(String[] args) throws Exception {
        App app = new App();
        app.start();
    }
    void start()
    {
        HomeView homeView = new HomeView();
        homeView.start();
    }
}
