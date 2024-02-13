package Controller;

import Model.Apartment;
import Model.Person;
import Model.UtilityExpenses;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ExpensesController {
    private static double costOfWaterPerPerson = 3.1;
    private static double costOfElectricityPerPerson = 5.2;
    private static double costOfHeatingPerSquareMeter = 2.5;

    public void setExpensesDetails() {
        costOfWaterPerPerson = readDoubleFromUser("Introduceti costul pentru apa per persoana: ");
        costOfElectricityPerPerson = readDoubleFromUser("Introduceti costul pentru electricitate per persoana: ");
        costOfHeatingPerSquareMeter = readDoubleFromUser("Introduceti costul pe metru patrat pentru încălzire: ");
    }

    public void displayCurrentCostIndexes() {
        System.out.println("Indicii costurilor actuale sunt:");
        System.out.printf("Costul pentru apa per persoana: %.2f RON\n", costOfWaterPerPerson);
        System.out.printf("Costul pentru electricitate per persoana: %.2f RON\n", costOfElectricityPerPerson);
        System.out.printf("Costul pentru încălzire per metru pătrat: %.2f RON\n", costOfHeatingPerSquareMeter);
    }

    public void displayOwnerExpensesTable(List<Apartment> apartments, List<Person> people) {
        System.out.println("Tabelul sumelor de platit pentru fiecare proprietar:");
        System.out.printf("%-16s %-16s %-21s\n", "Proprietar" , "Nr. Apartament", "Suma de achitat (RON)");
        for(Apartment apartment: apartments) {
            double ownerExpenses = calculateOwnerExpenses(apartment, people);
            String truncatedOwner = apartment.getOwner().length() <= 15 ? apartment.getOwner() : apartment.getOwner().substring(0, 10) + "...";
            System.out.printf("%-16s %-16s %-21.2f\n", truncatedOwner, apartment.getNrApart(), ownerExpenses);
        }
    }

    private double calculateOwnerExpenses(Apartment apartment, List<Person> persons) {
        int numberOfPersonsInApartment = 0;
        double surfaceOfApartment = apartment.getSurface();

        for(Person person : persons) {
            if (person.getNrApart() == apartment.getNrApart()) {
                numberOfPersonsInApartment++;
            }
        }

        double waterCost = numberOfPersonsInApartment * costOfWaterPerPerson;
        double electricityCost = numberOfPersonsInApartment * costOfElectricityPerPerson;
        double heatingCost = surfaceOfApartment * costOfHeatingPerSquareMeter;

        return waterCost + electricityCost + heatingCost;
    }

    public void displayMonthExpenses(List<Apartment> apartments, List<Person> persons) {
        LocalDate currentDate = LocalDate.now();
        UtilityExpenses expenses = calculateCommonMonthExpenses(apartments, persons);

        double heatingCost = expenses.getHeatingCost();
        double waterCost = expenses.getWaterCost();
        double electricityCost = expenses.getElectricityCost();

        List<String> monthNames = List.of(
                "Ianuarie", "Februarie", "Martie", "Aprilie", "Mai", "Iunie",
                "Iulie", "August", "Septembrie", "Octombrie", "Noiembrie", "Decembrie"
        );

        int currentMonthIndex = currentDate.getMonthValue() - 1;
        String currentMonth = monthNames.get(currentMonthIndex);

        System.out.println("Cheltuieli (estimative) comune pentru luna "+currentMonth+": ");
        System.out.printf("Caldura: %.2f RON\n", heatingCost);
        System.out.printf("Apa: %.2f RON\n", waterCost);
        System.out.printf("Curent: %.2f RON\n", electricityCost);
        System.out.printf("Total: %.2f RON\n", heatingCost + waterCost + electricityCost);
    }

    public static UtilityExpenses calculateCommonMonthExpenses(List<Apartment> apartments, List<Person> persons) {
        double heatingCost = calculateCommonHeatingCost(apartments);
        double waterCost = calculateCommonWaterCost(persons);
        double electricityCost = calculateCommonElectricityCost(persons);

        return new UtilityExpenses(heatingCost, waterCost, electricityCost);
    }

    private static double calculateCommonWaterCost(List<Person> persons) {
        int totalPersonsInBlock = persons.size();
        return totalPersonsInBlock * costOfWaterPerPerson;
    }

    private static double calculateCommonElectricityCost(List<Person> persons) {
        int totalPersonInBlock = persons.size();
        return totalPersonInBlock * costOfElectricityPerPerson;
    }

    private static double calculateCommonHeatingCost(List<Apartment> apartments) {
        double totalSurfaceArea = 0;

        for(Apartment apartment : apartments) {
            totalSurfaceArea += apartment.getSurface();
        }

        return totalSurfaceArea * costOfHeatingPerSquareMeter;
    }

    private static double readDoubleFromUser(String prompt) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print(prompt);
            return scanner.nextDouble();
        } catch (Exception error) {
            System.out.println("Error date reading: "+error);
            return readDoubleFromUser(prompt);
        }
    }
}
