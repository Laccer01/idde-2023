package edu.bbte.idde.vlim2099.backend.model;

public class UsedCar extends BaseEntity {
    private String brand;
    private String model;
    private Double engineSize;
    private Integer horsePower;
    private Double numberOfKm;
    private Integer yearOfManufacture;
    private String chassisNumber;
    private Integer price;

    public UsedCar() {

    }

    public UsedCar(String brand, String model, Double engineSize, Integer horsePower,
                   Double numberOfKm, Integer yearOfManufacture, String chassisNumber, Integer price) {

        this.brand = brand;
        this.model = model;
        this.engineSize = engineSize;
        this.horsePower = horsePower;
        this.numberOfKm = numberOfKm;
        this.yearOfManufacture = yearOfManufacture;
        this.chassisNumber = chassisNumber;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(Double engineSize) {
        this.engineSize = engineSize;
    }

    public Integer getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(Integer horsePower) {
        this.horsePower = horsePower;
    }

    public Double getNumberOfKm() {
        return numberOfKm;
    }

    public void setNumberOfKm(Double numberOfKm) {
        this.numberOfKm = numberOfKm;
    }

    public Integer getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(Integer yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setCar(UsedCar newUsedCar) {
        this.brand = newUsedCar.brand;
        this.model = newUsedCar.model;
        this.engineSize = newUsedCar.engineSize;
        this.horsePower = newUsedCar.horsePower;
        this.numberOfKm = newUsedCar.numberOfKm;
        this.yearOfManufacture = newUsedCar.yearOfManufacture;
        this.chassisNumber = newUsedCar.chassisNumber;
        this.price = newUsedCar.price;
    }

    @Override
    public String toString() {
        return "UsedCar{"
                + "brand='" + brand + '\''
                + ", model='" + model + '\''
                + ", engineSize=" + engineSize
                + ", horsePower=" + horsePower
                + ", numberOfKm=" + numberOfKm
                + ", yearOfManufacture=" + yearOfManufacture
                + ", chassisNumber='" + chassisNumber + '\''
                + ", price=" + price
                + '}';
    }
}
