package Controller;

import Model.MenuState;
import View.MenuView;

import java.util.Scanner;

public class MenuController {
    private static MenuState currentState = MenuState.INITIAL_M;
    private final BlockAdminController blockAdmin = new BlockAdminController();

    private static void setCurrentState(MenuState newMenuState) {
        currentState = newMenuState;
    }

    public static boolean handleMenuViewAndOptions() {
        MenuController menuController = new MenuController();
        MenuView menuView = new MenuView();

        switch (currentState) {
            case INITIAL_M :
                menuView.displayInitialMenu();
                return menuController.readInitialMenuOptions();
            case MAIN_M:
                menuView.displayMainMenu();
                return menuController.readMainMenuOptions();
            case PERSON_M:
                menuView.displayPersonMenu();
                return menuController.readPersonMenuOptions();
            case APARTMENT_M:
                menuView.displayApartmentMenu();
                return menuController.readApartmentMenuOptions();
            case EXPENSES_M:
                menuView.displayExpensesMenu();
                return menuController.readExpensesMenuOptions();
            case PERSON_BY_PROPERTY_M:
                menuView.displayPersonByPropertyMenu();
                return menuController.readPersonByPropertyMenuOptions();
            default:
                System.out.println("Optiune invalida!");
        }
        return true;
    }

    public boolean readInitialMenuOptions() {
        int option = readOption();
        switch (option) {
            case 1:
                blockAdmin.setPeopleAndApartmentsList();
                setCurrentState(MenuState.MAIN_M);
                break;
            case 2:
                System.out.println("Terminare program.");
                return false;
            default:
                System.out.println("Optiune invalida!");
        }
        return true;
    }

    public boolean readMainMenuOptions() {
        int option = readOption();
        switch (option) {
            case 1:
                setCurrentState(MenuState.PERSON_M);
                break;
            case 2:
                setCurrentState(MenuState.APARTMENT_M);
                break;
            case 3:
                setCurrentState(MenuState.EXPENSES_M);
                break;
            case 4:
                setCurrentState(MenuState.PERSON_BY_PROPERTY_M);
                break;
            case 5:
                System.out.println("Terminare program.");
                return false;
            default:
                System.out.println("Optiune invalida!");
        }
        return true;
    }

    public boolean readPersonMenuOptions () {
        int option = readOption();
        switch (option) {
            case 1:
                blockAdmin.createPerson();
                break;
            case 2:
                blockAdmin.displayAndUpdatePerson();
                break;
            case 3:
                blockAdmin.displayAndDeletePerson();
                break;
            case 4:
                blockAdmin.displayAllPeople();
                break;
            case 5:
                System.out.println("Revenire in meniul principal.");
                setCurrentState(MenuState.MAIN_M);
                break;
            default:
                System.out.println("Optiune invalida!");
        }
        return true;
    }

    public boolean readApartmentMenuOptions() {
        int option = readOption();
        switch (option) {
            case 1:
                blockAdmin.createApartment();
                break;
            case 2:
                blockAdmin.displayAndUpdateApartment();
                break;
            case 3:
                blockAdmin.displayAndDeleteApartment();
                break;
            case 4:
                blockAdmin.displayAllApartments();
                break;
            case 5:
                System.out.println("Revenire in meniul principal.");
                setCurrentState(MenuState.MAIN_M);
                break;
            default:
                System.out.println("Optiune invalida!");
        }
        return true;
    }

    public boolean readExpensesMenuOptions() {
        int option = readOption();
        switch (option) {
            case 1:
                blockAdmin.updateExpensesDetails();
                break;
            case 2:
                blockAdmin.displayCostDetails();
                break;
            case 3:
                blockAdmin.displayCommonMonthlyExpenses();
                break;
            case 4:
                blockAdmin.displayTotalOwnerExpenses();
                break;
            case 5:
                System.out.println("Revenire in meniul principal.");
                setCurrentState(MenuState.MAIN_M);
                break;
            default:
                System.out.println("Opțiune invalidă!");
        }
        return true;
    }

    public boolean readPersonByPropertyMenuOptions() {
        int option = readOption();
        switch (option) {
            case 1:
                blockAdmin.displayByProperty("copil");
                break;
            case 2:
                blockAdmin.displayByProperty("adult");
                break;
            case 3:
                blockAdmin.displayByProperty("pensionar");
                break;
            case 4:
                System.out.println("Revenire in meniul principal.");
                setCurrentState(MenuState.MAIN_M);
                break;
            default:
                System.out.println("Opțiune invalidă!");
        }
        return true;
    }

    public int readOption() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("\nOptiunea: ");
            return scanner.nextInt();
        } catch (Exception error) {
            System.out.println("Error MenuController: "+error.getMessage());
            return readOption();
        }
    }
}