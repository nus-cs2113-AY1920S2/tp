package seedu.duke;

public class Food {
    String foodName;
    private double carbohydrates;
    private double fats;
    private double fiber;
    private double minerals;
    private double proteins;
    private double vitamins;
    private double water;

    /**
     * Constructs the Food Object and its relevant nutirtional information.
     */

    public Food(String foodName, double carbohydrates, double fats, double fiber, double minerals,
                double proteins, double vitamins, double water) {
        this.foodName = foodName;
        setCarbohydrates(carbohydrates);
        setFats(fats);
        setFiber(fiber);
        setMinerals(minerals);
        setProteins(proteins);
        setVitamins(vitamins);
        setWater(water);
    }

    public Food(String foodName){
        this.foodName = foodName;
    }

    public void setCarbohydrates(double carbohydrates){
        this.carbohydrates = carbohydrates;
    }

    public void setFats(double fats){
        this.fats = fats;
    }

    public void setFiber(double fiber){
        this.fiber = fiber;
    }

    public void setMinerals(double minerals){
        this.minerals = minerals;
    }

    public void setProteins(double proteins){
        this.proteins = proteins;
    }

    public void setVitamins(double vitamins){
        this.vitamins = vitamins;
    }

    public void setWater(double water){
        this.water = water;
    }

    public String getFoodName() {
        return foodName;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public double getFats() {
        return fats;
    }

    public double getFiber() {
        return fiber;
    }

    public double getMinerals() {
        return minerals;
    }

    public double getProteins() {
        return proteins;
    }

    public double getVitamins() {
        return vitamins;
    }

    public double getWater() {
        return water;
    }
}
