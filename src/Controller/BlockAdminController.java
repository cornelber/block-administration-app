package Controller;

import Model.Apartment;
import Model.Person;

import java.util.ArrayList;
import java.util.List;

public class BlockAdminController {
    private static List<Person> people = new ArrayList<>();
    private static List<Apartment> apartments = new ArrayList<>();
    private final PersonController personController = new PersonController();
    private final ApartmentController apartmentController = new ApartmentController();
    private final ExpensesController expensesController = new ExpensesController();

    public void setPeopleAndApartmentsList() {
        people = personController.readAll();
        apartments = apartmentController.readAll();
    }

    public static List<Apartment> getApartmentsList() {
        return apartments;
    }

    public static List<Person> getPeopleList() {
        return people;
    }

    // Operatiuni CRUD pentru Apartment si Person
    public void createPerson() {
        personController.create(people);
    }

    public void createApartment() {
        apartmentController.create(apartments);
    }

    public void displayAndDeletePerson() {
        if (!people.isEmpty()) {
            personController.displayAll(people);
            personController.delete(people);
        } else {
            System.out.println("Nu s-a gasit nicio persoana in lista.");
        }
    }

    public void displayAndDeleteApartment() {
        if (!apartments.isEmpty()) {
            apartmentController.displayAll(apartments);
            apartmentController.delete(apartments);
        } else {
            System.out.println("Lista de apartamente este goala.");
        }
    }

    public void displayAndUpdatePerson() {
        if (!people.isEmpty()) {
            personController.displayAll(people);
            personController.update(people);
        } else {
            System.out.println("Nu s-a gasit nicio persoana in lista.");
        }
    }

    public void displayAndUpdateApartment() {
        if (!apartments.isEmpty()) {
            apartmentController.displayAll(apartments);
            apartmentController.update(apartments);
        } else {
            System.out.println("Lista de apartamente este goala.");
        }
    }

    public void displayAllPeople() {
        if (!people.isEmpty()) {
            personController.displayAll(people);
        } else {
            System.out.println("Lista de persoane este goala.");
        }
    }

    public void displayAllApartments() {
        if (!apartments.isEmpty()) {
            apartmentController.displayAll(apartments);
        } else {
            System.out.println("Lista de apartamente este goala.");
        }
    }

    // Operatiuni Expenses
    public void updateExpensesDetails() {
        expensesController.setExpensesDetails();
    }

    public void displayCostDetails() {
        expensesController.displayCurrentCostIndexes();
    }

    public void displayTotalOwnerExpenses() {
        expensesController.displayOwnerExpensesTable(apartments, people);
    }

    public void displayCommonMonthlyExpenses() {
        expensesController.displayMonthExpenses(apartments, people);
    }

    // Operatiune afisare (persoana dupa proprietate)
    public void displayByProperty(String property) {
        personController.displayPersonByProperty(people, property);
    }
}
