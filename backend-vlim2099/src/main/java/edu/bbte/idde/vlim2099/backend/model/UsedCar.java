package edu.bbte.idde.vlim2099.backend.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
//Lombok a boiler kód generáláshoz

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor()

public class UsedCar extends BaseEntity {
    private String brand;
    private String model;
    private Double engineSize;
    private Integer horsePower;
    private Double numberOfKm;
    private Integer yearOfManufacture;
    private String chassisNumber;
    private Integer price;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer version;

    public void setCar(UsedCar newUsedCar) {
        this.brand = newUsedCar.brand;
        this.model = newUsedCar.model;
        this.engineSize = newUsedCar.engineSize;
        this.horsePower = newUsedCar.horsePower;
        this.numberOfKm = newUsedCar.numberOfKm;
        this.yearOfManufacture = newUsedCar.yearOfManufacture;
        this.chassisNumber = newUsedCar.chassisNumber;
        this.price = newUsedCar.price;
        this.version = newUsedCar.version;
    }

}


