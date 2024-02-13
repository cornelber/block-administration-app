package Controller;

import Model.Apartment;
import Model.CrudOperations;
import Model.DataFileReader;

import java.util.List;
import java.util.Scanner;

public class ApartmentController implements CrudOperations<Apartment> {

    @Override
    public void create(List<Apartment> apartments) {
        System.out.println("Introduceti informatii pentru apartament nou:");

        int nrApart = readIntegerFromUser("Numar apartament: ");
        String owner = readStringFromUser("Proprietar: ");
        double surface = readDoubleFromUser("Suprafata apartamentului (m2): ");

        Apartment newApartment = new Apartment(nrApart, owner, surface);

        System.out.println("Apartamentul creat si adaugat in lista cu succes!");
        apartments.add(newApartment);
    }

    @Override
    public List<Apartment> readAll() {
        return DataFileReader.readApartmentsDataFile();
    }

    @Override
    public void displayAll(List<Apartment> apartments) {
        System.out.println("Lista de apartamente:");
        System.out.printf("%-16s %-15s %-14s %-9s\n", "Nr. Apartament", "Proprietar", "Nr. Persoane", "Suprafata");
        for (Apartment apartment : apartments) {
            System.out.printf("%s\n", apartment);
        }
    }

    @Override
    public void update(List<Apartment> apartments) {
        int nrApart = readIntegerFromUser("\nSelecteaza numarul apartamentului pe care doresti sa-l modifici: ");

        Apartment apartmentToUpdate = findApartmentByNrApart(apartments, nrApart);

        if(apartmentToUpdate != null) {
            System.out.println("Introduceti noile informatii:");

            updateNrApart(apartments, apartmentToUpdate);
            updateOwner(apartmentToUpdate);
            updateSurface(apartmentToUpdate);

            System.out.println("Informatiile pentru apartamentul cu numarul " + nrApart + " au fost actualizate cu succes!");
        } else {
            System.out.println("Numarul apartamentului introdus nu a fost gasit in lista.");
        }
    }

    @Override
    public void delete(List<Apartment> apartments) {
        int nrApart = readIntegerFromUser("\nSelecteaza numarul apartamentului pe care doresti sa-l stergi: ");

        Apartment apartmentToRemove = findApartmentByNrApart(apartments, nrApart);
        if (apartmentToRemove != null) {

            PersonController.deletePeopleByApartmentNumber(nrApart);

            apartments.remove(apartmentToRemove);
            System.out.println("Apartamentul si persoanele cu numarul de apartament " + nrApart + " a fost sterse cu succes!");
        } else {
            System.out.println("Numarul apartamentului introdus nu a fost gasit in lista.");
        }
    }

    private static void updateNrApart(List<Apartment> apartments, Apartment apartmentToUpdate) {
        String response = readStringFromUser("Doresti sa modifici numarul apartamentului? (Y/N): ");
        if (response.equalsIgnoreCase("y")) {
            int oldNrApart = apartmentToUpdate.getNrApart();
            int newNrApart = readIntegerFromUser("Numar nou al apartamentului: ");

            for(Apartment apartment : apartments) {
                if(apartment.getNrApart() != newNrApart) {
                    apartmentToUpdate.setNrApart(newNrApart);
                    PersonController.updatePeopleByApartmentNumber(oldNrApart, newNrApart);
                } else {
                    System.out.println("Apartament cu numarul " + newNrApart + " deja exista, nu poti modifica in felul dat.");
                    return;
                }
            }
        }
    }

    private static void updateOwner(Apartment apartment) {
        String response = readStringFromUser("Doresti sa modifici proprietarul? (Y/N): ");
        if (response.equalsIgnoreCase("y")) {
            String newOwner = readStringFromUser("Numele noului proprietar: ");
            apartment.setOwner(newOwner);
        }
    }

    private static void updateSurface(Apartment apartment) {
        String response = readStringFromUser("Doresti sa modifici suprafata? (Y/N): ");
        if (response.equalsIgnoreCase("y")) {
            double newSurface = readDoubleFromUser("Suprafata noua: ");
            apartment.setSurface(newSurface);
        }
    }

    public static void updateNumberOfPersons(int apartmentNumber, boolean addValue) {
        List<Apartment> apartments = BlockAdminController.getApartmentsList();

        Apartment apartment = findApartmentByNrApart(apartments, apartmentNumber);

        if(apartment != null) {
            int updatedNumberOfPersons = Math.max(0, apartment.getNrPersons() + (addValue ? 1 : -1));
            apartment.setNrPersons(updatedNumberOfPersons);
        }
    }

    public static boolean apartmentNumberExists(int apartmentNumber) {
        List<Apartment> apartments = BlockAdminController.getApartmentsList();

        for (Apartment apartment : apartments) {
            if (apartment.getNrApart() == apartmentNumber) {
                return true;
            }
        }
        return false;
    }

    private static Apartment findApartmentByNrApart(List<Apartment> apartments, int nrApart) {
        for (Apartment apartment : apartments) {
            if (apartment.getNrApart() == nrApart) {
                return apartment;
            }
        }
        return null;
    }

    private static String readStringFromUser(String prompt) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print(prompt);
            return scanner.nextLine();
        } catch (Exception error) {
            System.out.println("Error string reading: "+error);
            return readStringFromUser(prompt);
        }
    }

    private static int readIntegerFromUser(String prompt) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print(prompt);
            return scanner.nextInt();
        } catch (Exception error) {
            System.out.println("Error integer reading: "+error);
            return readIntegerFromUser(prompt);
        }
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
