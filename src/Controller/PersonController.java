package Controller;

import Model.Person;
import Model.CrudOperations;
import Model.DataFileReader;

import java.time.LocalDate;
import java.util.*;

public class PersonController implements CrudOperations<Person> {
    @Override
    public void create(List<Person> people) {
        System.out.println("Introduceti informatii pentru persoana noua:");
        int nrApart = readIntegerFromUser("Numar apartament: ");

        if (!ApartmentController.apartmentNumberExists(nrApart)) {
            System.out.println("Numarul apartamentului nu exista in lista de apartamente. Persoana nu poate fi adaugata.");
            return;
        } else {
            ApartmentController.updateNumberOfPersons(nrApart, true);
        }

        String nameSurname = readStringFromUser("Nume si prenume: ");
        LocalDate birthDate = LocalDate.parse(readStringFromUser("Data de nastere (YYYY-MM-DD): "));
        String job = readStringFromUser("Loc de munca: ");

        System.out.println("Persoana a fost creata si adaugata in lista cu succes!");
        people.add(new Person(nameSurname, nrApart, birthDate, job));
    }

    @Override
    public List<Person> readAll() {
        return DataFileReader.readPersonsDataFile();
    }

    @Override
    public void displayAll(List<Person> persons) {
        System.out.println("Lista de persoane:");
        System.out.printf("%-7s %-15s %-16s %-15s %-16s\n", "Index", "Nume Prenume", "Nr. Apartament", "Data Nasterii", "Locul de Munca");
        int index = 1;
        for (Person person : persons) {
            System.out.printf("%-7s %s\n", index, person);
            index++;
        }
    }

    @Override
    public void update(List<Person> people) {
        int personIndexToUpdate = readIntegerFromUser("\nSelecteaza indexul persoanei care doresti sa modifici: ");

        if(personIndexToUpdate >= 1 && personIndexToUpdate <= people.size()) {
            Person personToUpdate = people.get(personIndexToUpdate - 1);

            System.out.println("Introduceti noile informatii:");

            updateNameSurname(personToUpdate);
            updateNrApart(personToUpdate);
            updateBirthDate(personToUpdate);
            updateJob(personToUpdate);

            System.out.println("Informațiile pentru persoana cu indexul " + personIndexToUpdate + " au fost actualizate cu succes!");
        } else {
            System.out.println("Indexul introdus nu este valid.");
        }
    }

    @Override
    public void delete(List<Person> people) {
        int personIndexToRemove = readIntegerFromUser("\nSelecteaza indexul persoanei care doresti sa stergi: ");

        if (personIndexToRemove >= 1 && personIndexToRemove <= people.size()) {
            int apartmentNumber = people.get(personIndexToRemove - 1).getNrApart();

            ApartmentController.updateNumberOfPersons(apartmentNumber, false);

            people.remove(personIndexToRemove - 1);
            System.out.println("Persoana a fost stearsa cu succes!");
        } else {
            System.out.println("Indexul introdus nu este valid.");
        }
    }

    public void displayPersonByProperty(List<Person> people, String property) {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        Map<String, List<Person>> peopleMap = new HashMap<>();

        for (Person person : people) {
            int age = currentYear - person.getBirthDate().getYear();
            String propertyKey = getPropertyKey(property, age);

            peopleMap.computeIfAbsent(propertyKey, k -> new ArrayList<>()).add(person);
        }

        List<Person> peopleForProperty = peopleMap.getOrDefault(property, Collections.emptyList());
        if(!peopleForProperty.isEmpty()) {
            System.out.println("Lista persoanelor cu proprietatea '" + property + "':");
            peopleForProperty.forEach(person -> System.out.println(person.getNameSurname()));
        } else {
            System.out.println("Nu există persoane cu proprietatea '"+property+"'.");
        }
    }

    private static void updateNameSurname(Person person) {
        String response = readStringFromUser("Doresti sa modifici numele si prenumele? (Y/N): ");
        if (response.equalsIgnoreCase("y")) {
            String newNameSurname = readStringFromUser("Nume si prenume nou: ");
            person.setNameSurname(newNameSurname);
        }
    }

    private static void updateNrApart(Person person) {
        String response = readStringFromUser("Doresti sa modifici numarul apartamentului? (Y/N): ");
        if (response.equalsIgnoreCase("y")) {
            int oldNrApart = person.getNrApart();
            int newNrApart = readIntegerFromUser("Numar apartament nou: ");

            if(ApartmentController.apartmentNumberExists(newNrApart)) {
                person.setNrApart(newNrApart);

                ApartmentController.updateNumberOfPersons(oldNrApart, false);
                ApartmentController.updateNumberOfPersons(oldNrApart, true);
            } else {
                System.out.println("Numarul apartamentului introdus nu exista in lista de apartamente.");
            }
        }
    }

    private static void updateBirthDate(Person person) {
        String response = readStringFromUser("Doresti sa modifici data nasterii? (Y/N): ");
        if (response.equalsIgnoreCase("y")) {
            LocalDate newBirthDate = LocalDate.parse(readStringFromUser("Data de nastere (YYYY-MM-DD): "));
            person.setBirthDate(newBirthDate);
        }
    }

    private static void updateJob(Person person) {
        String response = readStringFromUser("Doresti sa modifici locul de munca? (Y/N): ");
        if (response.equalsIgnoreCase("y")) {
            String newJob = readStringFromUser("Loc de munca nou: ");
            person.setJob(newJob);
        }
    }

    public static void deletePeopleByApartmentNumber(int apartmentNumber) {
        List<Person> people = BlockAdminController.getPeopleList();

        List<Person> peopleToDelete = new ArrayList<>();

        for (Person person : people) {
            if (person.getNrApart() == apartmentNumber) {
                peopleToDelete.add(person);
            }
        }

        people.removeAll(peopleToDelete);
    }

    public static void updatePeopleByApartmentNumber(int oldApartmentNumber, int newApartmentNumber) {
        List<Person> people = BlockAdminController.getPeopleList();

        List<Person> peopleToUpdate = new ArrayList<>();

        for (Person person : people) {
            if (person.getNrApart() == oldApartmentNumber) {
                peopleToUpdate.add(person);
            }
        }

        for(Person person : peopleToUpdate) {
            person.setNrApart(newApartmentNumber);
        }
    }

    private static String getPropertyKey(String property, int age) {
        switch (property) {
            case "copil":
                if(age < 18) return "copil";
            case "adult":
                if(age >= 18 && age <= 64) return "adult";
            case "pensionar":
                if(age > 64) return "pensionar";
            default:
                return "Proprietate inexistenta.";
        }
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
}
