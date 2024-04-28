package ForestrySimulationProject;

import java.util.Random;

public class Tree {
    private int yearPlanted;
    private double treeHeight;
    private double growthRatePerYear;

    enum Species{
        MAPLE,
        FIR,
        BIRCH
    }
    private Species species;
    public Tree( Species species, int yearPlanted, double treeHeight, double growthRatePerYear){
        this.yearPlanted = yearPlanted;
        this.treeHeight = treeHeight;
        this.growthRatePerYear = growthRatePerYear;
        this.species = species;
    }

    // Getters
    public Species getSpecies() {
        return species;
    }

    public int getYearPlanted() {
        return yearPlanted;
    }

    public double getHeight() {
        return treeHeight;
    }

    public double getGrowthRate() {
        return growthRatePerYear;
    }

    // Setters
    public void setSpecies(Species species) {
        this.species = species;
    }

    public void setYearPlanted(int yearPlanted) {
        this.yearPlanted = yearPlanted;
    }

    public void setHeight(double treeHeight) {
        this.treeHeight = treeHeight;
    }

    public void setGrowthRate(double growthRatePerYear) {
        this.growthRatePerYear = growthRatePerYear;
    }

    public void simulateGrowthRate() {
        double growthFactor = 1 + (growthRatePerYear / 100);
        treeHeight *= growthFactor;
    }

    public String toString(){
        return species + ", " + yearPlanted +", "+ String.format("%.1f", treeHeight)+ ", " + String.format("%.1f", growthRatePerYear)+ "%";
    }

    Tree generateRandomTree() {
        Random rand = new Random();
        Species[] species = Species.values();
        Species randomSpecies = species[rand.nextInt(species.length)];
        int randomYear = rand.nextInt(25) + 2000;
        double randomHeight = rand.nextDouble() * 10 + 10;
        double randomGrowthRate = rand.nextDouble() * 10 + 10;
        return new Tree(randomSpecies, randomYear, randomHeight, randomGrowthRate);
    }

}// end of Tree class


