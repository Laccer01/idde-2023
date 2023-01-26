package edu.bbte.idde.vlim2099.spring.dao.model;

import lombok.*; //Lombok a boiler kód generáláshoz

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "SpringUsedCar")

public class UsedCar extends BaseEntity {
    @NotNull
    private String brand;
    @NotNull
    private String model;
    @NotNull
    @Positive
    private Double engineSize;
    @NotNull
    @Positive
    private Integer horsePower;
    @NotNull
    @Positive
    private Double numberOfKm;
    @NotNull
    @Positive
    private Integer yearOfManufacture;
    @NotNull
    private String chassisNumber;
    @NotNull
    @Positive
    private Integer price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "used_car_owner_id", nullable = false)
    private UsedCarOwner usedCarOwner;

    public void setCar(UsedCar newUsedCar) {
        this.brand = newUsedCar.brand;
        this.model = newUsedCar.model;
        this.engineSize = newUsedCar.engineSize;
        this.horsePower = newUsedCar.horsePower;
        this.numberOfKm = newUsedCar.numberOfKm;
        this.yearOfManufacture = newUsedCar.yearOfManufacture;
        this.chassisNumber = newUsedCar.chassisNumber;
        this.price = newUsedCar.price;
        this.usedCarOwner = newUsedCar.usedCarOwner;
    }

}


