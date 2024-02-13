package Controller;

public class AppController {
    public static void main(String[] args) {
        AppController.start();
    }

    public static void start() {
        while (true) {
            if (!MenuController.handleMenuViewAndOptions()) {
                break;
            }
        }
    }
}
