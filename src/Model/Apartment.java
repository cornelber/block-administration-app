package Model;

public class Apartment {
    private int nrApart;
    private String owner;
    private int nrPersons;
    private double surface;

    public Apartment(int nrApart, String owner, int nrPersons, double surface) {
        this.nrApart = nrApart;
        this.owner = owner;
        this.nrPersons = nrPersons;
        this.surface = surface;

    }

    public Apartment(int nrApart, String owner, double surface) {
        this.nrApart = nrApart;
        this.owner = owner;
        this.nrPersons = 0;
        this.surface = surface;
    }

    public int getNrApart() {
        return nrApart;
    }

    public void setNrApart(int nrApart) {
        this.nrApart = nrApart;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getNrPersons() {
        return nrPersons;
    }

    public void setNrPersons(int nrPersons) {
        this.nrPersons = nrPersons;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    @Override
    public String toString() {
        String truncatedOwner = owner.length() <= 15 ? owner : owner.substring(0, 10) + "...";
        return String.format("%-16d %-15s %-14d %-9.2f", nrApart, truncatedOwner, nrPersons, surface);
    }
}
