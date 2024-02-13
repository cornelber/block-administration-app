package View;

public class MenuView {

    public void displayInitialMenu() {
        System.out.println("\n*****************");
        System.out.println("* DEMO VERSIUNE *");
        System.out.println("* Meniu Initial *");
        System.out.println("*****************");
        System.out.println("1. Citire date din fisiere");
        System.out.println("2. Terminare program");
    }

    public void displayMainMenu() {
        System.out.println("\n***************************");
        System.out.println("* Meniu Administrare Bloc *");
        System.out.println("***************************");
        System.out.println("1. Gestionare Persoane");
        System.out.println("2. Gestionare Apartamente");
        System.out.println("3. Gestionare Cheltuieli Comune");
        System.out.println("4. Afisare Lista Persoane dupa Proprietate");
        System.out.println("5. Terminare program");
    }

    public void displayPersonMenu() {
        System.out.println("\n*****************************");
        System.out.println("* Meniu Gestionare Persoane *");
        System.out.println("*****************************");
        System.out.println("1. Adauga Persoana");
        System.out.println("2. Actualizeaza Persoana");
        System.out.println("3. Sterge Persoana");
        System.out.println("4. Afiseaza lista cu persoane");
        System.out.println("5. Intoarcere in meniul principal");
    }

    public void displayApartmentMenu() {
        System.out.println("\n********************************");
        System.out.println("* Meniu Gestionare Apartamente *");
        System.out.println("********************************");
        System.out.println("1. Adauga Apartament");
        System.out.println("2. Actualizeaza Apartament");
        System.out.println("3. Sterge Apartament");
        System.out.println("4. Afiseaza lista cu apartamente");
        System.out.println("5. Intoarcere in meniul principal");
    }

    public void displayExpensesMenu() {
        System.out.println("\n*******************************");
        System.out.println("* Meniu Gestionare Cheltuieli *");
        System.out.println("*******************************");
        System.out.println("1. Actualizeaza indicii de apa, curent si caldura");
        System.out.println("2. Afiseaza indicii de apa, curent si caldura");
        System.out.println("3. Afiseaza cheltuielile lunare comune");
        System.out.println("4. Afiseaza sumele de plata pentru fiecare proprietar");
        System.out.println("5. Intoarcere in meniul principal");
    }

    public void displayPersonByPropertyMenu() {
        System.out.println("\n*******************************************");
        System.out.println("* Meniu Afisare Persoane dupa Proprietate *");
        System.out.println("*******************************************");
        System.out.println("1. Afiseaza copii");
        System.out.println("2. Afiseaza adulti");
        System.out.println("3. Afiseaza pensionari");
        System.out.println("4. Intoarcere in meniul principal");
    }
}
