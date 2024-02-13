package Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DataFileReader {

    public static List<Person> readPersonsDataFile() {
        List<Person> persons = new ArrayList<>();
        try {
            String personsFilePath = "src/Data/Persons.txt";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(personsFilePath));

            String line;
            while((line = bufferedReader.readLine()) != null) {
                String[] attribute = line.split(",");

                persons.add(new Person(
                        attribute[0].trim(),
                        Integer.parseInt(attribute[1].trim()),
                        LocalDate.of(Integer.parseInt(attribute[4].trim()), Integer.parseInt(attribute[3].trim()), Integer.parseInt(attribute[2].trim())),
                        attribute[5].trim()
                ));
            }
            bufferedReader.close();
            return persons;

        } catch (Exception error) {
            System.out.println("Persons Data File Reader error: "+error);
            return null;
        }
    }

    public static List<Apartment> readApartmentsDataFile() {
        List<Apartment> apartments = new ArrayList<>();
        try {
            String apartmentsFilePath = "src/Data/Apartments.txt";
            BufferedReader bufferedReader = new BufferedReader(new FileReader(apartmentsFilePath));

            String line;
            while((line = bufferedReader.readLine()) != null ) {
                String[] attribute = line.split(",");

                apartments.add(new Apartment(
                        Integer.parseInt(attribute[0].trim()),
                        attribute[1].trim(),
                        Integer.parseInt(attribute[2].trim()),
                        Double.parseDouble(attribute[3].trim())
                ));
            }
            bufferedReader.close();
            return apartments;

        } catch (Exception error) {
            System.out.println("Apartments Data File Reader error: "+error);
            return null;
        }
    }
}
