package Model;

import java.time.LocalDate;

public class Person {
    private String nameSurname;
    private int nrApart;
    private LocalDate birthDate;
    private String job;

    // Constructor, getteri si setteri
    public Person (String nameSurname, int nrApart, LocalDate birthDate, String job) {
        this.nameSurname = nameSurname;
        this.nrApart = nrApart;
        this.birthDate = birthDate;
        this.job = job;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public int getNrApart() {
        return nrApart;
    }

    public void setNrApart(int nrApart) {
        this.nrApart = nrApart;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Override
    public String toString() {
        String truncatedNameSurname = nameSurname.length() <= 15 ? nameSurname : nameSurname.substring(0, 10) + "...";
        return String.format("%-15s %-16d %-15s %-16s", truncatedNameSurname, nrApart, birthDate, job);
    }
}
