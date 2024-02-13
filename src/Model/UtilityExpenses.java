package Model;

public class UtilityExpenses {
    private double heatingCost;
    private double waterCost;
    private double electricityCost;

    public UtilityExpenses(double heatingCost, double waterCost, double electricityCost) {
        this.heatingCost = heatingCost;
        this.waterCost = waterCost;
        this.electricityCost = electricityCost;
    }

    public double getHeatingCost() {
        return heatingCost;
    }

    public void setHeatingCost(double heatingCost) {
        this.heatingCost = heatingCost;
    }

    public double getWaterCost() {
        return waterCost;
    }

    public void setWaterCost(double waterCost) {
        this.waterCost = waterCost;
    }

    public double getElectricityCost() {
        return electricityCost;
    }

    public void setElectricityCost(double electricityCost) {
        this.electricityCost = electricityCost;
    }
}